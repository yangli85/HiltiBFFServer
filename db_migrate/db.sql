CREATE TABLE EMPLOYEE(
    id INT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    joining_date DATE NOT NULL,
    salary DOUBLE NOT NULL,
    ssn VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IMAGE(
   id INT NOT NULL auto_increment,
   name VARCHAR(100) NOT NULL,
   pattern VARCHAR(100) NOT NULL,
   categroy VARCHAR(100) NOT NULL,
   content LONGBLOB NOT NULL,
   height Integer NOT NULL,
   width Integer NOT NULL,
   size double NOT NULL,
   PRIMARY KEY (id)
);