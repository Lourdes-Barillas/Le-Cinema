/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/**
 *
 * @author Lourdes Pérez
 */
public class DataHistory {
    
    public static Firestore td;
    public static void Conectar() throws FileNotFoundException, IOException, InterruptedException, ExecutionException{
        //Thanks to this youtube video: 01.- Conectar firebase con java. Netbeans 11.2: https://youtu.be/DvIFCYJusLc
        
        FileInputStream serviceAccount =
          new FileInputStream("bdcinemadb.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .build();

        FirebaseApp.initializeApp(options);

        td = FirestoreClient.getFirestore();
        System.out.println("Se conecto con exito");
        
        
        //-----------------------------------------------------------------
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("actividadDeNodos").document("");
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Ada");
        data.put("last", "Lovelace");
        data.put("born", 1815);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
    }
    
    public static void main(String args[]){
        try{
            Conectar();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
