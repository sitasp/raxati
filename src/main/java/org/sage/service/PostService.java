package org.sage.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sage.constant.RaxatiConstants;
import org.sage.entity.PostEntity;
import org.sage.mapper.PostMapper;
import org.sage.object.domain.Post;
import org.sage.query.Queries;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class PostService {

    private final PostMapper postMapper;

    public Optional<Post> fetchPostById(String id) {
        Optional<PostEntity> postEntityOptional = PostEntity.find(Queries.PostQueries.POST_BY_ID, id)
                .firstResultOptional();
        if (postEntityOptional.isPresent()) {
            PostEntity postEntity = postEntityOptional.get();
            return Optional.ofNullable(postMapper.toDomain(postEntity));
        }
        return Optional.empty();
    }

    public List<Post> fetchPostsByUserId(String userId) {
        List<PostEntity> postEntities = PostEntity.find(Queries.PostQueries.POST_BY_USER_ID, userId)
                .list();
        return postMapper.toDomainList(postEntities);
    }

    @Transactional
    public Post createPost(Post post) {
        validate(post);
        preprocess(post);
        PostEntity postEntity = postMapper.toEntity(post);
        postEntity.persist();
        return postMapper.toDomain(postEntity);
    }

    private void preprocess(Post post) {
        if(Objects.isNull(post.getCreatedAt())){
            post.setCreatedAt(LocalDateTime.now());
        }
        if(Objects.isNull(post.getUpdatedAt())){
            post.setUpdatedAt(LocalDateTime.now());
        }
        post.setIsDeleted(false);
        if(Objects.isNull(post.getCreatedBy())) {
            post.setCreatedBy(RaxatiConstants.RAXATI_BOT);
        }
        if(Objects.isNull(post.getUpdatedBy())) {
            post.setUpdatedBy(RaxatiConstants.RAXATI_BOT);
        }
        post.setNumberOfLikes(0L);
        post.setNumberOfViews(0L);
        post.setStatus("active");
    }

    private void validate(Post post) {
        if(Objects.isNull(post)) {
            throw new IllegalArgumentException("Post cannot be null");
        }
    }
}
