package com.company;

import java.sql.*;

public class DBConnect {
//    Field
    private String url = "jdbc:postgresql://localhost:5432/";
    private String user_id = "postgres";
    private String pass = "aaa@111";
    private Connection connection = DriverManager.getConnection(url,user_id,pass);;
    private Statement statement = null;

    public DBConnect() throws SQLException {
    }


    //    getter for connection
    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
//    Constructor

    public void DBConnect() {
        Connection connection = this.connection;
        System.out.println("Connection Established to DB");
    }

    public void DBClose() throws NullPointerException, SQLException {
        try{
            connection.close();
            System.out.println("DB Connection Closed");
        }catch(NullPointerException e){
            System.out.println();
        }
    }
}