package com.zjl.comp.util;

import java.util.UUID;

public class UUIDUtil {

    public UUIDUtil() {
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
