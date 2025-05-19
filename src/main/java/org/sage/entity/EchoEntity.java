package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EchoEntity extends BaseULIDEntity {

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private UserEntity      author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String          content;

    @ManyToOne
    @JoinColumn(name = "parent_echo_id", referencedColumnName = "id")
    private EchoEntity      parentEcho;

    @ManyToOne
    @JoinColumn(name = "reshared_from_id", referencedColumnName = "id")
    private EchoEntity      resharedFrom;

    @Column(name = "visibility")
    private String          visibility;

    @ElementCollection
    @CollectionTable(name = "echo_media_urls", joinColumns = @JoinColumn(name = "echo_id"))
    @Column(name = "media_url")
    public List<String>     mediaUrls = new ArrayList<>();

    @Nullable
    @Column(name = "is_deleted")
    public Boolean          isDeleted;

    @Column(name = "created_at")
    public LocalDateTime    createdAt;

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
