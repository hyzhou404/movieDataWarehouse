package RelationAndType;

import org.neo4j.graphdb.Label;

public enum ExpTypes implements Label {
    MOVIE, FORMAT, LANGUAGE, GENRE, STUDIO, ACTOR, DIRECTOR, USERS, MOVIES;
}
