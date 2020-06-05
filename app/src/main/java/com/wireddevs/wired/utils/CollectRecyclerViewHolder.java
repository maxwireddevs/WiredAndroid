package com.wireddevs.wired.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wireddevs.wired.Objects.File;
import com.wireddevs.wired.R;

import androidx.recyclerview.widget.RecyclerView;

public class CollectRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView name;
    private final ImageView logo;
    private final Context context;
    private File file;

    public CollectRecyclerViewHolder(Context context, View itemView){
        super(itemView);
        this.name=(TextView)itemView.findViewById(R.id.collectname);
        this.logo=(ImageView)itemView.findViewById(R.id.collectlogo);
        this.context=context;
        itemView.setOnClickListener(this);
    }

    public void bindFile(File file){
        this.file=file;
        String fname=file.getName();
        this.name.setText(fname);
        String substr = fname.substring(fname.length() - 3);
        int logoid;
        if(substr.equals("txt")){
            logoid=R.drawable.textfile;
        }
        else if(substr.equals("pkk")){
            logoid=R.drawable.key;
        }
        else{
            logoid=R.drawable.folder;
        }
        this.logo.setImageDrawable(context.getResources().getDrawable(logoid));
    }

    @Override
    public void onClick(View v) {
        if (this.file != null) {
            //TODO onClick student list item
        }
    }
}
