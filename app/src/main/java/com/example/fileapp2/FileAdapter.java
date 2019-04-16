package com.example.fileapp2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private File[] files;

    public FileAdapter(File[] files) {
        this.files = files;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_file,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(files[i]).resize(200,200).into(viewHolder.imageView);
        viewHolder.textName.setText(files[i].getName());
        Date date=new Date(files[i].lastModified());
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault());
        viewHolder.textName.setText(sdf.format(date));
    }

    @Override
    public int getItemCount() {
        return files.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview);
            textName=itemView.findViewById(R.id.text_name);
        }
    }
}
