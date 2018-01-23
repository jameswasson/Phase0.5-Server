package com.Start;

import com.google.gson.Gson;

/**
 * Created by James on 1/20/2018.
 */

public class ResponseFromServer {
    boolean error;
    String errorMessage;
    String message;
    int number;

    public String getJson(){
        return new Gson().toJson(this);
    }
    public ResponseFromServer(String json){
        ResponseFromServer newResponse = new Gson().fromJson(json,ResponseFromServer.class);
        error = newResponse.error;
        errorMessage = newResponse.errorMessage;
        message = newResponse.message;
        number = newResponse.number;
    }
}
