package org.sage.entity;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidFactory;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.sage.configuration.ULIDBinaryConverter;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseULIDEntity extends PanacheEntityBase implements Serializable{

    private static final UlidFactory ulidFactory = UlidFactory.newMonotonicInstance();

    @Id
    @Convert(converter = ULIDBinaryConverter.class)
    @Column(name = "id", columnDefinition = "BYTEA")
    private Ulid id;

    @Column(name = "id_string", length = 26, nullable = false, unique = true)
    private String idString;

    @PrePersist
    public void assignId(){
        if(Objects.isNull(id)){
            this.id = ulidFactory.create();
            this.idString = this.id.toString();
        }
    }

    public Ulid getId() {
        return id;
    }

    public void setId(Ulid id) {
        this.id = id;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }
}