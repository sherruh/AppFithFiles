package com.example.fileapp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    File[] files;
    FileAdapter fileAdapter;
    File file;
    ArrayList<File> fileList;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getfiles();
        i=0;
    }

    private void initAdapter() {
        fileAdapter=new FileAdapter(fileList);
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
            fileList=new ArrayList<>(Arrays.asList(folder.listFiles()));
            initAdapter();
        }
    }

    public void cameraClick(View view) {
        i++;
        File folder=new File(Environment.getExternalStorageDirectory(),"MyFiles");
        if(!folder.exists()) folder.mkdir();
        file=new File(folder,"image"+i+".jpg");
        Uri uri= FileProvider.getUriForFile(this,"com.example.fileapp2.fileprovider",file);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,112);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==112){
            fileList.add(0,file);
            fileAdapter.notifyDataSetChanged();
        }
    }
}
