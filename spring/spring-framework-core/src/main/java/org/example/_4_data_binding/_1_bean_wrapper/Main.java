package org.example._4_data_binding._1_bean_wrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        BeanWrapper companyWrapper = new BeanWrapperImpl(company);
        companyWrapper.setPropertyValue("name", "Some Company Inc.");

        Employee employee = new Employee();
        BeanWrapper employeeWrapper = new BeanWrapperImpl(employee);
        employeeWrapper.setPropertyValue("name", "Jim Stravinsky");
        companyWrapper.setPropertyValue("managingDirector", employeeWrapper.getWrappedInstance());

        Float salary = (Float) companyWrapper.getPropertyValue("managingDirector.salary");
        System.out.println(company.getName());
    }
}
