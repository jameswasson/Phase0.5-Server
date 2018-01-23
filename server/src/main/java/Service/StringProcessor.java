package Service;
import Handler.ResponseFromServer;

public class StringProcessor {
    private static ResponseFromServer lastResponse;
    private static StringProcessor myStringProcessor;
    private StringProcessor() {
    }
    public static StringProcessor getStringProcessor() {
        if (myStringProcessor == null)
            myStringProcessor = new StringProcessor();
        return myStringProcessor;
    }

    public static ResponseFromServer getLastResponse() {
        return lastResponse;
    }
    public static void setLastResponse(ResponseFromServer lastResponse) {
        StringProcessor.lastResponse = lastResponse;
    }

    private ResponseFromServer _trim(String str) {
        String result = str.trim();
        ResponseFromServer toReturn = new ResponseFromServer();
        toReturn.message = result;
        toReturn.error = false;
        return toReturn;
    }
    private ResponseFromServer _toLowerCase(String str) {
        String madeLowerCase = str.toLowerCase();
        ResponseFromServer toReturn = new ResponseFromServer();
        toReturn.message = madeLowerCase;
        toReturn.error = false;
        return toReturn;
    }
    private ResponseFromServer _parseInteger(String str) {
        ResponseFromServer toReturn = new ResponseFromServer();
        try {
            toReturn.number = Integer.parseInt(str);
            toReturn.error = false;
        }catch (NumberFormatException e){
            toReturn.error = true;
            toReturn.errorMessage = e.getMessage();
        }
        return toReturn;
    }

    public static ResponseFromServer trim(String str) {
        setLastResponse(getStringProcessor()._trim(str));
        return getLastResponse();
    }
    public static ResponseFromServer toLowerCase(String str) {
        setLastResponse(getStringProcessor()._toLowerCase(str));
        return getLastResponse();
    }
    public static ResponseFromServer parseInteger(String str) {
        setLastResponse(getStringProcessor()._parseInteger(str));
        return getLastResponse();
    }
}