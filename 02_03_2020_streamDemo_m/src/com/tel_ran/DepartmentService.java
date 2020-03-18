package com.tel_ran;

import java.util.Collection;

public class DepartmentService {
    private Department department;
    private Employee employee;


    public long countOfEmpl(Collection<Department> departments, int salaryLimit) {
        return departments.stream()
                .filter(d -> d.getCode().substring(0, 3).equals("111"))
                .flatMap(d -> d.getEmployeesList().stream())
                .filter(e -> e.getSalary() > salaryLimit).count();
    }
}
