package memberDao;
import Object.membro;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class membroDao extends membro {
    private Firestore db;
    private ArrayList<membro> membroList =  new ArrayList<membro>();

    public membroDao() throws IOException {

        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("api-caramuru")
                        .build();
        db = firestoreOptions.getService();

    }

    public void addMembroList(membro membro){
        membroList.add(membro);
    }
    public membro popMembroList(){
       return membroList.get(0);
    }

    public  String validateMembro(membro membro)throws ExecutionException , InterruptedException{
        CollectionReference membros = db.collection("membro");
        Query query = membros.whereEqualTo("CPF", membro.getCPF());
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        if(querySnapshot != null){
            return "1";
        }else{
            return "0";
        }
    }

    public  void addMembroDB(membro membro) throws ExecutionException, InterruptedException {

        if(validateMembro(membro)=="0"){
            Map<String, Object> docData = new HashMap<>();
            docData.put("Nome", membro.getNome());
            docData.put("CPF", membro.getCPF());
            docData.put("Registro_UEB", membro.getRegistro_UEB());
            docData.put("Link_foto", membro.getLink_foto());
            docData.put("Date",membro.getDate());
            ApiFuture<WriteResult> future = db.collection("membro").document(membro.getCPF()).set(docData);
            System.out.println("Update time: "+ future.get().getUpdateTime());
        }if(validateMembro(membro)=="1"){
            System.out.println("Usuario ja cadastrado");
        }

    }

    public  void updateMembroDB(membro membro)throws ExecutionException, InterruptedException{

        

    }




    public Firestore getDb() {
        return db;
    }

    public void setDb(Firestore db) {
        this.db = db;
    }

}
