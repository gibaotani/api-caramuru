package memberDao;
import Object.membro;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.Gson;
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

    //criar classe de exceção para o tratamento da validação

    public  String validateMembro(String CPF)throws ExecutionException , InterruptedException{
        CollectionReference membros = db.collection("membro");
        Query query = membros.whereEqualTo("CPF", CPF);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        if(querySnapshot != null){
            return "1";
        }else{
            return "0";
        }
    }

    public String addMembroDB(membro membro) throws ExecutionException, InterruptedException {

        if(validateMembro(membro.getCPF())=="0"){
            Map<String, Object> docData = new HashMap<>();
            docData.put("Nome", membro.getNome());
            docData.put("CPF", membro.getCPF());
            docData.put("Registro_UEB", membro.getRegistro_UEB());
            docData.put("Link_foto", membro.getLink_foto());
            docData.put("Date",membro.getDate());
            ApiFuture<WriteResult> future = db.collection("membro").document(membro.getCPF()).set(docData);
            System.out.println("Update time: "+ future.get().getUpdateTime());
            Gson gson = new Gson();
            String json= gson.toJson(docData);
            return json;
        }if(validateMembro(membro.getCPF())=="1"){
            System.out.println("Usuario ja cadastrado");
        }
            return null;
    }

    public  void updateMembroDB(membro membro)throws ExecutionException, InterruptedException{



    }


    //Deleta registro de um usuario com base no CPF
    public  String deleteMembroDB(String CPF)throws ExecutionException, InterruptedException{

        if(validateMembro(CPF)=="0"){

            ApiFuture<WriteResult> writeResult = db.collection("membro").document("CPF").delete();

            System.out.println("Update time:  "+writeResult.get().getUpdateTime());

            return "Usuario deletado com sucesso";

        }if(validateMembro(CPF)=="1"){
            return "Usuario não encontrado";
        }else {
            return "Erro não indentificado";
        }
    }

    //le um membro no CloudFIrestore e retorna como JSON em caso de sucesso na operação
    public String readMembroDB(String CPF)throws ExecutionException, InterruptedException{
        membro membro;

        if(validateMembro(CPF)=="0"){

            DocumentReference docRef = db.collection("membro").document("CPF");
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            membro = document.toObject(membro.class);

            Map<String, Object> docData = new HashMap<>();
            docData.put("Nome", membro.getNome());
            docData.put("CPF", membro.getCPF());
            docData.put("Registro_UEB", membro.getRegistro_UEB());
            docData.put("Link_foto", membro.getLink_foto());
            docData.put("Date",membro.getDate());

            Gson gson = new Gson();
            String json= gson.toJson(docData);
            return json;

        }if(validateMembro(CPF)=="1"){
            return "Usuario não encontrado";
        }else{
            return "Erro não indentificado";
        }
    }


    public Firestore getDb() {
        return db;
    }

    public void setDb(Firestore db) {
        this.db = db;
    }

}
