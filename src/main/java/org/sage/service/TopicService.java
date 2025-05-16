package org.sage.service;


import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.sage.entity.TopicEntity;
import org.sage.mapper.TopicMapper;
import org.sage.object.domain.Topic;
import org.sage.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;

    public Optional<Topic> fetchTopicBySlug(String slug){
        Optional<TopicEntity> topicEntityOptional = topicRepository.findBySlug(slug);
        if(topicEntityOptional.isPresent()){
            TopicEntity topicEntity = topicEntityOptional.get();
            return Optional.ofNullable(topicMapper.toDomain(topicEntity));
        }
        return Optional.empty();
    }
}
