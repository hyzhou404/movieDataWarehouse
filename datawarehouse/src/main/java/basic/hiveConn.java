package basic;

import java.sql.*;

public class hiveConn {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String connectionStr = "jdbc:hive2://192.168.189.128:10000/movie";
    private static String connectionUser = "hive";
    private static String connectionPwd = "hive";
    private Connection connection = null;
    private Statement statement = null;
    private String sql = "";
    private ResultSet res = null;
    private int time = 0;

    public hiveConn() {
    }
    //执行hive的查询
    public int getQuery(String SQL){
        try {
            Class.forName(driverName);
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try{
            //建立连接
            connection = DriverManager.getConnection(connectionStr,connectionUser,connectionPwd);
            statement = connection.createStatement();
            try {
                //进行查询
                sql = SQL;
                Long startTime = System.currentTimeMillis();
                res = statement.executeQuery(sql);
                Long endTime = System.currentTimeMillis();
                Long tempTime = endTime - startTime;
                time = tempTime.intValue();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try{
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return time;
    }
}
