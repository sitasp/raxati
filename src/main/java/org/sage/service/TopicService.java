package org.sage.service;


import com.github.slugify.Slugify;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sage.constant.RaxatiConstants;
import org.sage.entity.PostEntity;
import org.sage.entity.TopicEntity;
import org.sage.mapper.PostMapper;
import org.sage.mapper.TopicMapper;
import org.sage.object.domain.Post;
import org.sage.object.domain.Topic;
import org.sage.repository.TopicRepository;

import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;

    private final PostMapper postMapper;

    private final Slugify slugify;

    public Optional<Topic> fetchTopicBySlug(String slug){
        Optional<TopicEntity> topicEntityOptional = topicRepository.findBySlug(slug);
        if(topicEntityOptional.isPresent()){
            TopicEntity topicEntity = topicEntityOptional.get();
            return Optional.ofNullable(topicMapper.toDomain(topicEntity));
        }
        return Optional.empty();
    }

    public Optional<Topic> fetchTopicById(String id){
        Optional<TopicEntity> topicEntityOptional = topicRepository.findById(id);
        if(topicEntityOptional.isPresent()){
            TopicEntity topicEntity = topicEntityOptional.get();
            return Optional.ofNullable(topicMapper.toDomain(topicEntity));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Topic> saveTopic(Topic topic){
        try {
            validate(topic);
            preprocess(topic);

            TopicEntity entity = topicMapper.toEntity(topic);
            TopicEntity.persist(entity);

            Topic saved = topicMapper.toDomain(entity);

            return Optional.of(saved);
        } catch (IllegalArgumentException e) {
            log.error("Failed to save topic", e);
            return Optional.empty();
        }
        catch (Exception e) {
            log.error("Failed to save topic", e);
            throw e;
        }
    }

    public List<Post> fetchPostsUsingTopicId(String topicId) {
        Optional<TopicEntity> topicEntityOptional = topicRepository.findById(topicId);
        if(topicEntityOptional.isPresent()){
            List<PostEntity> postEntities = topicEntityOptional.get().getPosts();
            return postMapper.toDomainList(postEntities);
        }
        return new ArrayList<>();
    }

    private void preprocess(Topic topic) {
        String generatedSlug = slugify.slugify(topic.getTitle());
        topic.setSlug(generatedSlug);
        if(Objects.isNull(topic.getCreatedAt())){
            topic.setCreatedAt(LocalDateTime.now());
        }
        if(Objects.isNull(topic.getUpdatedAt())){
            topic.setUpdatedAt(LocalDateTime.now());
        }
        topic.setIsDeleted(false);
        if(Objects.isNull(topic.getCreatedBy())) {
            topic.setCreatedBy(RaxatiConstants.RAXATI_BOT);
        }
        if(Objects.isNull(topic.getUpdatedBy())) {
            topic.setUpdatedBy(RaxatiConstants.RAXATI_BOT);
        }
        topic.setNumberOfLikes(0L);
        topic.setNumberOfViews(0L);
        topic.setStatus("active");
    }

    private void validate(Topic topic) {
        if(Objects.isNull(topic)){
            throw new IllegalArgumentException("Topic cannot be empty");
        }
    }
}
