package support;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGen {

    private String string;

    public HashGen(String string) {
        this.string = string;
    }

    public String md5Hash() {MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(string.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);

        String md5Hex = bigInt.toString(16);

        StringBuilder md5HexResult = new StringBuilder();

        while( md5HexResult.length() + md5Hex.length() < 32 ){
            md5HexResult.append("0");
        }

        md5HexResult.append(md5Hex);

        return md5HexResult.toString();
    }
}
