package com.wireddevs.wired.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wireddevs.wired.Objects.File;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    // 1. Initialize our adapter
    private final List<File> files;
    private Context context;
    private int itemResource;

    public RecyclerViewAdapter(Context context, int itemResource, List<File> files){
        this.files=files;
        this.context=context;
        this.itemResource=itemResource;
    }

    // 2. Override the onCreateViewHolder method
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 3. Inflate the view and return the new ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(this.itemResource, parent, false);
        return new RecyclerViewHolder(this.context,view);
    }

    // 4. Override the onBindViewHolder method
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        // 5. Use position to access the correct Student object
        File file=this.files.get(position);
        holder.bindFile(file);
    }

    @Override
    public int getItemCount() {
        return this.files.size();
    }

}
