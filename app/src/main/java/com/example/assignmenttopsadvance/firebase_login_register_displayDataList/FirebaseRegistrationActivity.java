package com.example.assignmenttopsadvance.firebase_login_register_displayDataList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.assignmenttopsadvance.databinding.ActivityFirebaseRegistrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseRegistrationActivity extends AppCompatActivity {
    private ActivityFirebaseRegistrationBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registration Screen");
        binding=ActivityFirebaseRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegistration.setOnClickListener(v -> {

            firebaseAuth=FirebaseAuth.getInstance();

            String email=binding.edtEmailFirebaseRegistration.getText().toString();
            String password=binding.edtPasswordFirebaseRegistration.getText().toString();

            if(email.equals("") || password.equals("") || password.length()<6){
                Toast.makeText(this,"Empty Field or password length is short",Toast.LENGTH_SHORT).show();
            }else{
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(getApplicationContext(),FirebaseLoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });


            }



        });





    }
}