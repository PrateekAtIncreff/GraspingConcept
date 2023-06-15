package org.example;

import java.sql. Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Hello world!");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","prateek", "prateeknith");
        Statement stmt = con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from employee");
        int id=0;
        while(rs.next()) {
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            id = rs.getInt(1);
        }
        id++;
        insert(con, id);
        selecting(con);
        deleting(con);
        selecting(con);
        con.close();
    }

    private static void insert(Connection con,int idx) throws SQLException {
        System.out.println("INSERTING...");
        Statement stmt = con.createStatement();
        for (int i = 0; i < 3; i++) {
            int id = i+idx;
            int age = 30 + i;
            String name = "name " + i;
            stmt.executeUpdate("insert into employee values(" + id + ", '" + name + "', " + age + ")");
        }
    }

    private static void selecting(Connection con) throws SQLException {
        System.out.println("SELECTING....");
        Statement stmt = con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from employee");
        while(rs.next())
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

    }
    private static void deleting(Connection con) throws SQLException{
        System.out.println("Deleting...");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id from employee");
        List<Integer> idlist= new ArrayList<Integer>();
        while (rs.next()) {
            idlist.add(rs.getInt(1));
        }
            for(int i=0;i<idlist.size();i++){
            stmt.executeUpdate("delete from employee where id=" + idlist.get(i));
        }
    }
}