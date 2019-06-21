package com.elfefe.wiid.models;

public enum MainPages {
    MAPS("Maps"),
    COMMUNITY("Community"),
    PRIVATE("Private");


    public String title;

    MainPages(String title){
        this.title = title;
    }
}
