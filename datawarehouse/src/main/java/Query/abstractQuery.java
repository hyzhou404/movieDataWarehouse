package Query;

public interface abstractQuery {
    String showAllMovies(String arg);
    String showAllDirectors(String arg);
    String showAllActors(String arg);
    String showAllMovieTypes(String arg);
    String ymMovieNum(String arg);
    String movieEditionNum(String arg);
    String movieByDirectorNum(String arg);
    String movieMainByActorNum(String arg);
    String movieByActorNum(String arg);
    String typeNum(String arg);
    String frequentActors(String arg);
    String frequentDirectors(String arg);
    String typeBestTime(String arg);
    String bestDurationBetween2Types(String arg);
}
