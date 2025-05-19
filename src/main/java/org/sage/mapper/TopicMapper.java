package org.sage.mapper;

import com.github.f4b6a3.ulid.Ulid;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.mapstruct.*;
import org.sage.object.domain.Topic;
import org.sage.entity.TopicEntity;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "cdi")
public interface TopicMapper {

    List<Topic> toDomainList(List<TopicEntity> entities);

    @Mapping(source = "id", target = "id", qualifiedByName = "ulidToString")
    Topic toDomain(TopicEntity topicEntity);

    @InheritInverseConfiguration(name = "toDomain")
    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUlid")
    TopicEntity toEntity(Topic topic);

    @Mapping(source = "id", target = "id", qualifiedByName = "stringToUlid")
    void updateEntityFromDomain(Topic topic, @MappingTarget TopicEntity topicEntity);

    @Mapping(source = "id", target = "id", qualifiedByName = "ulidToString")
    void updateDomainFromEntity(TopicEntity topicEntity, @MappingTarget Topic topic);

    @Named("ulidToString")
    static String ulidToString(Ulid ulid) {
        return ulid != null ? ulid.toString() : null;
    }

    @Named("stringToUlid")
    static Ulid stringToUlid(String id) {
        return id != null ? Ulid.from(id) : null;
    }

//    @AfterMapping
//    protected void e2d(TopicEntity entity, @MappingTarget Topic domain) {
//        if (Objects.nonNull(entity.getId())) {
//            domain.setId(entity.getId().toString());
//        }
//    }
//
//    @AfterMapping
//    @Transactional
//    protected void d2e(Topic domain, @MappingTarget TopicEntity entity) {
//        String topicId = domain.getId();
//        if (Objects.nonNull(topicId)) {
//            Ulid ulid = Ulid.from(topicId);
//            entity.setId(ulid);
//        }
//    }

}
