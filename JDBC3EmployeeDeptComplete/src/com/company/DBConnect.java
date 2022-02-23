package com.company;
import java.sql.*;

import java.sql.Connection;

public class DBConnect {
    private String url="jdbc:postgresql://localhost:5432/";
    private String info = "postgres";
    private String pass ="aaa@111";

    public Connection c=null;
    public Statement stmt = null;
    public ResultSet rs = null;

    public void startDb(){
        try{
            c=DriverManager.getConnection(url,info,pass);
            System.out.println("Database started");
        }catch (SQLException e){
            System.out.println("Some error occurred while connecting DB!!");
        }
    }

    public void stopDB() throws SQLException {
        c.close();
        System.out.println("Data base stopped!!");
    }


}