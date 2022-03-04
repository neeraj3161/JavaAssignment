package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Department extends DBConnect{
//    no of enrollment should be common for subjects per year
    private int Mechatronics = 98;
    private int Physics = 52;

    public int getMechatronics() {
        return Mechatronics;
    }

    public int getPhysics() {
        return Physics;
    }

    public int getAutomobiles() {
        return Automobiles;
    }

    public int getChemistry() {
        return Chemistry;
    }

    public int getMathematics() {
        return Mathematics;
    }

    public int getElectronics() {
        return Electronics;
    }

    public int getBiology() {
        return Biology;
    }

    private int Automobiles = 95;
    private int Chemistry = 88;
    private int Mathematics = 120;
    private int Electronics = 134;
    private int Biology = 120;
    private int IT = 145;

    //    Field
    private Connection connection = getConnection();
    private Statement statement = getStatement();
    private ResultSet resultSet = null;

    public Department() throws SQLException {
    }

    public void CompleteDepartmentInfo(){
        try{
            String docLength=null;
            statement=connection.createStatement();
            resultSet= statement.executeQuery("Select name,dept_name,ref_id,no_of_enrollments,id from employee join department on employee.dept_id = department.ref_id order by ref_id");
//        using PrintStream.format() method
            System.out.println();
            System.out.format("%7s %15s %10s %25s %18s \n","Emp_name","Dept_name","ref_id","no_of_enrollments","id");
            System.out.println("----------------------------------------------------------------------------------------------");

            while(resultSet.next()){
                System.out.format("%7s %17s %15s %12s %26s \n",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                docLength= resultSet.getString(1);
            }
            System.out.println();
            System.out.println();
        }catch(SQLException e){
            System.out.println("Something went wrong!!");
            e.printStackTrace();
        }
    }

    public void assignDepartment(String department_name,String dept_id_ref_id,int no_of_enrollment,String unique_id){
        try{
            if(department_name=="Physics"){
                no_of_enrollment=this.Physics;
            }else if(department_name=="Mechatronics"){
                no_of_enrollment=this.Mechatronics;
            }else if(department_name.equals("IT")){
                no_of_enrollment=this.IT;
            }else if(department_name=="Automobiles"){
                no_of_enrollment=this.Automobiles;
            }else if(department_name=="Biology"){
                no_of_enrollment=this.Biology;
            }else if(department_name=="Chemistry"){
                no_of_enrollment=this.Chemistry;
            }else if(department_name=="Electronics"){
                no_of_enrollment=this.Electronics;
            }else if(department_name=="Mathematics"){
                no_of_enrollment=this.Mathematics;
            }
            statement=connection.createStatement();
            PreparedStatement preparedStatement= connection.prepareStatement("Insert into department values(?,?,?,?)");
            preparedStatement.setString(1,department_name);
            preparedStatement.setString(2,dept_id_ref_id);
            preparedStatement.setInt(3,no_of_enrollment);
            preparedStatement.setString(4,unique_id);
            statement.executeUpdate(String.valueOf(preparedStatement));
            System.out.println("Data added successfully!!");
        }
        catch(SQLException e){
            System.out.println("Something went wrong check ref_id or unique id!!");
        }
    }

    public int get_noOfEnrollments(String dept_name) {
        int data = 0;
        try {
            statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("Select no_of_enrollments from department AS Employee_details where dept_name = ?");
            preparedStatement.setString(1, dept_name);
            ResultSet resultSet = statement.executeQuery(String.valueOf(preparedStatement));
            while (resultSet.next()) {
                int result = (resultSet.getInt(1));
                if (result > 0) {
                    data = 1;
                }else{
                    data=0;
                    System.out.println("Invalid input!!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong!!");
        }
        return data;
    }


    public void deleteData(String ref_id) {
        try{
            statement=connection.createStatement();
            PreparedStatement preparedStatement= connection.prepareStatement("Delete from department where ref_id = ?");
            preparedStatement.setString(1,ref_id);
            statement.executeUpdate(String.valueOf(preparedStatement));
            System.out.println("Record deleted successfully!!");
        }
        catch(SQLException e){
            System.out.println("Something went wrong!!");
        }
    }

    public int getIndividualDept(String ref_id){
        try{
            String docLength=null;
            statement=connection.createStatement();
            PreparedStatement preparedStatement= connection.prepareStatement("Select * from department where ref_id = ?");
            preparedStatement.setString(1,ref_id);
            ResultSet resultSet= statement.executeQuery(String.valueOf(preparedStatement));

            System.out.println("Employee details");
            System.out.println();
            System.out.format(" %7s %15s %12s %12s\n","dept_name","ref_id","no_of_enrollments","id");
            System.out.println("-------------------------------------------------------------------------------------------------");
            while(resultSet.next()){
                System.out.println();
                System.out.format("%7s %18s %12s %14s\n",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                docLength= resultSet.getString(1);
            }
            System.out.println(docLength.length());
            System.out.println();
            System.out.println();
        }
        catch(SQLException e){
            System.out.println("Something went wrong!!");
        }catch(NullPointerException e){
            return 0;
        }
        return 1;
    }

    public int totalDeptCount(){
        int num=0;
        try{
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select dept_name from department group by dept_name");
//            ResultSet count=statement.executeQuery("select count(dept_name) from department group by dept_name");
//
            System.out.println();
            System.out.println();
            while(resultSet.next()){
//                System.out.format("%5s %7s\n",num+1,resultSet.getString(1));
                num++;
            }
            System.out.println();
            System.out.println("Total no of departments : "+num);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return num;
    }

    public ArrayList<String> individualDeptname() {
        ArrayList<String> dept_names= new ArrayList<String>();
        String individual_names;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select dept_name from department group by dept_name");
            int i=0;
            while (resultSet.next()) {
                individual_names = resultSet.getString(1);
                dept_names.add(i,individual_names);
                i++;
            }
//            System.out.println(dept_names);


        } catch (SQLException e) {
            System.out.println("Something went wrong!!");
        }catch (IndexOutOfBoundsException e){
            System.out.println("Incorrect Input!!");
        }
        return dept_names;
    }


    public void findDeptbyName(String dept_name){
        try{
            String docLength=null;
            statement= connection.createStatement();
            PreparedStatement preparedStatement= connection.prepareStatement("Select emp_id,name,dept_name,no_of_enrollments from department join employee on employee.dept_id=department.ref_id where dept_name= ?");
            preparedStatement.setString(1,dept_name);
            ResultSet resultSet=statement.executeQuery(String.valueOf(preparedStatement));
            System.out.format("%7s %10s %11s %15s\n","Emp_id" ,"name","dept_name","no_of_enrollments");
            System.out.println();
            while(resultSet.next()){
                System.out.format("%7s %10s %11s %12s\n",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                docLength=resultSet.getString(1);
            }
            System.out.println(docLength.length());
        }catch(SQLException e){
            System.out.println("Something went wrong please try again!!");;
        }catch(NullPointerException e){
            System.out.println("\n\nInvalid input please try again!!\n\n");
        }
    }

    public void minSalary(){
        try{
            statement= connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select name,salary,emp_id from employee where salary= (select min(salary)from employee)");
            while(resultSet.next()){
                System.out.println("Name: "+resultSet.getString(1));
                System.out.println("Salary: "+resultSet.getString(2));
                System.out.println("Emp ID: "+resultSet.getString(3));
            }

        }catch(SQLException e){
            System.out.println("Something went wrong!!");
        }
    }

    public void maxSalary(){
        try{
            statement= connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select name,salary,emp_id from employee where salary= (select max(salary)from employee)");
            while(resultSet.next()){
                System.out.println("Name: "+resultSet.getString(1));
                System.out.println("Salary: "+resultSet.getString(2));
                System.out.println("Emp ID: "+resultSet.getString(3));

            }

        }catch(SQLException e){
            System.out.println("Something went wrong!!");
        }
    }

}