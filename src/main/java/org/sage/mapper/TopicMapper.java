package org.sage.mapper;

import com.github.f4b6a3.ulid.Ulid;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.mapstruct.*;
import org.sage.object.domain.Topic;
import org.sage.entity.TopicEntity;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TopicMapper {

    List<Topic> toDomainList(List<TopicEntity> entities);

    Topic toDomain(TopicEntity topicEntity);

    @InheritInverseConfiguration(name = "toDomain")
    TopicEntity toEntity(Topic topic);

    void updateEntityFromDomain(Topic topic, @MappingTarget TopicEntity topicEntity);

    void updateDomainFromEntity(TopicEntity topicEntity, @MappingTarget Topic topic);

}
