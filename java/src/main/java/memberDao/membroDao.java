package memberDao;
import Object.membro;
import com.google.cloud.firestore.Firestore;
import java.io.*;
import java.util.ArrayList;

public class membroDao extends membro {
    private Firestore db;
    private ArrayList<membro> membroList =  new ArrayList<membro>();

    public membroDao() throws IOException {

        membro = new membro();
    }

    public void addMembroList(membro membro){
        membroList.add(membro);
    }
    public membro popMembroList(){
        membroList
    }
    public Firestore getDb() {
        return db;
    }

    public void setDb(Firestore db) {
        this.db = db;
    }



}
