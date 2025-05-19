package org.sage.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import org.sage.object.domain.Topic;
import org.sage.entity.TopicEntity;
import org.sage.mapper.TopicMapper;
import org.sage.object.response.LandingPageResponse;
import org.sage.repository.TopicRepository;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class HomeService {

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;

    public LandingPageResponse getTopicsForHomepage() {
        List<TopicEntity> fetchedTopicEntities = TopicEntity.findAll().list();
        List<Topic> fetchedTopics = topicMapper.toDomainList(fetchedTopicEntities);
        LandingPageResponse response = new LandingPageResponse();
        response.setLiveNews(fetchedTopics);
        response.setArchivedTopics(fetchedTopics);
        return response;
    }
}
