package com.example.myapplication.util;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    //MySQL 驱动 根据使用的驱动自行调整使用

    //private static String driver = "com.mysql.jdbc.Driver";

    private static String driver = "net.sourceforge.jtds.jdbc.Driver";

    //MYSQL数据库连接Url，此处需要注意根据使用的驱动不同，连接url的方式也会有所差别，本文使用的驱动为JTDS

    //private static String url = "jdbc:mysql://IP:端口号/数据库";

    private static String url = "jdbc:jtds:sqlserver://cdb-awt1o0iq.bj.tencentcdb.com:10228;instanceName=SQLEXPRESS;databaseName=whisper";

    private static String user = "Phineas";//用户名

    private static String password = "zxczxc3932";//密码



    /**

     * 连接数据库

     * */
    public static Connection getConn(){

        Connection conn = null;

        try {

            Class.forName(driver);//获取MYSQL驱动

            conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
            Log.d("------", "getConn: succeed");

        }
        catch (Exception e) {
            Log.d("------", "getConn: fail");

            e.printStackTrace();

        }

        return conn;

    }



    /**

     * 关闭数据库

     * */
    public static void closeAll(Connection conn, PreparedStatement ps){

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (ps != null) {

            try {

                ps.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }



    /**

     * 关闭数据库

     * */



    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){

        if (conn != null) {

            try {

                conn.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (ps != null) {

            try {

                ps.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

        if (rs != null) {

            try {

                rs.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

}

