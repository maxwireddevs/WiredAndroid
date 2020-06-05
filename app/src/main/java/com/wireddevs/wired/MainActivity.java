package com.wireddevs.wired;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wireddevs.wired.Objects.File;
import com.wireddevs.wired.utils.RecyclerViewAdapter;
import com.wireddevs.wired.utils.RecyclerViewItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView main=findViewById(R.id.mainmenu);
        ArrayList<File>displayl=new ArrayList<>();
        String[] toDisplay=getResources().getStringArray(R.array.main);
        for (String s : toDisplay) {
            displayl.add(new File(s, 0));
        }
        main.setHasFixedSize(true);
        main.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerViewAdapter ra= new RecyclerViewAdapter(this,R.layout.folderitem,displayl);
        main.setAdapter(ra);
        main.addOnItemTouchListener(new RecyclerViewItemClickListener(this, main, new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==0){
                    toExplore();
                }
            }
            @Override
            public void onLongItemClick(View view, int position) {}}));
    }

    public void toExplore(){
        Intent exploreintent=new Intent(this,MainExplore.class);
        exploreintent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(exploreintent);
    }
}
