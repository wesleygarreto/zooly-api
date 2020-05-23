package br.com.truvainfo.zoolyapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.MissingResourceException;
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

}
