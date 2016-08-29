package hilti.dao.impl;

import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import hilti.model.Employee;
import hilti.dao.EmployeeDao;
import hilti.dao.AbstractDao;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

    public void saveEmployee(Employee employee) {
        persist(employee);
    }

    @SuppressWarnings("unchecked")
    public List<Employee> findAllEmployees() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<Employee>) criteria.list();
    }

    public void deleteEmployeeBySsn(String ssn) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssn", ssn));
        Employee employee = (Employee)crit.uniqueResult();
        delete(employee);
    }


    public Employee findBySsn(String ssn) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssn", ssn));
        return (Employee) criteria.uniqueResult();
    }

    public void updateEmployee(Employee employee) {
        getSession().update(employee);
    }

}
