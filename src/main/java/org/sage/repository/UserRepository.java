package org.sage.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.sage.entity.UserEntity;

import java.util.Optional;

@ApplicationScoped
public class UserRepository {

    public Optional<UserEntity> findUserById(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        return UserEntity.find("id = ?1 AND (isDeleted IS NULL OR isDeleted = false)", id)
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
