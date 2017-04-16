package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oahnus on 2017/4/1
 * 22:02.
 */
public class MongoDBTest {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;
    // 连接数量
    private final static int POOLSIZE = 100;
    // 等待队列长度
    private final static int BLOCKSIZE = 100;

    public static void main(String[] args) {
        MongoClient client = new MongoClient(HOST, PORT);
        MongoDatabase database = client.getDatabase("mytest");
//        database.createCollection("test");
        MongoCollection<Document> collection = database.getCollection("col");

        FindIterable<Document> findIterable = collection.find();
        for (Document aFindIterable : findIterable) {
            System.out.println(aFindIterable);
        }

        // 获取collection
//        database.createCollection("students");
        collection = database.getCollection("students");
        Document document = new Document("studentId","1341901120")
                .append("name","李狗蛋")
                .append("profession","计算机科学与技术");

        // 插入
        List<Document> documents = new ArrayList<>();
        documents.add(document);
//        collection.insertOne(document);

        // 条件更新
//        collection.updateMany(Filters.eq("name", "李狗蛋"),
//                new Document("$set",new Document("studentId","1341901120")
//                .append("name","李狗蛋")
//                .append("profession","计科")));

        System.out.println();
        findIterable = collection.find(Filters.eq("name","李狗蛋"));
        for (Document aFindIterable : findIterable) {
            System.out.println(aFindIterable);
        }


    }
}
