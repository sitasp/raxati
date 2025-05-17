package org.sage.util;

import java.util.UUID;

public class IdGenerator {

    public static String newUuid() {
        return UUID.randomUUID().toString();
    }
}
