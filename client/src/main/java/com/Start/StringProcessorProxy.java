package com.Start;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sun.corba.se.spi.presentation.rmi.PresentationManager;

import java.lang.reflect.Type;

import javax.xml.ws.Response;

public class StringProcessorProxy implements IStringProcessor {
    private final String className = "Service.StringProcessor";

    private static StringProcessorProxy mySPP;
    public static StringProcessorProxy getStringProcessorProxy(){
        if (mySPP == null)
            mySPP = new StringProcessorProxy();
        return mySPP;
    }
    private StringProcessorProxy(){}

    //sends Commands
    public String _trim(String toTrim){
        GenericCommand myCommand = new GenericCommand(className,"trim",
                new Class<?>[]{String.class},
                new Object[]{toTrim});

        String stringToSend = toString(myCommand);

        String response = ClientCommunicator.send("command",stringToSend);
        ResponseFromServer myResponse =  new Gson().fromJson(response,ResponseFromServer.class);

        if (myResponse.error)
            return myResponse.errorMessage;
        else
            return myResponse.message;
    }
    public String _toLowerCase(String input){
        GenericCommand myCommand = new GenericCommand(className,"toLowerCase",
                new Class<?>[]{String.class},
                new Object[]{input});

        String stringToSend = toString(myCommand);

        String response = ClientCommunicator.send("command",stringToSend);
        ResponseFromServer myResponse =  new Gson().fromJson(response,ResponseFromServer.class);

        if (myResponse.error)
            return myResponse.errorMessage;
        else
            return myResponse.message;
    }
    public int _parseInteger(String input) throws Exception{

        GenericCommand myCommand = new GenericCommand(className,"parseInteger",
                new Class<?>[]{String.class},
                new Object[]{input});



        String stringToSend = toString(myCommand);

        String response = ClientCommunicator.send("command",stringToSend);
        ResponseFromServer myResponse =  new Gson().fromJson(response,ResponseFromServer.class);

        if (myResponse.error)
            throw new Exception(myResponse.errorMessage);
        else
            return myResponse.number;
    }

    //does not send Commands
    public String trim(String toTrim){

        String response = ClientCommunicator.send("trim",toTrim);
        ResponseFromServer myResponse =  new Gson().fromJson(response,ResponseFromServer.class);

        if (myResponse.error)
            return myResponse.errorMessage;
        else
            return myResponse.message;
    }
    public String toLowerCase(String input){

        String response = ClientCommunicator.send("toLowerCase",input);
        ResponseFromServer myResponse =  new Gson().fromJson(response,ResponseFromServer.class);

        if (myResponse.error)
            return myResponse.errorMessage;
        else
            return myResponse.message;
    }
    public int parseInteger(String input) throws Exception{

        String response = ClientCommunicator.send("parseInteger",input);
        ResponseFromServer myResponse =  new Gson().fromJson(response,ResponseFromServer.class);

        if (myResponse.error)
            throw new Exception(myResponse.errorMessage);
        else
            return myResponse.number;
    }



    static class ClassDataSerializer implements JsonSerializer<Class> {
        @Override
        public JsonElement serialize(Class src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTypeName());
        }
    }

    public static String toString(GenericCommand rfs){
        String toReturn;
        GsonBuilder myBuilder = new GsonBuilder();
        myBuilder.registerTypeAdapter(Class.class,new ClassDataSerializer());
        toReturn = myBuilder.create().toJson(rfs);
        return toReturn;
    }
}