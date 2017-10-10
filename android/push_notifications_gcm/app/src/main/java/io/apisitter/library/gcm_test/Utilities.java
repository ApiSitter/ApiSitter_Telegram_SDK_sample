package io.apisitter.library.gcm_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by francesco on 09/10/17.
 */

public class Utilities {

    public static Pattern pattern = Pattern.compile("[\\-0-9]+");
//    public static SecureRandom random = new SecureRandom();

//    public static volatile DispatchQueue stageQueue = new DispatchQueue("stageQueue");
//    public static volatile DispatchQueue globalQueue = new DispatchQueue("globalQueue");
//    public static volatile DispatchQueue searchQueue = new DispatchQueue("searchQueue");
//    public static volatile DispatchQueue phoneBookQueue = new DispatchQueue("photoBookQueue");

//    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

//    static {
//        try {
//            File URANDOM_FILE = new File("/dev/urandom");
//            FileInputStream sUrandomIn = new FileInputStream(URANDOM_FILE);
//            byte[] buffer = new byte[1024];
//            sUrandomIn.read(buffer);
//            sUrandomIn.close();
//            random.setSeed(buffer);
//        } catch (Exception e) {
//            FileLog.e(e);
//        }
//    }

//    public static void aesIgeEncryption(ByteBuffer buffer, byte[] key, byte[] iv, boolean encrypt, boolean changeIv, int offset, int length) {
//        aesIgeEncryption(buffer, key, changeIv ? iv : iv.clone(), encrypt, offset, length);
//    }

    public static Integer parseInt(String value) {
        if (value == null) {
            return 0;
        }
        Integer val = 0;
        try {
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                String num = matcher.group(0);
                val = Integer.parseInt(num);
            }
        } catch (Exception e) {
//            FileLog.e(e);
        }
        return val;
    }

    public static Long parseLong(String value) {
        if (value == null) {
            return 0L;
        }
        Long val = 0L;
        try {
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                String num = matcher.group(0);
                val = Long.parseLong(num);
            }
        } catch (Exception e) {
//            FileLog.e(e);
        }
        return val;
    }

    public static String parseIntToString(String value) {
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }


}
