package io.graversen.hashingapp;

import lombok.Synchronized;
import lombok.experimental.UtilityClass;

import java.net.InetAddress;

@UtilityClass
public class Utils {
    private static String cachedHostname = null;

    @Synchronized
    public static String getHostname() {
        if (cachedHostname == null) {
            try {
                cachedHostname = InetAddress.getLocalHost().getHostName();
            } catch (Exception e) {
                cachedHostname = "Unknown Host";
            }
        }

        return cachedHostname;
    }
}
