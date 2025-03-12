package org.noa.TheBeautyBarDemo.repository;

import org.noa.TheBeautyBarDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}