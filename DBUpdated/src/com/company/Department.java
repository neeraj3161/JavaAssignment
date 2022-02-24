package com.company;

import java.sql.*;

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
            statement=connection.createStatement();
            resultSet= statement.executeQuery("Select name,dept_name,ref_id,no_of_enrollments,id from employee join department on employee.dept_id = department.ref_id");
//        using PrintStream.format() method
            System.out.println();
            System.out.format("%7s %15s %10s %25s %18s \n","Emp_name","Dept_name","ref_id","no_of_enrollments","id");
            System.out.println("----------------------------------------------------------------------------------------------");

            while(resultSet.next()){
                System.out.format("%7s %17s %15s %12s %26s \n",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
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

    public void getIndividualDept(String ref_id){
        try{
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

            }
            System.out.println();
            System.out.println();

        }
        catch(SQLException e){
            System.out.println("Something went wrong!!");
        }
    }
}