
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.print.attribute.standard.DocumentName;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import java.util.Arrays;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import memberDao.membroDao;

import java.util.Arrays;
import org.bson.Document;


@SuppressWarnings("serial")
@WebServlet(name = "HammerServlet", value="/")

public class HammerServlet  extends HttpServlet{
    membroDao membroDao;


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException   {
        PrintWriter out = resp.getWriter();

        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("api-caramuru")
                        .build();
        Firestore db = firestoreOptions.getService();
        ApiFuture<QuerySnapshot> query  = db.collection("membro").orderBy("Nome").get();

        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents =querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents){
                out.println("Nome  "+ document.getString("Nome"));
                out.println("REGISTRO UEB  "+ document.get("Registro_UEB"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
