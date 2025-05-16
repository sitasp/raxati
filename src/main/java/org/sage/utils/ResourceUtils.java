package org.sage.utils;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

public class ResourceUtils {
    public static <T> Response requireOrBadRequest(Optional<T> opt, String message) {
        return opt
                .map(value -> Response.ok(value).build())
                .orElseThrow(() -> new BadRequestException(message));
    }
}
