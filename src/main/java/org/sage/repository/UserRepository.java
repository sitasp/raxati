package org.sage.repository;

import com.github.f4b6a3.ulid.Ulid;
import io.vertx.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.sage.entity.UserEntity;

import java.util.Optional;

@ApplicationScoped
public class UserRepository {

    public Optional<UserEntity> findUserById(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        Ulid ulid = Ulid.from(id);
        return UserEntity.find("id = ?1 AND (isDeleted IS NULL OR isDeleted = false)", ulid)
                .firstResultOptional();
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return UserEntity.find("email = ?1 AND (isDeleted IS NULL OR isDeleted = false)", email)
                .firstResultOptional();
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return UserEntity.find("userName = ?1 AND (isDeleted IS NULL OR isDeleted = false)", username)
                .firstResultOptional();
    }
}
