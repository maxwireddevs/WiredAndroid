package com.wireddevs.wired.Objects;

public class File {
    private String name;
    private int behaviour;

    public File(){

    }
    public File(String name, int logoid){
        this.name=name;
        this.behaviour =logoid;
    }

    public void setName(String name){this.name=name;}
    public void setBehaviour(int behaviour){this.behaviour = behaviour;}
    public String getName(){return this.name;}
    public int getBehaviour(){return this.behaviour;}
}
