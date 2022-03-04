package com.company;
import java.sql.*;

public class Employee extends DBConnect{
//    Field
    private Connection connection = getConnection();
    private Statement statement = getStatement();
    private ResultSet resultSet = null;
    private int individualEmpoyeDate;

    //    Constructor for the class

    public Employee() throws SQLException {
    }
    //    Retrieving complete employee data
    public void completeEmpData() throws SQLException {
        try{
            statement=connection.createStatement();
            resultSet= statement.executeQuery("Select * from employee order by emp_id");
//        using PrintStream.format() method
            System.out.println();
            System.out.format("%7s %14s %10s %7s %15s %12s %12s\n","Employee ID","name","Address","City","phone number","Salary","Department ID");
            System.out.println("-------------------------------------------------------------------------------------------------");

            while(resultSet.next()){
                System.out.format("%7s %14s %10s %7s %18s %12s %14s\n",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }
        }catch(SQLException e){
            System.out.println("There's some issues connecting to the server!!");
        }
    }

    public int tableCount() throws SQLException{
            statement=connection.createStatement();
            int count=0;
            ResultSet resultSet=statement.executeQuery("Select count(*) from employee");
            while(resultSet.next()){
                System.out.println("Total no of employees : "+resultSet.getInt(1));
                count= resultSet.getInt(1);
            }
            return count;
    }

    public int checkDept_ID(String Dept_id){
        int error_code=0;
        try{
        statement=connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where dept_id = ?");
        preparedStatement.setString(1,Dept_id);
        ResultSet resultSet=statement.executeQuery(String.valueOf(preparedStatement));
    }catch(SQLException e){
        System.out.println("Something went wrong. Please check the department id: "+Dept_id+" and try again!!");

            error_code=1;
        }
        return error_code;
    }

    //    Add new employee

    public void newEmployee(String emp_id,String name,String address,String city,String ph_no,int salary,String dept_id) {
        try{
        statement=connection.createStatement();
        PreparedStatement preparedStatement= connection.prepareStatement("Insert into employee values(?,?,?,?,?,?,?)");
        preparedStatement.setString(1,emp_id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,address);
        preparedStatement.setString(4,city);
        preparedStatement.setString(5,ph_no);
        preparedStatement.setInt(6,salary);
        preparedStatement.setString(7,dept_id);
        statement.executeUpdate(String.valueOf(preparedStatement));
        System.out.println("Data added successfully!!");
    }
        catch(SQLException e){
        System.out.println("\n\nSomething went wrong. Please check the Employee/Department id: "+emp_id+"/"+dept_id+" and try again!!\n\n");
    }
    }

    public void deleteData(String emp_id) {
        try{
        statement=connection.createStatement();
        PreparedStatement preparedStatement= connection.prepareStatement("Delete from employee where emp_id = ?");
        preparedStatement.setString(1,emp_id);
        statement.executeUpdate(String.valueOf(preparedStatement));
        System.out.println("Record deleted successfully!!");
    }
        catch(SQLException e){
        System.out.println("Something went wrong!!");
        System.out.println("Please check and delete the department data first!!");
    }
    }

    public int getIndividualInfo(String emp_id) {
       try {
           statement = connection.createStatement();
           PreparedStatement preparedStatement = connection.prepareStatement("Select * from employee AS Employee_details where emp_id = ?");
           preparedStatement.setString(1, emp_id);
           ResultSet resultSet = statement.executeQuery(String.valueOf(preparedStatement));

           System.out.println("Employee details");
           System.out.println();
           System.out.format("%7s %14s %10s %7s %15s %12s %12s\n", "Employee ID", "name", "Address", "City", "phone number", "Salary", "Department ID");
           System.out.println("-------------------------------------------------------------------------------------------------");
           if (resultSet.next()) {
               System.out.println();
                   System.out.format("%7s %14s %10s %7s %18s %12s %14s\n", resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
           }else{
               individualEmpoyeDate=1;
           }
           System.out.println();
           System.out.println();
       }catch(SQLException e){
        System.out.println("Connection went wrong with the Database!!");
    }catch(NullPointerException e){
           System.out.println("No data returned check the employee id: "+emp_id+" and try again!!");
       }
//        System.out.println(individualEmpoyeDate);
        return individualEmpoyeDate;
    }

    public void updateName(String emp_id,String parameter) {
        try {

        statement=connection.createStatement();
        PreparedStatement preparedStatement= connection.prepareStatement("update employee set name = ? where emp_id = ?");
        preparedStatement.setString(1,parameter);
        preparedStatement.setString(2,emp_id);
        statement.executeUpdate(String.valueOf(preparedStatement));

    }
        catch(SQLException e){
            System.out.println("Something went wrong. Please check the employee id: "+emp_id+" and try again!!");
        }
    }

    public void updateAddress(String emp_id,String parameter) {
        try{

        statement=connection.createStatement();
        PreparedStatement preparedStatement= connection.prepareStatement("update employee set address = ? where emp_id = ?");
        preparedStatement.setString(1,parameter);
        preparedStatement.setString(2,emp_id);
        statement.executeUpdate(String.valueOf(preparedStatement));
    }
        catch(SQLException e){
        System.out.println("Something went wrong. Please check the employee id: "+emp_id+" and try again!!");
    }

    }

    public void updateSalary(String emp_id,int parameter) {
        try {


        statement=connection.createStatement();
        PreparedStatement preparedStatement= connection.prepareStatement("update employee set salary = ? where emp_id = ?");
        preparedStatement.setInt(1,parameter);
        preparedStatement.setString(2,emp_id);
        statement.executeUpdate(String.valueOf(preparedStatement));
        }
        catch(SQLException e){
            System.out.println("Something went wrong. Please check the employee id: "+emp_id+" and try again!!");
        }

    }

    public void updateCity(String emp_id,String parameter) {
       try {
           statement = connection.createStatement();
           PreparedStatement preparedStatement = connection.prepareStatement("update employee set city = ? where emp_id = ?");
           preparedStatement.setString(1, parameter);
           preparedStatement.setString(2, emp_id);
           statement.executeUpdate(String.valueOf(preparedStatement));
       }
        catch(SQLException e){

            System.out.println("Something went wrong. Please check the employee id: "+emp_id+" and try again!!");
        }

    }

    public void updatePhno(String emp_id,String parameter) {
        try{
        statement=connection.createStatement();
        PreparedStatement preparedStatement= connection.prepareStatement("update employee set ph_no = ? where emp_id = ?");
        preparedStatement.setString(1,parameter);
        preparedStatement.setString(2,emp_id);
        statement.executeUpdate(String.valueOf(preparedStatement));

        }
        catch(SQLException e){
            System.out.println("Something went wrong. Please check the employee id: "+emp_id+" and try again!!");
        }
    }
}