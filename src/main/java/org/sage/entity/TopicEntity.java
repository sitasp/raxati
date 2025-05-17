package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "topic")
@Data
public class TopicEntity {

    @Id
    @Column(name = "id")
    private String           id;

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

    @Nullable
    @Column(name = "is_deleted")
    private Boolean          isDeleted;

    @Column(name = "created_at")
    private LocalDateTime    createdAt;

    @Column(name = "updated_at")
    private LocalDateTime    updatedAt;

    @Nullable
    @Column(name = "deleted_at")
    private LocalDateTime    deletedAt;

    @Column(name = "created_by")
    private String           createdBy;

    @Column(name = "updated_by")
    private String           updatedBy;

    @Nullable
    @Column(name = "deleted_by")
    private String           deletedBy;
}
