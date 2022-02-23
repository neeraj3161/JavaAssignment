package com.company;
import org.postgresql.gss.GSSOutputStream;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;


public class Dept extends DBConnect{
    public void viewTable() throws SQLException {
        startDb();
        stmt = c.createStatement();
        String statement = "select * from department";
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
        System.out.println("Total no of data in department's table : "+empNo);
        System.out.println();
        stopDB();
    }

    public void addData(String dept_name,String ref_id,int no_of_enrollments,String id){
        startDb();
        try{
            stmt=c.createStatement();
//            was getting error in the normal execution so created a prepare statement
            PreparedStatement ps = c.prepareStatement("insert into department values(?,?,?,?)");
            ps.setString(1,dept_name);
            ps.setString(2,ref_id);
            ps.setInt(3,no_of_enrollments);
            ps.setString(4,id);
            stmt.executeUpdate(String.valueOf(ps));
            System.out.println("Data updated successfully!!");
        }catch (SQLException e){
            System.out.println("There was some error inserting data!!");
            e.printStackTrace();
        }
    }

//    Deleting the data using where clause

    public  void deleteDept(String dept_name){
        startDb();
        try{

            stmt=c.createStatement();
            PreparedStatement ps = c.prepareStatement("Delete from department where dept_name =?");
            ps.setString(1,dept_name);
            System.out.println("Department "+dept_name+" deleted from the data!!");
            stmt.executeUpdate(String.valueOf(ps));

        }

        catch(SQLException e){
            System.out.println("Some Error occurred!!");
            e.printStackTrace();
        }
    }

//    getting join data

    

}