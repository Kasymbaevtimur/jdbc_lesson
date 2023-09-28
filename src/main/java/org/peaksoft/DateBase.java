package org.peaksoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DateBase {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "post";

    public static Connection connection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("Success connected to the database");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }


}
