package br.com.truvainfo.zoolyapi.util;

import br.com.truvainfo.zoolyapi.domain.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.util.Objects.nonNull;

public class GeneralUtil {

    private static final String MSG_INTERNAL_ERROR = "msg.error.internal";

    private static final ResourceBundle bundle;
    private static Logger logger = LoggerFactory.getLogger(GeneralUtil.class);

    static {
        bundle = ResourceBundle.getBundle("messages", new Locale("pt", "BR"));
    }

    public static MyUserDetails getLoggedUser() {
        final MyUserDetails myUserDetails = getNamesAuthentication();
        return myUserDetails;
    }

    private static MyUserDetails getNamesAuthentication() {
        Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new IllegalArgumentException(getMessage("MSG_INTERNAL_ERROR")));
        return (MyUserDetails) authentication.getPrincipal();
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

    public static String getTemplateEmail() {
        return "<center><td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-top-width:0;border-left-width:0;border-right-width:0;margin:0;padding:0\">\t<div style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;max-width:600px;display:block;background-color:#ffffff;margin:0 auto;padding:15px 0px\">\t\t<table style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-collapse:collapse;border-spacing:0px;width:100%;margin:0;padding:0\"> \t\t\t<tbody><tr style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;width:600px;margin:0;padding:15px\">\t\t\t\t<td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-top-width:0;border-left-width:0;border-right-width:0;margin:0;padding:0\">\t\t\t\t\t<table style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-collapse:collapse;border-spacing:0px;width:100%;margin:0;padding:0\">\t\t\t\t\t\t<tbody><tr style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;width:600px;margin:0;padding:15px\">\t\t\t\t\t\t\t<td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-top-width:0;border-left-width:0;border-bottom-width:0;margin:0;padding:20px\"><img src=\"https://i.imgur.com/CHVpwoA.png\" width=\"70\" height=\"38\" style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;margin:auto;max-width:100%;padding:0\" class=\"CToWUd\"></td>\t\t\t\t\t\t\t<td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-top-width:0;border-bottom-width:0;border-right-width:0;font-size:18px;font-weight:600;margin:0;padding:10px 20px 20px 0px;color:black\" align=\"right\">Olá, <span>#{username}</span></td>\t\t\t\t\t\t</tr>\t\t\t\t\t</tbody></table>\t\t\t\t</td>\t\t\t</tr>\t\t\t<tr style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;width:600px;margin:0;padding:15px\">\t\t\t\t<td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-top-width:0;border-left-width:0;border-right-width:0;margin:0;padding:0\" bgcolor=\"#0a0d27\">\t\t\t\t\t<table style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-collapse:collapse;border-spacing:0px;width:100%;margin:0;padding:0\">\t\t\t\t\t\t<tbody><tr style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;width:600px;margin:0;padding:15px\">\t\t\t\t\t\t\t<td align=\"center\" class=\"m_-2100657152982101040bottom-padding\" style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;background-color:#0a0d27 no-repeat top center/cover;margin:0;padding:0 0 40px;border:0\">\t\t\t\t\t\t\t\t<h2 class=\"m_-2100657152982101040inner-padding-hero\" style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;font-weight:500;font-size:20px;color:#ffffff;margin:0 0 12px;padding:30px 100px 0px\">Redefinir Senha</h2>\t\t\t\t\t\t\t\t<div class=\"m_-2100657152982101040hero m_-2100657152982101040inner-padding-hero\" style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;color:#ffffff;max-width:100%;margin:0;padding:30px 100px 0px\" align=\"center\">\t\t\t\t\t\t\t\t\t<h1 style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;color:#ffffff;font-weight:bold;font-size:24px;margin:0 0 5px;padding:0\">\t\t\t\t\t\t\t\t\t\tRecebemos uma requisição para redefinição de senha<br><br>\t\t\t\t\t\t\t\t\t\t<span style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;color:#b198df;margin:0;padding:0\">Para prosseguir com a ação:</span>\t\t\t\t\t\t\t\t\t</h1>\t\t\t\t\t\t\t\t</div>\t\t\t\t\t\t\t</td>\t\t\t\t\t\t</tr>\t\t\t\t\t</tbody></table>\t\t\t\t</td>\t\t\t</tr>  \t\t\t<tr><td><div style=\"clear:both;font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;margin:0;padding:0\"></div>\t\t\t</td></tr><tr style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;width:600px;margin:0;padding:15px\">\t\t\t\t<td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;margin:0;padding:0;border:0\">\t\t\t\t\t<table style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;border-collapse:collapse;border-spacing:0px;width:100%;margin:0;padding:0\">\t\t\t\t\t\t<tbody><tr style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;width:600px;margin:0;padding:15px\">\t\t\t\t\t\t\t<td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;margin:0;padding:0;border:0\">\t\t\t\t\t\t\t\t<p class=\"m_-2100657152982101040cta\" style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;font-weight:normal;font-size:16px;color:#19171c;border-radius:6px;margin:30px 0;padding:0\" align=\"center\"><a href=\"#{userUrl}\" class=\"m_-2100657152982101040button-sum\" style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;color:white;text-decoration:none;background:linear-gradient(90deg,#ff725f 30%,#f231a5);border-radius:6px;font-weight:600;display:inline-block;vertical-align:left;font-size:1.2em;line-height:1em;margin:0 auto;padding:25px 45px\" target=\"_blank\" data-saferedirecturl=\"#{userUrl}\">Redefinir</a></p>\t\t\t\t\t\t\t</td>\t\t\t\t\t\t</tr>\t\t\t\t\t</tbody></table>\t\t\t\t</td>\t\t\t</tr>\t\t</tbody></table>\t</div></td><tr style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;width:600px;margin:0;padding:15px\">\t<td style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;margin:0;padding:0;border:0\">\t\t<p style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;font-size:12px;color:#19171c;font-weight:200;margin:5px auto;padding:0\" align=\"center\">Você está recebendo este e-mail porque foi realizada uma solicitação na aplicação Zooly Backoffice.                                    \t\t</p>\t\t<p style=\"font-family:&quot;Helvetica Neue&quot;,&quot;Helvetica&quot;,Helvetica,Arial,sans-serif;font-size:12px;color:#19171c;font-weight:200;margin:5px auto;padding:0\" align=\"center\">© 2020 ZoolyBackOffice.\t\t</p>\t</td></tr></center>\t\t";
    }

}
