package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Employee emp= new Employee();
        Dept dept=new Dept();
//        emp.addEmployee("1008","Pavan","aaa-zzz-aaa","Pune","8888888899",65000,"108");
//        emp.viewTable();
////        emp.deleteEmploye("Pavan");
//        emp.viewTable();
//        dept.viewTable();
//        emp.updateEmployeeName("Sam","Sameer");
        emp.viewTable();
//        emp.addEmployee("1008","Shivam","aa-bb-cc","pune","6559998897",65800,"108");
        dept.viewTable();
//        adding this employee to the dept table
//        dept.addData("IT","108",145,"1009");
        dept.viewTable();

    }
}