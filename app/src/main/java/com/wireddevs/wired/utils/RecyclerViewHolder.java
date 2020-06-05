package com.wireddevs.wired.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.wireddevs.wired.Objects.File;
import com.wireddevs.wired.R;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView name;
    private final ImageView logo;
    private final Context context;
    private File file;

    RecyclerViewHolder(Context context, View itemView){
        super(itemView);
        this.name=(TextView)itemView.findViewById(R.id.name);
        this.logo=(ImageView)itemView.findViewById(R.id.logo);
        this.context=context;
        itemView.setOnClickListener(this);
    }

    void bindFile(File file){
        this.file=file;
        String fname=file.getName();
        this.name.setText(fname);
        String substr = fname.substring(fname.length() - 3);
        int logoid;
        switch (substr) {
            case "txt":
                logoid = R.drawable.textfile;
                break;
            case "pkk":
                logoid = R.drawable.key;
                break;
            case "lck":
                logoid = R.drawable.locked;
                break;
            case "png":
                logoid = R.drawable.image;
                break;
            case "exe":
                logoid = R.drawable.inwardarrow;
                break;
            default:
                logoid = R.drawable.folder;
                break;
        }
        this.logo.setImageDrawable(context.getResources().getDrawable(logoid));
    }

    @Override
    public void onClick(View v) {
        if (this.file != null) {

        }
    }
}
