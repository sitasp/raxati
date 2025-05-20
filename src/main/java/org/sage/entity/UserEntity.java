package org.sage.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
public class UserEntity extends BaseULIDEntity {

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
}
