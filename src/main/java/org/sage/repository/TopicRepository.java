package org.sage.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sage.entity.TopicEntity;

@ApplicationScoped
public class TopicRepository implements PanacheRepository<TopicEntity> {
}
