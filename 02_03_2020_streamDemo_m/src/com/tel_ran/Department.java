package com.tel_ran;

import java.util.List;

public class Department {
    private String name;
    private String code;
    private List<Employee> employeesList;

    public Department(String name, String code, List<Employee> employeesList) {
        this.name = name;
        this.code = code;
        this.employeesList = employeesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }
}
