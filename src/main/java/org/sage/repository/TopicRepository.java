package org.sage.repository;


import com.github.f4b6a3.ulid.Ulid;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sage.entity.TopicEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TopicRepository {

    public Optional<TopicEntity> findBySlug(String slug) {
        return TopicEntity.find("slug = ?1 and (isDeleted is null or isDeleted = false)", slug)
                .firstResultOptional();
    }

    public Optional<TopicEntity> findById(String id){
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        Ulid ulid = Ulid.from(id);
        return TopicEntity.find("id = ?1 and (isDeleted is null or isDeleted = false)", ulid)
                .firstResultOptional();
    }
}
