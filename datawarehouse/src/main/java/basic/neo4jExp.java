package basic;

import RelationAndType.ExpRelations;
import RelationAndType.ExpTypes;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class neo4jExp {
    public static void main(String[] args) {
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService db= dbFactory.newEmbeddedDatabase(new File("D:\\tools\\neo4j\\data\\databases\\speedTest.db"));
        try (Transaction tx = db.beginTx()) {

            Node user1 = db.createNode(ExpTypes.USERS);
            user1.setProperty("name", "John Johnson");
            user1.setProperty("year_of_birth", 1982);
            System.out.println("Create user:"+user1.getId());
            Node user2 = db.createNode(ExpTypes.USERS);
            user2.setProperty("name", "Kate Smith");
            user2.setProperty("locked", true);
            System.out.println("Create user:"+user2.getId());
            Node user3 = db.createNode(ExpTypes.USERS);
            user3.setProperty("name", "Jack Jeffries");
            user3.setProperty("cars_owned", new String[]{"BMW", "Audi"});
            System.out.println("Create user:"+user3.getId());

            Node movie1 = db.createNode(ExpTypes.MOVIES);
            movie1.setProperty("name", "Fargo");
            Node movie2 = db.createNode(ExpTypes.MOVIES);
            movie2.setProperty("name", "Alien");
            Node movie3 = db.createNode(ExpTypes.MOVIES);
            movie3.setProperty("name", "Heat");

            user1.createRelationshipTo(user2, ExpRelations.IS_FRIEND_OF);
            user1.createRelationshipTo(user3, ExpRelations.IS_FRIEND_OF);

            Relationship rel1 = user1.createRelationshipTo(movie1, ExpRelations.HAS_SEEN);
            rel1.setProperty("starts", 5);
            Relationship rel2 = user2.createRelationshipTo(movie3, ExpRelations.HAS_SEEN);
            rel2.setProperty("starts", 3);
            Relationship rel3 = user3.createRelationshipTo(movie1, ExpRelations.HAS_SEEN);
            rel3.setProperty("starts", 4);
            Relationship rel4 = user3.createRelationshipTo(movie2, ExpRelations.HAS_SEEN);
            rel4.setProperty("starts", 5);

//            Random random = new Random(System.currentTimeMillis());
//            long tic = System.currentTimeMillis();
//            ArrayList<Node> users = new ArrayList<>();
//            for(int i =0; i<100000; ++i){
//                Node user = db.createNode(ExpTypes.USERS);
//                users.add(user);
//            }
//            for(int i =0; i< 99999; ++i){
//                Node user = users.get(i);
//                for(int j=0; j<10; ++j){
//                    Node targetuser = users.get(random.nextInt(100000-i-1)+i+1);
//                    user.createRelationshipTo(targetuser, ExpRelations.IS_FRIEND_OF);
//                }
//            }
//            long toc = System.currentTimeMillis();
//            System.out.println(toc-tic);

            tx.success();
        }
        System.out.println("Done successfully");
    }
}
