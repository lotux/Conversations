package eu.siacs.conversations.utils;

import java.util.HashMap;

/**
 * Created by reza on 6/27/16.
 */
public class CountryCodeHelper {
    public static final HashMap<Integer,String> alphaCodeByNumbericCode = new HashMap<>();

    static {
        alphaCodeByNumbericCode.put(98,"IR");
        alphaCodeByNumbericCode.put(1,"US");
        alphaCodeByNumbericCode.put(1,"CA");


    }
}
