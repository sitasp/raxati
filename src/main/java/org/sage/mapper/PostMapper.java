package org.sage.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.mapstruct.*;
import org.sage.entity.PostEntity;
import org.sage.entity.UserEntity;
import org.sage.object.domain.Post;

import java.util.Objects;

@ApplicationScoped
@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostMapper implements BaseMapper<Post, PostEntity> {

    @AfterMapping
    protected void e2d(PostEntity entity, @MappingTarget Post domain) {
        UserEntity author = entity.getAuthor();
        if(Objects.nonNull(author)) {
            domain.setAuthorId(author.getId());
        }
        PostEntity parentPost = entity.getParentPost();
        if(Objects.nonNull(parentPost)) {
            domain.setParentPostId(parentPost.getId());
        }
        PostEntity resharedPost = entity.getResharedPost();
        if(Objects.nonNull(resharedPost)) {
            domain.setResharedPostId(resharedPost.getId());
        }
    }

    @AfterMapping
    @Transactional
    protected void d2e(Post domain, @MappingTarget PostEntity entity) {
        String authorId = domain.getAuthorId();
        if(Objects.nonNull(authorId)) {
            UserEntity author = UserEntity.findById(authorId);
            entity.setAuthor(author);
        }
        String parentPostId = domain.getParentPostId();
        if(Objects.nonNull(parentPostId)) {
            PostEntity parentPost = PostEntity.findById(parentPostId);
            entity.setParentPost(parentPost);
        }
        String resharedPostId = domain.getResharedPostId();
        if(Objects.nonNull(resharedPostId)) {
            PostEntity resharedPost = PostEntity.findById(resharedPostId);
            entity.setResharedPost(resharedPost);
        }
    }
}
