package com.kitri.weatherwear;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlConnTest {

    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String Url = "jdbc:mysql://localhost:3306/weatherwear?serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "1234";

    @Test
    public void testConn() throws Exception{
        Class.forName(Driver);

        try {
            Connection con = DriverManager.getConnection(Url, user, password);
            String outer_wear_name = getClothes(con);
            System.out.println(outer_wear_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getClothes(Connection con) throws Exception{
        String res = null;
        String sqlQuery = "select outer_wear from clothes where wear_code = 3";

        try {
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) res = rs.getString("outer_wear");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

}

