package Handler;

import com.google.gson.Gson;

public class ResponseFromServer {
    public boolean error;
    public String errorMessage;
    public String message;
    public int number;

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
    public ResponseFromServer(){}
}