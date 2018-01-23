package Handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import Service.StringProcessor;

public class TrimHandler implements HttpHandler {
    String response = null;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.print("Called TrimHandler");
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                ResponseFromServer result = StringProcessor.trim(reqData);
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
}