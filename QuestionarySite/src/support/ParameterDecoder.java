package support;

import java.io.UnsupportedEncodingException;

public class ParameterDecoder {

    public static String decodePostParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"), "UTF-8");
    }
}
