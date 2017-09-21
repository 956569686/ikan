package me.chriskyle.library.net.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Description :
 * <p/>
 * Created by Chris Kyle on 2017/7/21.
 */
public final class Util {

    public static String urlDecode(String url) {
        try {
            String prevURL = "";
            String decodeURL = url;
            while (!prevURL.equals(decodeURL)) {
                prevURL = decodeURL;
                decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
            }
            return decodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while decoding" + e.getMessage();
        }
    }
}
