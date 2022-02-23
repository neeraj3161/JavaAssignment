package com.company;
import org.postgresql.gss.GSSOutputStream;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;


public class Employee extends DBConnect{
    public void viewTable() throws SQLException {
        startDb();
        stmt = c.createStatement();
        String statement = "select * from employee";
        rs = stmt.executeQuery(statement);
        ResultSetMetaData rsMeta=rs.getMetaData();
//        This will give the number of columns
        int count=rsMeta.getColumnCount();
//        To get no of emp
        int empNo=0;
        System.out.println();
        while (rs.next()) {
            for(int i=1;i<=count;i++){
                System.out.print(rsMeta.getColumnName(i));
                System.out.print(" : "+rs.getString(i));
                System.out.println();
            }
            System.out.println();
            empNo++;
        }
        System.out.println();
        System.out.println("Total no of employees : "+empNo);
        System.out.println();
        stopDB();
    }

    public void addEmployee(String emp_id,String name,String address,String city,String ph_no,int salary,String dept_id){
        startDb();
        try{
            stmt=c.createStatement();
//            was getting error in the normal execution so created a prepare statement
            PreparedStatement ps = c.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");
            ps.setString(1,emp_id);
            ps.setString(2,name);
            ps.setString(3,address);
            ps.setString(4,city);
            ps.setString(5,ph_no);
            ps.setInt(6,salary);
            ps.setString(7,dept_id);
            stmt.executeUpdate(String.valueOf(ps));
            System.out.println("Data updated successfully!!");
        }catch (SQLException e){
            System.out.println("There was some error inserting data!!");
            e.printStackTrace();
        }
    }

//    Deleting the data using where clause

    public  void deleteEmploye(String name){
        startDb();
        try{

            stmt=c.createStatement();
            PreparedStatement ps = c.prepareStatement("Delete from employee where name =?");
            ps.setString(1,name);
            System.out.println("Employe "+name+" deleted from the data!!");
            stmt.executeUpdate(String.valueOf(ps));

        }

        catch(SQLException e){
            System.out.println("Some Error occurred!!");
            e.printStackTrace();
        }
    }

    public void updateEmployeeName(String value,String condition){
        try{
            startDb();
            stmt=c.createStatement();
            PreparedStatement ps = c.prepareStatement("Update employee set name =  ?"
                    +"Where name = ?");
            System.out.println(String.valueOf(ps));
            ps.setString(1,value);
            ps.setString(2,condition);
//            convert this prepared statement into the string value
            stmt.executeUpdate(String.valueOf(ps));
            System.out.println("Data Updated Successfully!!");
            System.out.println();
            stopDB();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}