package hcmiucvip.solutionforsavingstudentrecords.core.Auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthUtil {
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String hashString(String str) {
        String hashedStr;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            hashedStr = bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            hashedStr = null;
        }
        return hashedStr;
    }
    public static void main(String[] args) {
        String pass = "cvip123";
        System.out.println(hashString(pass));
    }
}