package org.sage.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sage.entity.TopicEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TopicRepository implements PanacheRepository<TopicEntity> {

    public Optional<TopicEntity> findBySlug(String slug) {
        return find("slug = ?1 and (isDeleted is null or isDeleted = false)", slug)
                .firstResultOptional();
    }

    public Optional<TopicEntity> findById(String id){
        return find("id = ?1 and (isDeleted is null or isDeleted = false)", id)
                .firstResultOptional();
    }
}
