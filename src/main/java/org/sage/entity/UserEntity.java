package org.sage.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "user")
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    public UUID             id;

    @Column(name = "user_name", unique = true, nullable = false)
    public String           userName;

    @Column(name = "name")
    public String           name;

    @Column(name = "email", unique = true, nullable = false)
    public String           email;

    @Column(name = "mobile_number")
    public String           mobileNumber;

    @Column(name = "location")
    public String           location;

    @Column(name = "profile_picture_url")
    public String           profilePictureUrl;

    @Column(name = "bio")
    public String           bio;

    @Column(name = "is_deleted")
    public Boolean          isDeleted;

    @Column(name = "created_at")
    public LocalDateTime    createdAt;

    @Column(name = "updated_at")
    public LocalDateTime    updatedAt;


    @Column(name = "deleted_at", nullable = true)
    public LocalDateTime    deletedAt;

    @Column(name = "created_by")
    public String           createdBy;

    @Column(name = "updated_by")
    public String           updatedBy;

    @Nullable
    @Column(name = "deleted_by")
    public String           deletedBy;
}
