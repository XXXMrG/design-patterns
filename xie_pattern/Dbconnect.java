package xie;

import java.sql.*;

public class Dbconnect {
    String drivername  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/my?useSSL=false";
    Connection connect = null;
    ResultSet rs = null;
    Statement stmt = null;

    //加载数据库
    public void conn() {
        try {
            Class.forName(drivername);  //加载mysql的驱动程序
        }
        catch(java.lang.ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
    }

    //执行查询语句
    public ResultSet executeQuery(String sql) {
        try {
            connect = DriverManager.getConnection(url,"root","Ln/y&aF37vBnmdt4");
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
        }
        catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    //关闭资源
    public void closeDate() throws SQLException {
        rs.close();
        stmt.close();
        connect.close();
    }


}
