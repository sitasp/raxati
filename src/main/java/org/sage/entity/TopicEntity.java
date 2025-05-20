package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "topics")
@Data
public class TopicEntity extends BaseULIDEntity {

    @Column(name = "title")
    private String           title;

    @Nullable
    @Column(name = "description", columnDefinition = "TEXT")
    private String           description;

    @Column(name = "number_of_views")
    private Long             numberOfViews;

    @Column(name = "number_of_likes")
    private Long             numberOfLikes;

    @Column(name = "slug")
    private String           slug;

    @Column(name = "status")
    private String           status;
}
