package RelationAndType;

import org.neo4j.graphdb.RelationshipType;

public enum ExpRelations implements RelationshipType {
    IS_FRIEND_OF, HAS_SEEN;
}
