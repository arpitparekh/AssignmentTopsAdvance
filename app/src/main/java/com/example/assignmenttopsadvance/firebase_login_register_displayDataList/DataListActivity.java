package com.example.assignmenttopsadvance.firebase_login_register_displayDataList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.assignmenttopsadvance.R;
import com.example.assignmenttopsadvance.databinding.ActivityDataListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataListActivity extends AppCompatActivity {
    private ActivityDataListBinding binding;
    private DatabaseReference database;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDataListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pd=new ProgressDialog(this);
        pd.setMessage("Loading Data");
        pd.show();

        database= FirebaseDatabase.getInstance().getReference().child("Users");

        ArrayList<UserData>dataArrayList=new ArrayList<>();

        ArrayAdapter<UserData>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataArrayList);

        binding.listVewDataList.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    dataArrayList.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        dataArrayList.add(dataSnapshot.getValue(UserData.class));
                    }
                    pd.dismiss();
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_data_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_add_data){
            Intent intent=new Intent(this,AddDataActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}