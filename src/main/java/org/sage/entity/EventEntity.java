package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "event")
@Data
public class EventEntity {

    @Id
    @Column(name = "id")
    public String   id;

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
    public TopicEntity topic;

    @Column(name = "slug")
    public String           slug;

    @Column(name = "status")
    public String           status;

    @Nullable
    @Column(name = "is_deleted")
    public Boolean          isDeleted;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime    updatedAt;

    @Nullable
    @Column(name = "deleted_at")
    public LocalDateTime    deletedAt;

    @Column(name = "created_by")
    public String           createdBy;

    @Column(name = "updated_by")
    public String           updatedBy;

    @Nullable
    @Column(name = "deleted_by")
    public String           deletedBy;
}