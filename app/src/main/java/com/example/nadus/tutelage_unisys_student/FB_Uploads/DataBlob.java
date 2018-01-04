package com.example.nadus.tutelage_unisys_student.FB_Uploads;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.nadus.tutelage_unisys_student.DataModels.UserCreds;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
/**
 * Created by msuba on 12/31/2017.
 */
/*data blob for uploading blobs*/

public class DataBlob extends AppCompatActivity
{
    private static FirebaseStorage storage;
    private static StorageReference storageReference;
    private static Firebase fb_db;
    public static int userCreateFlag=0;
    public static ProgressDialog progressDialog;


    public static void CreateUser(Uri filePath, DatabaseReference databaseReference, UserCreds userCreds,Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Setting up your account...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if(filePath != null)
        {
            //fb_db = new Firebase("https://tutelage-d619f.firebaseio.com/").child(FBPath);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            StorageReference ref = storageReference.child(databaseReference+ UserCreds.Umail);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            databaseReference.setValue(userCreds);
                           // userCreateFlag = 1;
//                            return Boolean.TRUE;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            System.out.println("PROGRESS IS "+progress );
                        }
                    });
        }

    }
    public static void UserDetailsUpload(UserCreds userCreds,String FBpath)
    {

    }
}
