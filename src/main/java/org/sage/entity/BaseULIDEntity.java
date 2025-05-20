package org.sage.entity;

import com.github.f4b6a3.ulid.UlidFactory;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class BaseULIDEntity extends PanacheEntityBase implements Serializable{

    private static final UlidFactory ulidFactory = UlidFactory.newMonotonicInstance();

    @Id
    @Column(name = "id", length = 26)
    private String id;

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

    @PrePersist
    public void assignId(){
        if(Objects.isNull(id)){
            this.id = ulidFactory.create().toString();
        }
    }
}