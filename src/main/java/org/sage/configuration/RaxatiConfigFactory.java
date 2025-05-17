package org.sage.configuration;

import com.github.slugify.Slugify;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;


@ApplicationScoped
public class RaxatiConfigFactory {

    @Produces @Singleton
    public Slugify slugify() {
        return Slugify.builder().build();
    }
}
