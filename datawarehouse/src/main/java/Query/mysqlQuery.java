package Query;
import Global.Results;

import java.sql.*;
import key.key;

public class mysqlQuery implements abstractQuery {
    /* *************************************************************************************/
    /*--------------------------------------------------------------------------------------
         所有接口需要使用System.currentTimeMillis()记录运行时间，然后修改Results里相应的时间
     -------------------------------------------------------------------------------------*/
    /* ************************************************************************************/
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/moviedata";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = key.mysqlUserName;
    static final String PASS = key.mysqlPassword;

    public int recordTime;
    public static String result=null;
    public static Connection conn = null;
    public static Statement stmt = null;
    @Override
    public String showAllMovies(String arg) {
        result="\n";
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT name FROM movies limit 1000";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                //String id  = rs.getString("id");
                result = result+rs.getString("name")+"\n";
                //result=result+rs.getString("name")+" ";
                // 输出数据
//                System.out.print("ID: " + id);
//                System.out.print(", 电影名称: " + name);
//                System.out.print("\n");
            }
            System.out.print(result);
            // 完成后关闭
            rs.close();
//            stmt.close();
//            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

//查询所有导演
    @Override
    public String showAllDirectors(String arg) {
        result="\n";
        try{
            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT name FROM directors limit 1000";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result = result+rs.getString("name")+"\n";
            }
            System.out.print(result);
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }
//查找所有演员
    @Override
    public String showAllActors(String arg) {
        result="\n";
        try{
            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT name FROM actors limit 1000";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result = result+rs.getString("name")+"\n";
            }
            System.out.print(result);
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    //查询所有类别
    @Override
    public String showAllMovieTypes(String arg) {
        result=null;
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT genreName FROM genres";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= result+rs.getString("genreName")+"\n";
                System.out.print(result);
            }
            System.out.print(result);
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    @Override
    public String ymMovieNum(String arg) {
        result=null;
        String month;
        String year=arg.substring(0,4);
        if(arg.length()==6)
        {
           month = arg.substring(5, 6);
        }
        else
        {
            month = arg.substring(5, 7);
        }

        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "select count(*) as countnum from movies where year(release_time)="+year+" and month (release_time)="+month;
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= rs.getString("countnum")+"\n";
                System.out.print(result);
            }
            System.out.print(result);
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    @Override
    public String movieEditionNum(String arg) {
        result=null;
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT count(format_name) as countnum FROM hasedition where movie_id in (select id from movies where name='"+arg+"')";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= rs.getString("countnum")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    @Override
    public String movieByDirectorNum(String arg) {

        result=null;
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT count(movieId) as countnum FROM direct where directorId in (select id from directors where name='"+arg+"')";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= rs.getString("countnum")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    @Override
    public String movieMainByActorNum(String arg) {

        result=null;
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT count(movieId) as countnum FROM act where isStar='TRUE' and actorId in (select id from actors where name='"+arg+"')";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= rs.getString("countnum")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    //某演员参演电影多少部
    @Override
    public String movieByActorNum(String arg) {

        result=null;
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT count(movieId) as countnum FROM act where  actorId in (select id from actors where name='"+arg+"')";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= rs.getString("countnum")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    //某类型电影多少部
    @Override
    public String typeNum(String arg) {
        result=null;
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "SELECT count(movieId) as countnum FROM hasGenres where  genreName='"+ arg+"'";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= rs.getString("countnum")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;

    }

    //xxx演员经常和那些演员合作
    @Override
    public String frequentActors(String arg) {

        result="\n";
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            sql = "select name from actors where id in (SELECT actorId FROM  (select actorId,movieId from act where movieId in (select movieId from act where actorId in (select id from actors where name='"+arg+"'))) as table1 group by actorId having count(movieId)>2)  ";
            //sql = "SELECT actorId FROM  (select actorId,movieId from act where movieId in (select movieId from act where actorId in (select id from actors where name='James Flavin'))) as table1 group by actorId having count(movieId)>2  ";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= result+rs.getString("name")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }

    //演员经常和哪些导演合作
    @Override
    public String frequentDirectors(String arg) {
        result="\n";
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
           // sql = "select name from actors where id in (SELECT actorId FROM  (select actorId,movieId from act where movieId in (select movieId from act where actorId in (select id from actors where name='"+arg+"'))) as table1 group by actorId having count(movieId)>2)  ";
            sql="select name from directors where id in (select directorId from (select directorId, movieId from direct where movieId in (select movieId from act where actorId in (select id from actors where name='"+arg+"'))) as table1 group by directorId having count(movieId)>1)";
            //sql = "SELECT actorId FROM  (select actorId,movieId from act where movieId in (select movieId from act where actorId in (select id from actors where name='James Flavin'))) as table1 group by actorId having count(movieId)>2  ";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= result+rs.getString("name")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;

    }

    @Override
    public String typeBestTime(String arg) {
        result="\n";
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;

            sql="select period from (select period,count(id) as movieInMonth from (select month(release_time) as  period,id  from movies where id in (select movieId from hasGenres where genreName='"+arg+"') and review_num>0) as table1 group by period) as table2 where movieInMonth=" +
                    "(select max(movieInMonth) from (select period,count(id) as movieInMonth from (select month(release_time) as  period,id  from movies where id in (select movieId from hasGenres where genreName='"+arg+"') and review_num>0) as table3 group by period)as table3)";

            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= rs.getString("period")+"月\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;

    }

    @Override
    public String bestDurationBetween2Types(String arg) {

        result="\n";
        try{

            //开始计时
            recordTime=(int)System.currentTimeMillis();

            String sql;
            // sql = "select name from actors where id in (SELECT actorId FROM  (select actorId,movieId from act where movieId in (select movieId from act where actorId in (select id from actors where name='"+arg+"'))) as table1 group by actorId having count(movieId)>2)  ";
            sql="select name from directors where id in (select directorId from (select directorId, movieId from direct where movieId in (select movieId from act where actorId in (select id from actors where name='"+arg+"'))) as table1 group by directorId having count(movieId)>1)";
            //sql = "SELECT actorId FROM  (select actorId,movieId from act where movieId in (select movieId from act where actorId in (select id from actors where name='James Flavin'))) as table1 group by actorId having count(movieId)>2  ";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                result= result+rs.getString("name")+"\n";
                System.out.print(result);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        //结束计时
        Results.MySQLTime=(int)System.currentTimeMillis()-recordTime;
        return result;
    }
}
