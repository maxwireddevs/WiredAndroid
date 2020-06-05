package com.wireddevs.wired;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wireddevs.wired.Objects.File;
import com.wireddevs.wired.utils.CollectRecyclerViewAdapter;
import com.wireddevs.wired.utils.RecyclerViewAdapter;
import com.wireddevs.wired.utils.RecyclerViewItemClickListener;

import java.util.ArrayList;

public class MainExplore extends AppCompatActivity {

    String[] toDisplay;
    int[] behaviourList;
    ArrayList<File> displayl=new ArrayList<>();
    ArrayList<File> collectfile=new ArrayList<>();
    int currentlayer=0;
    ImageView backbutton,screencover;
    RecyclerViewAdapter ra;
    String foldername;
    String behaviourname;
    String readmename;
    int folderid;
    int behaviourid;
    int readmeid;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_explore);

        final RecyclerView display=findViewById(R.id.folder);
        final RecyclerView collect=findViewById(R.id.collectitems);

        display.setHasFixedSize(true);
        display.setLayoutManager(new LinearLayoutManager(this));
        ra= new RecyclerViewAdapter(this,R.layout.folderitem,displayl);
        display.setAdapter(ra);

        getFileList(currentlayer);
        backbutton=findViewById(R.id.backbutton);
        screencover=findViewById(R.id.screencover);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFunction();
            }
        });

        collect.setHasFixedSize(true);
        LinearLayoutManager hLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        collect.setLayoutManager(hLayoutManager);
        final CollectRecyclerViewAdapter collectAdapter = new CollectRecyclerViewAdapter(this, R.layout.collectitem, collectfile);
        collect.setAdapter(collectAdapter);

        display.addOnItemTouchListener(new RecyclerViewItemClickListener(this, display, new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if(displayl.get(position).getBehaviour()==0){
                    currentlayer++;
                    getFileList(currentlayer);
                    ra.notifyDataSetChanged();
                }
                else if(displayl.get(position).getBehaviour()==1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainExplore.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = getLayoutInflater();
                    View v= inflater.inflate(R.layout.dialog, null);
                    builder.setView(v);
                    TextView message=v.findViewById(R.id.message);
                    message.setText(getResources().getString(readmeid));
                    builder.show();
                }
                else if(displayl.get(position).getBehaviour()==2){
                    for(int i=0;i<collectfile.size();i++){
                        if(collectfile.get(i).getName().equals(displayl.get(position).getName())){
                            return;
                        }
                    }
                    if(displayl.get(position).getName().substring(displayl.get(position).getName().length() - 3).equals("pkk")){
                        collectfile.add(displayl.get(position));
                        collectAdapter.notifyDataSetChanged();
                    }

                }
                else if(displayl.get(position).getBehaviour()==3){
                    for(int i=0;i<collectfile.size();i++){
                        String keyname=collectfile.get(i).getName().substring(0,collectfile.get(i).getName().length() - 3);
                        String filename=displayl.get(position).getName().substring(0,displayl.get(position).getName().length() - 3);
                        if(keyname.equals(filename)){
                            notifyString("Folder unlocked."+"\n"+"\n");
                            currentlayer++;
                            getFileList(currentlayer);
                            ra.notifyDataSetChanged();
                            return;
                        }
                    }
                    playSound("error");
                    notifyString("This folder is locked."+"\n"+"\n");
                }
                else if(displayl.get(position).getBehaviour()==4){
                    if(displayl.get(position).getName().substring(0,displayl.get(position).getName().length() - 4).equals("subject5")){
                        playSound("error");
                        notifyString("This file appears to be corrupted, or of the wrong type."+"\n"+"\n");
                    }
                    else{
                        int photoid = getResources().getIdentifier(displayl.get(position).getName().substring(0,displayl.get(position).getName().length() - 4), "drawable", getPackageName());
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainExplore.this);
                        builder.setCancelable(true);
                        LayoutInflater inflater = getLayoutInflater();
                        View v= inflater.inflate(R.layout.imagedialog, null);
                        builder.setView(v);
                        ImageView image=v.findViewById(R.id.photo);
                        image.setImageDrawable(getResources().getDrawable(photoid));
                        builder.show();
                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                };
                            }
                        });
                    }

                }
                else if(displayl.get(position).getBehaviour()==5){
                    for(int i=0;i<collectfile.size();i++){
                        String keyname=collectfile.get(i).getName().substring(0,collectfile.get(i).getName().length() - 3);
                        String filename=displayl.get(position).getName().substring(0,displayl.get(position).getName().length() - 3);
                        if(keyname.equals(filename)){
                            notifyString("Folder unlocked."+"\n"+"\n");
                            currentlayer+=3;
                            getFileList(currentlayer);
                            ra.notifyDataSetChanged();
                            return;
                        }
                    }
                    playSound("error");
                    notifyString("This folder is locked."+"\n"+"\n");
                }
                else if(displayl.get(position).getBehaviour()==6){
                    if(id==0){



                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainExplore.this);
                        builder.setCancelable(false);
                        LayoutInflater inflater = getLayoutInflater();
                        View v= inflater.inflate(R.layout.sm_dialog, null);
                        builder.setView(v);
                        final TextView tt400dialog=v.findViewById(R.id.name);
                        ImageView key=v.findViewById(R.id.key);
                        final Dialog k=builder.show();
                        key.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                id++;
                                if(id<4){
                                    String smid="sm"+id;
                                    int smnumid = getResources().getIdentifier(smid, "string", getPackageName());
                                    tt400dialog.setText(smnumid);
                                }
                                else{
                                    k.dismiss();
                                    collectfile.add(new File("shell3.pkk",2));
                                    collectAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                    else{
                        playSound("error");
                        notifyString("This file is locked."+"\n"+"\n");
                    }
                }
            }
            @Override
            public void onLongItemClick(View view, int position) {}
        }));
    }

    private void getFileList(int layer) {
        displayl.clear();
        this.currentlayer=layer;
        foldername="f"+currentlayer;
        behaviourname="i"+currentlayer;
        readmename="readme"+currentlayer;
        folderid = this.getResources().getIdentifier(foldername, "array", this.getPackageName());
        behaviourid = this.getResources().getIdentifier(behaviourname, "array", this.getPackageName());
        readmeid=this.getResources().getIdentifier(readmename,"string",this.getPackageName());
        toDisplay=getResources().getStringArray(folderid);
        behaviourList=getResources().getIntArray(behaviourid);
        for(int i=0;i<toDisplay.length;i++){
            displayl.add(new File(toDisplay[i],behaviourList[i]));
        }
    }

    private void notifyString(String notify){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainExplore.this);
        builder.setCancelable(true);
        LayoutInflater inflater = getLayoutInflater();
        View v= inflater.inflate(R.layout.dialog, null);
        builder.setView(v);
        TextView message=v.findViewById(R.id.message);
        message.setText(notify);
        builder.show();
    }


    @Override
    public void onBackPressed() {
        backFunction();
    }

    public void playSound(String name){
        int soundid = getResources().getIdentifier(name, "raw", getPackageName());
        final MediaPlayer error = MediaPlayer.create(this, soundid);
        error.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        error.start();
    }

    public void backFunction(){
        if(currentlayer==5){
            currentlayer=2;
            getFileList(currentlayer);
            ra.notifyDataSetChanged();
        }
        else if(currentlayer>0){
            currentlayer--;
            getFileList(currentlayer);
            ra.notifyDataSetChanged();
        }
    }
}
