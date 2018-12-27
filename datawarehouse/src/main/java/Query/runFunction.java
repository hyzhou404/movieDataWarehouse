package Query;

import Global.Results;

import java.util.Random;

public class runFunction {
    public static String run(String function, String arg){
        String result = "The result for '"+function+"' is: \n .......";;
        System.out.println(arg);
        switch (function) {
            case "列出所有的电影":
                result = new mysqlQuery().showAllMovies(arg);
                new neo4jQuery().showAllMovies(arg);
                new influxQuery().showAllMovies(arg);
                new hiveQuery().showAllMovies(arg);
                break;
            case "列出所有的导演":
                result = new mysqlQuery().showAllDirectors(arg);
                new neo4jQuery().showAllDirectors(arg);
                new influxQuery().showAllDirectors(arg);
                new hiveQuery().showAllDirectors(arg);
                break;
            case "列出所有的演员":
                result = new mysqlQuery().showAllActors(arg);
                new neo4jQuery().showAllActors(arg);
                new influxQuery().showAllActors(arg);
                new hiveQuery().showAllActors(arg);
                break;
            case "列出所有的电影类型":
                result = new mysqlQuery().showAllMovieTypes(arg);
                new neo4jQuery().showAllMovieTypes(arg);
                new influxQuery().showAllMovieTypes(arg);
                new hiveQuery().showAllMovieTypes(arg);
                break;
            case "xxxx年xx月有多少部电影":
                result = new mysqlQuery().ymMovieNum(arg);
                new neo4jQuery().ymMovieNum(arg);
                new influxQuery().ymMovieNum(arg);
                new hiveQuery().ymMovieNum(arg);
                break;
            case "xxxx电影有多少版本":
                result = new mysqlQuery().movieEditionNum(arg);
                new neo4jQuery().movieEditionNum(arg);
                new influxQuery().movieEditionNum(arg);
                new hiveQuery().movieEditionNum(arg);
                break;
            case "xxx导演共有多少部电影":
                result = new mysqlQuery().movieByDirectorNum(arg);
                new neo4jQuery().movieByDirectorNum(arg);
                new influxQuery().movieByDirectorNum(arg);
                new hiveQuery().movieByDirectorNum(arg);
                break;
            case "xxx演员主演了多少部电影":
                result = new mysqlQuery().movieMainByActorNum(arg);
                new neo4jQuery().movieMainByActorNum(arg);
                new influxQuery().movieMainByActorNum(arg);
                new hiveQuery().movieMainByActorNum(arg);
                break;
            case "xxx演员参演了多少部电影":
                result = new mysqlQuery().movieByActorNum(arg);
                new neo4jQuery().movieByActorNum(arg);
                new influxQuery().movieByActorNum(arg);
                new hiveQuery().movieByActorNum(arg);
                break;
            case "xxx类型的电影共有多少部":
                result = new mysqlQuery().typeNum(arg);
                new neo4jQuery().typeNum(arg);
                new influxQuery().typeNum(arg);
                new hiveQuery().typeNum(arg);
                break;
            case "xxx演员经常和哪些演员合作":
                result = new mysqlQuery().frequentActors(arg);
                new neo4jQuery().frequentActors(arg);
                new influxQuery().frequentActors(arg);
                new hiveQuery().frequentActors(arg);
                break;
            case "xxx演员经常和哪些导演合作":
                result = new mysqlQuery().frequentDirectors(arg);
                new neo4jQuery().frequentDirectors(arg);
                new influxQuery().frequentDirectors(arg);
                new hiveQuery().frequentDirectors(arg);
                break;
            case "xxx类型电影常见的黄金上映期是什么":
                result = new mysqlQuery().typeBestTime(arg);
                new neo4jQuery().typeBestTime(arg);
                new influxQuery().typeBestTime(arg);
                new hiveQuery().typeBestTime(arg);
                break;
            case "xxx类型电影上映多久后xxx类型电影销售最好":
                result = new mysqlQuery().bestDurationBetween2Types(arg);
                new neo4jQuery().bestDurationBetween2Types(arg);
                new influxQuery().bestDurationBetween2Types(arg);
                new hiveQuery().bestDurationBetween2Types(arg);
                break;

        }
        return result;
    }
}
