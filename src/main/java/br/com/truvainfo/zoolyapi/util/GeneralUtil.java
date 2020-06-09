package br.com.truvainfo.zoolyapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

public class GeneralUtil {

    private static final String MSG_INTERNAL_ERROR = "msg.error.internal";

    private static final ResourceBundle bundle;
    private static Logger logger = LoggerFactory.getLogger(GeneralUtil.class);

    static {
        bundle = ResourceBundle.getBundle("messages", new Locale("pt", "BR"));
    }

    public static String getMessage(final String message) {
        try {
            final String msg = nonNull(message) ? message : MSG_INTERNAL_ERROR;
            return bundle.getString(msg);
        } catch (MissingResourceException e) {
            logger.error(e.getMessage(), e);
        }

        return bundle.getString(MSG_INTERNAL_ERROR);
    }

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomWord() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

}
