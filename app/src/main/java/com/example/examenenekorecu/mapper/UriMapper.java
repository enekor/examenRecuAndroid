package com.example.examenenekorecu.mapper;

import android.net.Uri;

public class UriMapper {

    private static UriMapper mapper;
    private UriMapper(){}

    public static UriMapper getInstance(){
        if(mapper==null){
            mapper = new UriMapper();
        }
        return  mapper;
    }

    public Uri fromStringToUri(String string){
        return Uri.parse(string);
    }

    public String fromUriToString(Uri uri){
        return uri.toString();
    }
}
