package com.martin.joukl.cv02martinjoukl.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    List<AppUser> findByActive(boolean active);

    @Query(value = "SELECT user FROM AppUser AS user JOIN user.roles AS role where role.name LIKE :listedRole")
    List<AppUser> listByRoleName(@Param("listedRole") String listedRole);
}
