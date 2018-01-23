package com.Start;

public class Main {
    public static void main(String args[]){
        testEverythingOnce();
    }
    private static void testEverythingOnce(){
        int parse1 = 3;
        int parse2 = 6;
        String lower1 = "hello WORLD";
        String lower2 = "HELLO world";

        String trim1 = "  hello world  ";
        String trim2 = "      hello world    ";

        lower1 = StringProcessorProxy.getStringProcessorProxy()._toLowerCase(lower1);
        lower2 = StringProcessorProxy.getStringProcessorProxy().toLowerCase(lower2);

        trim1 = StringProcessorProxy.getStringProcessorProxy()._trim(trim1);
        trim2 = StringProcessorProxy.getStringProcessorProxy().trim(trim2);

        try {
            parse1 = StringProcessorProxy.getStringProcessorProxy()._parseInteger("1235");
        }catch (Exception e){}
        try {
            parse2 = StringProcessorProxy.getStringProcessorProxy().parseInteger("-1235");
        }catch (Exception e){}

        if (lower1.equals(lower2))
            System.out.println("ToLowerCase works!");
        if (trim1.equals(trim2))
            System.out.println("Trim works!");

        if (parse1 + parse2 == 0)
            System.out.println("ParseInt works! (1)");

        try{
            StringProcessorProxy.getStringProcessorProxy().parseInteger("1000numbers");
        }catch(Exception e){
            System.out.println("ParseInt works! (2)");
        }
        try{
            StringProcessorProxy.getStringProcessorProxy()._parseInteger("Million");
        }catch(Exception e){
            System.out.println("ParseInt works! (3)");
        }
    }
}