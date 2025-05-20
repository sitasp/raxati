package org.sage.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.sage.entity.PostEntity;
import org.sage.object.domain.Post;

@ApplicationScoped
@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostMapper implements BaseMapper<Post, PostEntity> {
}
