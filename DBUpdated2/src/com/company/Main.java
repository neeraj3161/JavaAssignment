package com.company;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
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
            System.out.println("   3. Table Employee Count");
            System.out.println("   4. View Department Data");
            System.out.println("   5. Assign Department");
            System.out.println("   6. Delete Department Data");
            System.out.println("   7. Department Count");
            System.out.println("   8. Department Info");
            System.out.println("   9. Min/Max salary");
            System.out.println("   0. Exit");
            try{
                userInput = scanner.nextInt();
                if(userInput!=0){
                    connect_dataBase.DBConnect();
                }else if(userInput==0){
                    connect_dataBase.DBClose();
                    System.out.println("DB Closed");
                }else{
                    System.out.println("Incorrect input please try again!!");
                }

            switch (userInput) {
                case 1:
                    employee.completeEmpData();
                    System.out.println();
                    System.out.println("Press any number to Go back or 0 to quit");
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
//                        int i = 1000,j=100;
                        System.out.println("Press 0 to quit");
//                        System.out.println("**Use a Emp_ID above :"+(i+employee.tableCount())+" ***");
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
                        if(ph_no.length()>10){
                            System.out.println("Phone number should be 10 digits please try again");
                            System.out.println("\n\nPress any number to exit");
                            scanner.nextInt();
                            break;
                        }
                        System.out.print("Salary: ");
                        int Salary=scanner.nextInt();
                        if(Salary<=0){
                            System.out.println("Salary should be greater than 0 please try again");
                            System.out.println("\n\nPress any number to exit");
                            scanner.nextInt();
                            break;
                        }
//                        System.out.println("**Use a Dept ID above :"+(j+employee.tableCount())+" ***");
                        System.out.print("Unique Dept_ID: ");
                        String dept_id=scanner.next();
                        int dept_id_check=employee.checkDept_ID(dept_id);
                        if(dept_id_check==0){
                            employee.newEmployee(emp_id,name,address,city,ph_no,Salary,dept_id);
                            System.out.println("\n\nPress any number to go back\n\n");
                            scanner.nextInt();
                        }else{
                            System.out.println(dept_id_check);
                        }

                    }else if(userInput==2){
                        System.out.println("Enter the employee id you want to change: ");
                        String emp_id= scanner.next();
//                        employee.getIndividualInfo(emp_id);
                        if(employee.getIndividualInfo(emp_id)!=0){
                            System.out.println("\n\nInvalid employee id "+emp_id+ " check the employee id and try again!!\n\n");
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }else{
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
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();

                        }else if(user_input==2){
                            System.out.println("Enter the updated Address : ");
                            String address= scanner.next();
                            employee.updateName(emp_id,address);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }else if(user_input==3){
                            System.out.println("Enter the new phn_no: ");
                            String ph_no= scanner.next();
                            if(ph_no.length()>10){
                                System.out.println("Phone number should be 10 digits please try again");
                                System.out.println("\n\nPress any number to exit");
                                scanner.nextInt();
                                break;
                            }
                            employee.updateName(emp_id,ph_no);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }else if(user_input==4){
                            System.out.println("Enter city: ");
                            String city= scanner.next();
                            employee.updateCity(emp_id,city);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }else if(user_input==5){
                            System.out.println("Enter updated salary: ");
                            int salary= scanner.nextInt();
                            if(salary<=0){
                                System.out.println("Salary should be greater than 0 please try again");
                                System.out.println("\n\nPress any number to exit");
                                scanner.nextInt();
                                break;
                            }
                            employee.updateSalary(emp_id,salary);
                            System.out.println("Updated data: ");
                            employee.getIndividualInfo(emp_id);
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }else if(user_input==0){break;}}
                        break;
                    }else if(userInput==3){
                        System.out.println("Enter the employee id: ");
                        String emp_id= scanner.next();
                        if(employee.getIndividualInfo(emp_id)!=1){
                            System.out.println("\n\nInvalid input check the employee id and try again!!\n\n");
                        }else{
                            employee.getIndividualInfo(emp_id);
                            System.out.println("Delete Data(y/n) >>> ");
                            String Final_input=scanner.next();
                            System.out.println(Final_input);
                            if(Final_input.equals("y")){
                                employee.deleteData(emp_id);
                                System.out.println("\n\nPress any number to go back");
                                scanner.nextInt();
                            }
                        }
                        break;
                    }else if(userInput==4){
                        System.out.println("Enter the employee id: ");
                        String emp_id= scanner.next();
                        if(employee.getIndividualInfo(emp_id)!=1){
                            System.out.println("\n\nInvalid input check the employee id and try again!!\n\n");
                        }else{
                        System.out.println("\nPress 0 to go back");
                        switch (scanner.nextInt()){
                            case 0:
                                break;
                            default:
                                System.out.println("\n\nInvalid input!!\n\n");
                            }
                        }
                    }else if(userInput==0){break;}
                    break;

                case 3:
                    employee.tableCount();
                    System.out.println();
                    System.out.println("Press any number to go back or 0 to quit");
                    userInput=scanner.nextInt();
                    break;

                case 4:
                    department.CompleteDepartmentInfo();
                    System.out.println("Press any number to Go back or 0 to quit");
                    userInput=scanner.nextInt();
                    System.out.println("\n\nPress any number to go back");
                    scanner.nextInt();
                    break;

                case 5 :
                    System.out.println("Press 0 to quit");
                    System.out.println("Select the dept from list: ");
                    System.out.println();
                    int input;
                    String dept_name=null;
                    System.out.println("1# Add new dept");
                    try{
                        for(int i=1;i<=department.individualDeptname().size();i++){
                            String name=department.individualDeptname().get(i-1);
                            System.out.println((i+1)+"# "+name);
                        }
                        System.out.println("0# Exit");
                        System.out.println();
                        input=scanner.nextInt();
                        if(input==1){
                            System.out.print("Dept name: ");
                            dept_name=scanner.next();
                        }else if(input==0){
                            break;
                        }
                        else{
                            dept_name=department.individualDeptname().get(input-2);
                        }

                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Incorrect input!!");
                        break;
                    }
                    System.out.print("ref_id/dept_id: ");
                    String ref_id=scanner.next();
                    System.out.print("Unique ID: ");
                    String id=scanner.next();
                    System.out.println("No of enrollments: ");
                    int no_of_enrollments=scanner.nextInt();
                    department.assignDepartment(dept_name,ref_id,no_of_enrollments,id);
                    System.out.println("\n\nPress any number to go back");
                    scanner.nextInt();
                    break;
                case 6:
                    System.out.println("Enter ref_if/dept_id: ");
                    String dept_id=scanner.next();
                    if(department.getIndividualDept(dept_id)==0){
                        System.out.println("\n");
                        System.out.println("Invalid input please try again!!\n\n");
                        System.out.println("\n\nPress any number to go back");
                        scanner.nextInt();
                    }else{
                        System.out.println();
                        System.out.println("Delete Data(y/n) >>> ");
                        String Final_input=scanner.next();
                        System.out.println(Final_input);
                        if(Final_input.equals("y")){
                            department.deleteData(dept_id);
                            System.out.println("Data deleted successfully!!");
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }else if(Final_input.equals("n")){
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }else{
                            System.out.println("Invalid input select y or n!!");
                            System.out.println("\n\nPress any number to go back");
                            scanner.nextInt();
                        }
                    }
                    break;

                case 7:
                    System.out.println(department.totalDeptCount());
                    System.out.println("\n\nPress any number to go back");
                    scanner.nextInt();
                case 8:
                    System.out.println("Press 0 to exit");
                    System.out.println("Enter dept name: ");
                    String u_input= scanner.next();
                    if(u_input.equals("0")){
                        break;
                    }else{
                        department.findDeptbyName(u_input);
                        System.out.println("\n\nPress any number to go back");
                        scanner.nextInt();
                    }
                    break;
                case 9:
                    System.out.println("Minimum salary: ");
                    System.out.println("--------------------\n");
                    department.minSalary();
                    System.out.println();
                    System.out.println("Maximum salary: ");
                    System.out.println("---------------------\n");
                    department.maxSalary();
                    System.out.println("\n\n");
                    System.out.println("\n\nPress any number to go back");
                    scanner.nextInt();
                    break;
                case 0:
                    System.out.println("Thank you for using DB");
                    break;
                default:
                    System.out.println("\n\nPlease enter a option between 0-9\n\nPress any number to go back");
                    scanner.nextInt();
                    }
            }catch (InputMismatchException e){
                System.out.println("Invalid input please try again!!");
                break;
            }
            }
        while (userInput !=0);

        }
    }