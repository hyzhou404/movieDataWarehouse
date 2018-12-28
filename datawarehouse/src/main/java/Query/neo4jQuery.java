package Query;

import Global.Results;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class neo4jQuery implements abstractQuery {

    private Result exec(String query){
        long tic = System.currentTimeMillis();
        Result executeResult = db.execute(query);
        long toc = System.currentTimeMillis();
        Results.Neo4jTime = (int)(toc-tic);
        return executeResult;
    }

    private String getResStr(Result executeResult, String key){
        StringBuilder res = new StringBuilder();
        HashSet<Object> rows = new HashSet<>();
        while(executeResult.hasNext()){
            Object row = executeResult.next().get(key);
            if(row != null && !rows.contains(row)) {
                rows.add(row);
                res.append(row.toString());
                res.append("\n");
            }
        }
        if (res.toString().equals("")){
            res.append("未查询到结果");
        }
        return res.toString();
    }



    /* *************************************************************************************/
    /*--------------------------------------------------------------------------------------
         所有接口需要使用System.currentTimeMillis()记录运行时间，然后修改Results里相应的时间
     -------------------------------------------------------------------------------------*/
    /* ************************************************************************************/

    private static GraphDatabaseService db;
    private static Boolean hasCreated = false;

    neo4jQuery(){
        if(!hasCreated) {
            GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
            db = dbFactory.newEmbeddedDatabaseBuilder(new File("C:\\Users\\q8975\\IdeaProjects\\movieDataWarehouse\\datawarehouse\\src\\main\\resources\\data\\movie.db")).newGraphDatabase();
            hasCreated = true;
        }
    }

    @Override
    public String showAllMovies(String arg) {
        String query = "MATCH (m:MOVIE) RETURN m.name LIMIT 1000";
        return getResStr(exec(query), "m.name");
    }

    @Override
    public String showAllDirectors(String arg) {
        String query = "MATCH (d:DIRECTOR) RETURN d.name LIMIT 1000";
        return getResStr(exec(query), "d.name");
    }

    @Override
    public String showAllActors(String arg) {
        String query = "MATCH (actor:ACTOR) RETURN actor.name LIMIT 1000";
        return getResStr(exec(query), "actor.name");
    }

    @Override
    public String showAllMovieTypes(String arg) {
        String query = "MATCH (g:GENRE) RETURN g.genreName LIMIT 1000";
        return getResStr(exec(query), "g.genreName");
    }

    @Override
    public String ymMovieNum(String arg) {
        //查询&计时
        String[] tmp = arg.split("#");
        String time = tmp[0].trim()+"/"+tmp[1].trim();
        String query = "MATCH (m:MOVIE) WHERE m.release_time =~ '"+time+".*' RETURN count(*)";
        return String.valueOf(exec(query).next().get("count(*)"));
    }

    @Override
    public String movieEditionNum(String arg) {
        //查询
        String query = "MATCH (:MOVIE{name: '"+arg+"'})-[HAS_EDITION]->(format:FORMAT)"+
                        " RETURN format.formatName";
        Result executeResult = exec(query);
        //生成结果
        StringBuilder res = new StringBuilder();
        HashSet<String> formats = new HashSet<>();
        while(executeResult.hasNext()){
            Object Format = executeResult.next().get("format.formatName");
            if(Format != null && !formats.contains(Format.toString())){
                formats.add(Format.toString());
                res.append(Format.toString());
                res.append("\n");
            }
        }
        return "共有"+formats.size()+"个版本\n"+res.toString();
    }

    @Override
    public String movieByDirectorNum(String arg) {
        String query = "MATCH (:DIRECTOR {name: '"+arg+"'})-[DIRECT]->(m:MOVIE) RETURN count(*)";
        return String.valueOf(exec(query).next().get("count(*)"));
    }

    @Override
    public String movieMainByActorNum(String arg) {
        String query = "MATCH (:ACTOR {name: '"+arg+"'})-[rel:ACT]->(m:MOVIE) " +
                        "WhERE rel.Star = 'TRUE' RETURN count(*)";
        return String.valueOf(exec(query).next().get("count(*)"));
    }

    @Override
    public String movieByActorNum(String arg) {
        String query = "MATCH (:ACTOR {name: '"+arg+"'})-[ACT]->(m:MOVIE) RETURN count(*)";
        return String.valueOf(exec(query).next().get("count(*)"));
    }

    @Override
    public String typeNum(String arg) {
        String query = "MATCH (:GENRE {genreName: '"+arg+"'})<-[HAS_EDITION]-(m:MOVIE)"+
                " RETURN count(*)";
        return String.valueOf(exec(query).next().get("count(*)"));
    }

    @Override
    public String frequentActors(String arg) {
        String query = "MATCH (:ACTOR {name: \""+arg+"\"})-[r1:ACT]->(m:MOVIE)<-[r2:ACT]-(a:ACTOR)\n" +
                            "RETURN a.name";
        Result execResult = exec(query);
        HashMap<String, Integer> actorCount = new HashMap<>();
        while(execResult.hasNext()){
            Object actorName = execResult.next().get("a.name");
            if(actorName!=null){
                String name = actorName.toString();
                if(actorCount.containsKey(name)){
                    Integer count = actorCount.get(name);
                    actorCount.put(name, ++count);
                }
                else{
                    actorCount.put(name, 1);
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Integer> entry : actorCount.entrySet()) {
            if (entry.getValue() >= 3) {
                res.append(entry.getKey());
                res.append("\n");
            }
        }
        if(res.toString().equals("")){
            String tmp = "没有与"+arg+"合作次数大于等于3次的演员";
            res.append(tmp);
        }
        return res.toString();
    }

    @Override
    public String frequentDirectors(String arg) {
        String query = "MATCH (:ACTOR {name: \""+arg+"\"})-[ACT]->()<-[DIRECT]-(d:DIRECTOR)\n" +
                            "RETURN d.name";
        Result execResult = exec(query);
        HashMap<String, Integer> directorCount = new HashMap<>();
        while(execResult.hasNext()){
            Object directorName = execResult.next().get("d.name");
            if(directorName!=null){
                String name = directorName.toString();
                if(directorCount.containsKey(name)){
                    Integer count = directorCount.get(name);
                    directorCount.put(name, ++count);
                }
                else{
                    directorCount.put(name, 0);
                }

            }
        }
        StringBuilder res = new StringBuilder("");
        for (Map.Entry<String, Integer> entry : directorCount.entrySet()) {
            if (entry.getValue() >= 3) {
                res.append(entry.getKey());
                res.append("\n");
            }
        }
        if(res.toString().equals("")){
            String tmp = "没有与"+arg+"合作次数大于等于3次的导演";
            res.append(tmp);
        }
        return res.toString();
    }

    @Override
    public String typeBestTime(String arg) {
        String query = "MATCH (g:GENRE {genreName: '"+arg+"'})<-[HAS_EDITION]-(m:MOVIE)\n" +
                        "WITH toInteger(m.review_num) AS num, m.release_time AS time\n" +
                        "RETURN num, time ORDER BY num DESC";
        Result execResult = exec(query);
        int count = 0;
        StringBuilder res = new StringBuilder();
        HashSet<String> months = new HashSet<>();
        while(execResult.hasNext() && count < 3){
            Object time = execResult.next().get("time");
            if(time!=null){
                String month = time.toString().split("/")[1]+"月\n";
                months.add(month);
                ++count;
            }
        }
        for (String month : months) {
            res.append(month);
        }
        return res.toString();
    }

    @Override
    public String bestDurationBetween2Types(String arg) {
        return null;
    }
}
