package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "events")
@Data
public class EventEntity extends BaseULIDEntity {

    @Column(name = "name")
    public String   name;

    @Column(name = "details", columnDefinition = "TEXT")
    public String   details;

    @Column(name = "number_of_likes")
    public Long     numberOfLikes;

    @Column(name = "number_of_views")
    public Long     numberOfViews;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
    public TopicEntity      topic;

    @Column(name = "slug")
    public String           slug;

    @Column(name = "status")
    public String           status;
}