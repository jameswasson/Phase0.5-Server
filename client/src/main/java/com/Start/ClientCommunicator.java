package com.Start;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by James on 1/20/2018.
 */

public class ClientCommunicator {
    public static String send(String urlPath, String request){
        String result = "";
        try {
            result = SendRequest(request, urlPath);
        }
        catch (Exception e){
            result = "Error occured: " + result;
        }
        return result;
    }

    private static String SendRequest(String requestBody,String urlPath)throws Exception{
        URL url;
        String IPAddress = "10.24.68.140";
        String localPort = "1234";
        url = new URL("http://" + IPAddress + ":" + localPort+ "/" + urlPath + "/");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod("GET");
        http.setDoOutput(true);

        http.connect();
        OutputStream out = http.getOutputStream();
        writeString(requestBody,out);
        out.close();

        InputStream response = http.getInputStream();
        return readString(response);
    }

    private static void writeString(String str, OutputStream os) throws Exception {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }//thanks stackoverflow
    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }//copied from notes
}
