package com.example.assignmenttopsadvance.firebase_saveProfileImage;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Toast;

import com.example.assignmenttopsadvance.R;
import com.example.assignmenttopsadvance.databinding.ActivitySaveProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public class SaveProfileActivity extends AppCompatActivity {

    private ActivitySaveProfileBinding binding;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // new activity result method by ankit sir video

        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            String newPath = copyFileToInternal(result); // this is important function call
                            if (newPath != null) {
                                Bitmap image = BitmapFactory.decodeFile(newPath);
                                binding.imageViewFirebase.setImageBitmap(image);

                                binding.btnUploadImage.setOnClickListener(v -> {

                                    uploadFile(result);

                                });
                            }

                        }
                    }

                }
        );

        binding.btnSelectImage.setOnClickListener(v -> {
            launcher.launch("image/*");
        });

    }
    
    //only new thing is this....function made by me... u only need file Uri result....

    private void uploadFile(Uri result) {

        ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading File");
        pd.setMessage("Please Wait......");

        pd.show();

        //What a WonderFull Program

        //generate random key
        final String randomKey = UUID.randomUUID().toString();
        StorageReference picturesRef = storageReference.child("images/" + randomKey);

        //put uri into putFile()

        picturesRef.putFile(result)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
        //this is not necessary but for practice by watching those 2  ankit sir videos....

    private String copyFileToInternal(Uri result) {
        Cursor cursor = getContentResolver().query(result, new String[]{OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE}, null, null, null);
        cursor.moveToFirst();

        String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//        long size = cursor.getLong(cursor.getColumnIndex(OpenableColumns.SIZE));
        File file = new File(getFilesDir() + "/" + displayName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = getContentResolver().openInputStream(result);

            byte buffers[] = new byte[1024];
            int read;
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }
            inputStream.close();
            outputStream.close();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}