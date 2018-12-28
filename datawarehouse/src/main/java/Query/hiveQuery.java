package Query;

import Global.Results;
import basic.hiveConn;

public class hiveQuery implements abstractQuery {
    /* *************************************************************************************/
    /*--------------------------------------------------------------------------------------
         所有接口需要使用System.currentTimeMillis()记录运行时间，然后修改Results里相应的时间
     -------------------------------------------------------------------------------------*/
    /* ************************************************************************************/
    private hiveConn hive = new hiveConn();
    private String sql = "";
    @Override
    public String showAllMovies(String arg) {
        sql = "select * from movies";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String showAllDirectors(String arg) {
        sql = "select * from directors";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String showAllActors(String arg) {
        sql = "select * from actors";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String showAllMovieTypes(String arg) {
        sql = "select * from movieTypes";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String ymMovieNum(String arg) {
        sql = "select count(*) from movies where release_time like'%"+arg+"%'";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String movieEditionNum(String arg) {
        sql = "select m.name,count(*) from movies as m,hasEdition as h where m.id = h.movieId and m.name = '"+arg+"' group by m.name";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String movieByDirectorNum(String arg) {
        sql = "select d.name,count(*) from directors as d,direct as dd where d.id = dd.directorId and d.name = '"+arg+"' group by d.name";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String movieMainByActorNum(String arg) {
        sql = "select a.name,count(*) from actors as a,act as aa where a.id = aa.actorId and a.name = '"+arg+"' and aa.isStar = 'TRUE' group by a.name";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String movieByActorNum(String arg) {
        sql = "select a.name,count(*) from actors as a,act as aa where a.id = aa.actorId and a.name = '"+arg+"' group by a.name";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String typeNum(String arg) {
        sql = "select count(*) from hasGenre where genreName = '"+arg+"'";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String frequentActors(String arg) {
        sql = "select name from actors where id in " +
                "(SELECT actorId FROM  (select actorId,movieId from act where movieId in " +
                "(select movieId from act where actorId in (select id from actors where name='"+arg+"'))) " +
                "as temp group by actorId having count(movieId)>2) ";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String frequentDirectors(String arg) {
        sql = "select name from directors where id in " +
                "(SELECT directorId FROM  (select directorId,movieId from direct where movieId in " +
                "(select movieId from act where actorId in (select id from actors where name='"+arg+"'))) " +
                "as temp group by directorId having count(movieId)>2) ";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String typeBestTime(String arg) {
        sql = "select month, max(sum_num) from " +
                "(select MONTH(release_time) as month,sum(review_num) as sum_num from movies where id in " +
                "(select movieId from hasGenre where genreName ='"+arg+"') " +
                "group by MONTH(release_time)) as temp " +
                "group by month";
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }

    @Override
    public String bestDurationBetween2Types(String arg) {
        Results.HiveTime = hive.getQuery(sql);
        return null;
    }
}
