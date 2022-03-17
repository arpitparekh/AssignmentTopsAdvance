package com.example.assignmenttopsadvance.firebase_login_register_displayDataList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.assignmenttopsadvance.databinding.ActivityAddDataBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDataActivity extends AppCompatActivity {
    private ActivityAddDataBinding binding;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database=FirebaseDatabase.getInstance().getReference().child("Users");

        binding.btnAddData.setOnClickListener(v -> {
            String firstName=binding.edtFirstName.getText().toString();
            String lastName=binding.edtLastName.getText().toString();
            String mobile=binding.edtMobile.getText().toString();
            String email=binding.edtEmail.getText().toString();

            UserData data= new UserData(firstName,lastName,mobile,email);

            database.push().setValue(data);
            Toast.makeText(this,"Data Added SuccessFully",Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(this,DataListActivity.class);
            startActivity(intent);
        });

    }
}