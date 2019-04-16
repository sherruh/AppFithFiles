package com.example.fileapp2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    File[] files;
    FileAdapter fileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getfiles();

    }

    private void initAdapter() {
        fileAdapter=new FileAdapter(files);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(fileAdapter);
    }

    private void getfiles() {
        File folder=new File(Environment.getExternalStorageDirectory(),"DCIM/Screenshots");
        if(folder.exists()){
            files=folder.listFiles();
            for(File file:files){
                Log.d("MyApp",file.getAbsolutePath());
            }
            initAdapter();
        }
    }
}
