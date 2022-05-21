/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author Lourdes Pérez
 */
public class Prueba {
    
    public static Firestore td;
    public static void Conectar() throws FileNotFoundException, IOException{
        //Thanks to this youtube video: 01.- Conectar firebase con java. Netbeans 11.2: https://youtu.be/DvIFCYJusLc
        FileInputStream serviceAccount = new FileInputStream("bdcinemadb.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://bdcinema-5c24a-default-rtdb.firebaseio.com")
            .build();

        FirebaseApp.initializeApp(options);
        td = FirestoreClient.getFirestore();
        System.out.println("Se conecto con exito");
    }
    
    public static void main(String args[]){
        try{
            Conectar();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
