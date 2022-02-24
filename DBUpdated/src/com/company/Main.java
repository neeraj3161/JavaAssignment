package com.company;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    //    to get user input
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int userInput=0;
//        instances
        DBConnect connect_dataBase= new DBConnect();
        Employee employee = new Employee();
        Department department = new Department();

//        employee.newEmployee("1009","Prashant","aaa","Mumbai","123467",72562,"109");
        do {
            System.out.println("  -----------------------------");
            System.out.println("      Employee Database");
            System.out.println("  -----------------------------");
            System.out.println("   1. View Employee Data");
            System.out.println("   2. Edit Employee Data");
            System.out.println("   3. Table Count");
            System.out.println("   4. Department Info");
            System.out.println("   5. Assign Department");
            System.out.println("   6. Delete Department Data");

            System.out.println("   0. Exit");

//            System.out.println("   7. View Table");
//            System.out.println("   8. View Table");
            userInput = scanner.nextInt();
            if(userInput!=0){
                connect_dataBase.DBConnect();
            }else{
                connect_dataBase.DBClose();
                System.out.println("Closed");
            }
            switch (userInput) {
                case 1:
                    employee.completeEmpData();
                    System.out.println();
                    System.out.println("Press any 1 to Go back or 0 to quit");
                    userInput=scanner.nextInt();
                    break;

                case 2:
                    System.out.println("1. Add new employee");
                    System.out.println("2. Update Table");
                    System.out.println("3. Delete employee record");
                    System.out.println("4. Get Individual Info");
                    System.out.println("0. Exit");


                    userInput=scanner.nextInt();
                    if(userInput==1){
                        int i = 1000,j=100;
                        System.out.println("Press 0 to quit");
                        System.out.println("**Use a Emp_ID above :"+(i+employee.tableCount())+" ***");
                        System.out.print("Unique Emp_Id: ");
                        String emp_id=scanner.next();
                        System.out.print("Name: ");
                        String name=scanner.next();
                        System.out.print("Address: ");
                        String address=scanner.next();
                        System.out.print("City: ");
                        String city=scanner.next();
                        System.out.print("Ph_no: ");
                        String ph_no=scanner.next();
                        System.out.print("Salary: ");
                        int Salary=scanner.nextInt();
                        System.out.println("**Use a Dept ID above :"+(j+employee.tableCount())+" ***");
                        System.out.print("Unique Dept_ID: ");
                        String dept_id=scanner.next();
                        int dept_id_check=employee.checkDept_ID(dept_id);
                        if(dept_id_check==0){
                            employee.newEmployee(emp_id,name,address,city,ph_no,Salary,dept_id);
                            break;
                        }else{
                            System.out.println(dept_id_check);
                        }

                    }else if(userInput==2){
                        System.out.println("Enter the employee id you want to change: ");
                        String emp_id= scanner.next();
                        employee.getIndividualInfo(emp_id);
                        System.out.println("1. Name");
                        System.out.println("2. Address");
                        System.out.println("3. Ph_no");
                        System.out.println("4. City");
                        System.out.println("5. Salary");
                        System.out.println("0. Back");

                        int user_input= scanner.nextInt();
                        if(user_input==1){
                            System.out.println("Enter the updated name: ");
                            String update_name= scanner.next();
                            employee.updateName(emp_id,update_name);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);

                        }else if(user_input==2){
                            System.out.println("Enter the updated Address : ");
                            String address= scanner.next();
                            employee.updateName(emp_id,address);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                        }else if(user_input==3){
                            System.out.println("Enter the new phn_no: ");
                            String ph_no= scanner.next();
                            employee.updateName(emp_id,ph_no);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                        }else if(user_input==4){
                            System.out.println("Enter city: ");
                            String city= scanner.next();
                            employee.updateCity(emp_id,city);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                        }else if(user_input==5){
                            System.out.println("Enter updated salary: ");
                            int salary= scanner.nextInt();
                            employee.updateSalary(emp_id,salary);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                        }else if(user_input==0){break;}
                        break;
                    }else if(userInput==3){
                        System.out.println("Enter the employee id: ");
                        String emp_id= scanner.next();
                        employee.getIndividualInfo(emp_id);
                        System.out.println("Delete Data(y/n) >>> ");
                        String Final_input=scanner.next();
                        System.out.println(Final_input);
                        if(Final_input.equals("y")){
                            employee.deleteData(emp_id);
                            break;
                        }
                        break;
                    }else if(userInput==4){
                        System.out.println("Enter the employee id: ");
                        String emp_id= scanner.next();
                        employee.getIndividualInfo(emp_id);
                        System.out.println("Press any number to go back");
                        scanner.next();
                        break;
                    }else if(userInput==0){break;}

                case 3:
                    employee.tableCount();
                    System.out.println();
                    System.out.println("Press any 1 to Go back or 0 to quit");
                    userInput=scanner.nextInt();
                    break;

                case 4:
                    department.CompleteDepartmentInfo();
                    System.out.println("Press any number to exit");
                    scanner.nextInt();
                    break;

                case 5 :
                    System.out.println("Press 0 to quit");
                    System.out.print("Dept name: ");
                    String dept_name=scanner.next();
                    System.out.print("ref_id/dept_id: ");
                    String ref_id=scanner.next();
                    System.out.print("Unique ID: ");
                    String id=scanner.next();
                    System.out.println("No of enrollments: ");
                    int no_of_enrollments=scanner.nextInt();
                    department.assignDepartment(dept_name,ref_id,no_of_enrollments,id);
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Enter ref_if/dept_id: ");
                    String dept_id=scanner.next();
                    department.getIndividualDept(dept_id);
                    System.out.println("Delete Data(y/n) >>> ");
                    String Final_input=scanner.next();
                    System.out.println(Final_input);
                    if(Final_input.equals("y")){
                        department.deleteData(dept_id);
                        System.out.println("Data deleted successfully!!");
                        break;
                    }
                    break;

                case 0:
                    System.out.println("Thank you for using DB");

                    break;
                default:

                    }
            }
        while (userInput !=0);

        }
    }