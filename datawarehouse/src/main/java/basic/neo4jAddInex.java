package basic;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

import static RelationAndType.ExpTypes.*;

public class neo4jAddInex {
    private static GraphDatabaseService db;
    private static Boolean hasCreated = false;
    neo4jAddInex(){
        if(!hasCreated) {
            GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
            db = dbFactory.newEmbeddedDatabaseBuilder(new File("C:\\Users\\q8975\\IdeaProjects\\movieDataWarehouse\\datawarehouse\\src\\main\\resources\\data\\movie.db")).newGraphDatabase();
            hasCreated = true;
        }
    }
    void addIndex(Label label, String attribute){
        try(Transaction tx = db.beginTx()){
            db.schema().indexFor(label).on(attribute).create();
            tx.success();
        }
        System.out.println("Done");
    }
}

class index{
    public static void main(String[] args) {
        neo4jAddInex adder = new neo4jAddInex();
        adder.addIndex(LANGUAGE, "languageName");
        adder.addIndex(GENRE, "genreName");
        adder.addIndex(STUDIO, "studioId");
        adder.addIndex(ACTOR, "actorId");
        adder.addIndex(DIRECTOR, "directorId");
    }
}
