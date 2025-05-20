package org.sage.mapper;

import org.mapstruct.*;
import org.sage.object.domain.Topic;
import org.sage.entity.TopicEntity;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TopicMapper extends BaseMapper<Topic, TopicEntity>{
}
