package org.example;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnitTestWithH2 {
    DataSource dataSource;

    @Before
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }

    @Test
    public void testCount() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setDataSource(dataSource);
        assertEquals(4, employeeDAO.count());
    }

    @Test
    public void testFindAll() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setDataSource(dataSource);

        List<Employee> employees = employeeDAO.findAll();
        assertEquals("James", employees.get(0).getFirstName());
        assertEquals(4, employees.size());
    }

}
