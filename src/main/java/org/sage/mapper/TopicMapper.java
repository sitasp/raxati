package org.sage.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
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
