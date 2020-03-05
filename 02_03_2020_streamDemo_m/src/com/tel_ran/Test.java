package com.tel_ran;

import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Test {


    @org.junit.Test
    public  void test_sumOfCancelledTransactions(){
        Transaction tr1=new Transaction("89053425",State.FINISHED,2894);
        Transaction tr2=new Transaction("89034264356677", State.CANCELLED,320);
        Transaction tr3= new Transaction("432158543", State.CANCELLED, 1200);
        Transaction tr4=new Transaction("79456437", State.PROCESS, 3214);
        Transaction tr5=new Transaction("7946534437", State.CANCELLED, 100);
        Transaction tr6=new Transaction("542356437", State.PROCESS, 0);
        Transaction tr7=new Transaction("794652345637", State.FINISHED, 4500);
        Transaction tr8=new Transaction("7945647654", State.CANCELLED, 3000);
        Transaction tr9=new Transaction("67347437", State.FINISHED, 236);

        List<Transaction> transactionList1= Arrays.asList(tr1,tr2,tr3);
        List<Transaction> transactionList2=Arrays.asList(tr4,tr5,tr6);
        List<Transaction> transactionList3=Arrays.asList(tr7,tr8);

        Account account1=new Account("7984325", 3050, transactionList1);
        Account account2=new Account("56234634", -13050, transactionList2);
        Account account3=new Account("7856543", 350, transactionList3);

        List<Account> accountList=Arrays.asList(account1,account2,account3);

        TransactionService transactionService=new TransactionService();

        assertEquals(4520, transactionService.sumOfCancelledTransactions(accountList));

    }

    @org.junit.Test
    public void countEmployee(){
        Employee emp1=new Employee("vasya", 1200);
        Employee emp2=new Employee("bob",12000);
        Employee emp3=new Employee("petya", 3000);
        Employee emp4=new Employee("sasha", 300);
        Employee emp5=new Employee("masha", 2450);
        Employee emp6=new Employee("tom", 1500);
        Employee emp7=new Employee("bill", 113000);
        Employee emp8=new Employee("roman", 5000);
        Employee emp9=new Employee("polly", 4987);



        List<Employee> employeeList1=Arrays.asList(emp1, emp2, emp3);
        List<Employee> employeeList2=Arrays.asList(emp4,emp5,emp6);
        List<Employee> employeeList3=Arrays.asList(emp7,emp8,emp9);

        Department dep1=new Department("Financial","1118943", employeeList1);
        Department dep2=new Department("IT","111890543",employeeList2);
        Department dep3=new Department("administrative","3214523", employeeList3 );

        List<Department> departmentList=Arrays.asList(dep1,dep2,dep3);
        DepartmentService departmentService=new DepartmentService();
        int limit=2500;

        assertEquals(2,departmentService.countOfEmpl(departmentList,limit) );
    }
}
