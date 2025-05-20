package org.sage.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "posts")
public class PostEntity extends BaseULIDEntity {

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private UserEntity      author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String          content;

    @ManyToOne
    @JoinColumn(name = "parent_post_id", referencedColumnName = "id")
    private PostEntity      parentPost;

    @ManyToOne
    @JoinColumn(name = "reshared_from_id", referencedColumnName = "id")
    private PostEntity      resharedFrom;

    @Column(name = "visibility")
    private String          visibility;

    @Column(name = "number_of_likes")
    public Long             numberOfLikes;

    @Column(name = "number_of_views")
    public Long             numberOfViews;

    @Column(name = "status")
    public String           status;

    @ElementCollection
    @CollectionTable(name = "post_media_urls", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "media_url")
    public List<String>     mediaUrls = new ArrayList<>();
}
