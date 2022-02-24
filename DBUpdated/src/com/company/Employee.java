package com.company;
import java.sql.*;

public class Employee extends DBConnect{
//    Field
    private Connection connection = getConnection();
    private Statement statement = getStatement();
    private ResultSet resultSet = null;

    //    Constructor for the class

    public Employee() throws SQLException {
    }
    //    Retrieving complete employee data
    public void completeEmpData() throws SQLException {
        try{
            statement=connection.createStatement();
            resultSet= statement.executeQuery("Select * from employee");
//        using PrintStream.format() method
            System.out.println();
            System.out.format("%7s %14s %10s %7s %15s %12s %12s\n","Emp_ID","Emp_name","Address","City","Ph_no","Salary","Dept_ID");
            System.out.println("-------------------------------------------------------------------------------------------------");

            while(resultSet.next()){
                System.out.format("%7s %14s %10s %7s %18s %12s %14s\n",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }
        }catch(SQLException e){
            System.out.println("Something went wrong!!");
            e.printStackTrace();
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
        statement.executeQuery(String.valueOf(preparedStatement));
    }catch(SQLException e){
            e.printStackTrace();
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
        System.out.println("Something went wrong!!");
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
    }
    }

    public void getIndividualInfo(String emp_id) {
       try{
        statement=connection.createStatement();
        PreparedStatement preparedStatement= connection.prepareStatement("Select * from employee AS Employee_details where emp_id = ?");
        preparedStatement.setString(1,emp_id);
       ResultSet resultSet= statement.executeQuery(String.valueOf(preparedStatement));

        System.out.println("Employee details");
        System.out.println();
        System.out.format("%7s %14s %10s %7s %15s %12s %12s\n","Emp_ID","Emp_name","Address","City","Ph_no","Salary","Dept_ID");
        System.out.println("-------------------------------------------------------------------------------------------------");
       while(resultSet.next()){
           System.out.println();
           System.out.format("%7s %14s %10s %7s %18s %12s %14s\n",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));

       }
        System.out.println();
        System.out.println();

    }
        catch(SQLException e){
        System.out.println("Something went wrong!!");
    }
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
        System.out.println("Something went wrong!!");
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
        System.out.println("Something went wrong!!");
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
            System.out.println("Something went wrong!!");
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

            System.out.println("Something went wrong!!");
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
            System.out.println("Something went wrong!!");
        }
    }
}