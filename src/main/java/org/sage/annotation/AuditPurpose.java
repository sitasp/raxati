package org.sage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Marker annotation to indicate that the field is used for auditing or readability,
 * typically storing the string form of a foreign key.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface AuditPurpose {
    /**
     * Optional reason or explanation for storing the string version of a foreign key.
     */
    String reason() default "";
}
