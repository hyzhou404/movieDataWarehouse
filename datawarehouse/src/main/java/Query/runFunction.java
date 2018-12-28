package Query;

public class runFunction {
    private static String result;
    private static int argCheck(String arg, int num){
        if(arg.equals("") && num > 0){
            result = "参数不足";
            return -1;
        }
        String[] tmp = arg.split("#");
        if(tmp.length < num){
            result = "参数不足！";
            return -1;
        }
        else if(tmp.length > num){
            result = "参数过多！";
            return 1;
        }
        else{
            return 0;
        }
    }
    public static String run(String function, String arg){
        System.out.println(arg);
        switch (function) {
            case "列出所有的电影":
                new mysqlQuery().showAllMovies(arg);
                result = new neo4jQuery().showAllMovies(arg);
                new influxQuery().showAllMovies(arg);
                new hiveQuery().showAllMovies(arg);
                break;
            case "列出所有的导演":
                new mysqlQuery().showAllDirectors(arg);
                result = new neo4jQuery().showAllDirectors(arg);
                new influxQuery().showAllDirectors(arg);
                new hiveQuery().showAllDirectors(arg);
                break;
            case "列出所有的演员":
                new mysqlQuery().showAllActors(arg);
                result = new neo4jQuery().showAllActors(arg);
                new influxQuery().showAllActors(arg);
                new hiveQuery().showAllActors(arg);
                break;
            case "列出所有的电影类型":
                new mysqlQuery().showAllMovieTypes(arg);
                result = new neo4jQuery().showAllMovieTypes(arg);
                new influxQuery().showAllMovieTypes(arg);
                new hiveQuery().showAllMovieTypes(arg);
                break;
            case "xxxx年xx月有多少部电影":
                if(argCheck(arg, 2)!=0){
                    break;
                }
                new mysqlQuery().ymMovieNum(arg);
                result = new neo4jQuery().ymMovieNum(arg);
                new influxQuery().ymMovieNum(arg);
                new hiveQuery().ymMovieNum(arg);
                break;
            case "xxxx电影有多少版本":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().movieEditionNum(arg);
                result = new neo4jQuery().movieEditionNum(arg);
                new influxQuery().movieEditionNum(arg);
                new hiveQuery().movieEditionNum(arg);
                break;
            case "xxx导演共有多少部电影":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().movieByDirectorNum(arg);
                result = new neo4jQuery().movieByDirectorNum(arg);
                new influxQuery().movieByDirectorNum(arg);
                new hiveQuery().movieByDirectorNum(arg);
                break;
            case "xxx演员主演了多少部电影":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().movieMainByActorNum(arg);
                result = new neo4jQuery().movieMainByActorNum(arg);
                new influxQuery().movieMainByActorNum(arg);
                new hiveQuery().movieMainByActorNum(arg);
                break;
            case "xxx演员参演了多少部电影":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().movieByActorNum(arg);
                result = new neo4jQuery().movieByActorNum(arg);
                new influxQuery().movieByActorNum(arg);
                new hiveQuery().movieByActorNum(arg);
                break;
            case "xxx类型的电影共有多少部":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().typeNum(arg);
                result = new neo4jQuery().typeNum(arg);
                new influxQuery().typeNum(arg);
                new hiveQuery().typeNum(arg);
                break;
            case "xxx演员经常和哪些演员合作":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().frequentActors(arg);
                result = new neo4jQuery().frequentActors(arg);
                new influxQuery().frequentActors(arg);
                new hiveQuery().frequentActors(arg);
                break;
            case "xxx演员经常和哪些导演合作":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().frequentDirectors(arg);
                result = new neo4jQuery().frequentDirectors(arg);
                new influxQuery().frequentDirectors(arg);
                new hiveQuery().frequentDirectors(arg);
                break;
            case "xxx类型电影常见的黄金上映期是什么":
                if(argCheck(arg, 1)!=0){
                    break;
                }
                new mysqlQuery().typeBestTime(arg);
                result = new neo4jQuery().typeBestTime(arg);
                new influxQuery().typeBestTime(arg);
                new hiveQuery().typeBestTime(arg);
                break;
            case "xxx类型电影上映多久后xxx类型电影销售最好":
                if(argCheck(arg, 2)!=0){
                    break;
                }
                new mysqlQuery().bestDurationBetween2Types(arg);
                result = new neo4jQuery().bestDurationBetween2Types(arg);
                new influxQuery().bestDurationBetween2Types(arg);
                new hiveQuery().bestDurationBetween2Types(arg);
                break;

        }
        return result;
    }
}
