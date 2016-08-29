package hilti.controller;

import hilti.service.EmployeeService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import hilti.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Created by yangli on 8/25/16.
 */
@RestController
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployee() {
        List<Employee> employeeList = employeeService.findAllEmployees();
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity createCustomer(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Employee " + employee.getName());
        employeeService.saveEmployee(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/{ssn}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteUser(@PathVariable("ssn") String ssn) {
        System.out.println("Fetching & Deleting Employee with ssn " + ssn);

        Employee employee = employeeService.findBySsn(ssn);
        if (employee == null) {
            System.out.println("Unable to delete. Employee with ssn " + ssn + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        employeeService.deleteEmployeeBySsn(ssn);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/employee/{ssn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("ssn") String ssn) {
        System.out.println("Fetching Employee with ssn " + ssn);
        Employee employee = employeeService.findBySsn(ssn);
        if (employee == null) {
            System.out.println("Employee with ssn " + ssn + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
}