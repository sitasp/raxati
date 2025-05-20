package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.sage.annotation.AuditPurpose;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PostEntity extends BaseULIDEntity {

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private UserEntity      author;

    @Column(name = "author_id_string", length = 26)
    @AuditPurpose(reason = "string form of FK for author_id")
    private String          authorIdString;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String          content;

    @ManyToOne
    @JoinColumn(name = "parent_post_id", referencedColumnName = "id")
    private PostEntity      parentPost;

    @Column(name = "parent_post_id_string", length = 26)
    @AuditPurpose(reason = "string form of FK for parent_post_id")
    private String          parentPostIdString;

    @ManyToOne
    @JoinColumn(name = "reshared_from_id", referencedColumnName = "id")
    private PostEntity      resharedFrom;

    @Column(name = "reshared_from_id_string", length = 26)
    @AuditPurpose(reason = "string form of FK for reshared_from_id")
    private String          resharedFromIdString;

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
