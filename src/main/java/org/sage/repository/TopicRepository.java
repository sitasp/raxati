package org.sage.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sage.entity.TopicEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TopicRepository implements PanacheRepository<TopicEntity> {

    public Optional<TopicEntity> findBySlug(String slug) {
        return find("slug = ?1 and (is_deleted is null or is_deleted = false)", slug)
                .firstResultOptional();
    }
}
