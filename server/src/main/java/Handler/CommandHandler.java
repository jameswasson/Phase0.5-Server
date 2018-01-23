package Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

import Service.GenericCommand;
import Service.StringProcessor;

public class CommandHandler implements HttpHandler {
    String response = null;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.print("Called CommandHandler");
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);

                GsonBuilder myBuilder = new GsonBuilder();
                myBuilder.registerTypeAdapter(GenericCommand.class,new GenericCommandDeserializer());
                GenericCommand toExecute = myBuilder.create().fromJson(reqData,GenericCommand.class);


                toExecute.execute();
                ResponseFromServer result = StringProcessor.getLastResponse();
                response = new Gson().toJson(result);


                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                PrintWriter pw = new PrintWriter(exchange.getResponseBody());
                pw.write(response);
                pw.flush();
                pw.close();
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
        System.out.println(".");
    }
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private GenericCommand getCommand(String json){
        GenericCommand toReturn;
        GsonBuilder myBuilder = new GsonBuilder();

        toReturn = myBuilder.create().fromJson(json,GenericCommand.class);
        return toReturn;
    }

    public class GenericCommandDeserializer implements JsonDeserializer<GenericCommand> {

        @Override
        public GenericCommand deserialize(JsonElement Element,Type myType, JsonDeserializationContext context) {
            GenericCommand toReturn;
            Class<?>[] _paramTypes = {null};

            String _className = Element.getAsJsonObject().get("_className").getAsString();
            String _methodName = Element.getAsJsonObject().get("_methodName").getAsString();
            try {
                Class c = Class.forName(Element.getAsJsonObject().get("_paramTypes").getAsString());
                _paramTypes[0] = c;
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            Object[] _paramValues = {Element.getAsJsonObject().get("_paramValues").getAsString()};


            toReturn = new GenericCommand(_className,_methodName,_paramTypes,_paramValues);
            return toReturn;
        }

    }

}