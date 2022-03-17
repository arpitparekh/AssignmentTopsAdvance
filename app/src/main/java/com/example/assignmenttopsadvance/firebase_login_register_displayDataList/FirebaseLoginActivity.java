package com.example.assignmenttopsadvance.firebase_login_register_displayDataList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.assignmenttopsadvance.databinding.ActivityFirebaseLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLoginActivity extends AppCompatActivity {
    private ActivityFirebaseLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login Screen");
        binding=ActivityFirebaseLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvNewUser.setOnClickListener(v -> {
            Intent intent=new Intent(this,FirebaseRegistrationActivity.class);
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(v -> {

            firebaseAuth=FirebaseAuth.getInstance();

            String email=binding.edtEmailFirebase.getText().toString();
            String password=binding.edtPasswordFirebase.getText().toString();

            if(email.equals("") || password.equals("") || password.length()<6){
                Toast.makeText(this,"Empty Field or Password length is short",Toast.LENGTH_SHORT).show();

            }else{
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(getApplicationContext(),DataListActivity.class);
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