package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "topic")
@Data
public class TopicEntity {
    @Id
    public String           id;
    public String           title;
    @Nullable
    public String           description;
    public Long             numberOfViews;
    public Long             numberOfLikes;
    public String           slug;
    public String           status;
    @Nullable
    public Boolean          isDeleted;
    public LocalDateTime    createdAt;
    public LocalDateTime    updatedAt;
    @Nullable
    public LocalDateTime    deletedAt;
    public String           createdBy;
    public String           updatedBy;
    @Nullable
    public String           deletedBy;
}
