package com.ads.orm.Spring_Orm.Repositories;

import com.ads.orm.Spring_Orm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,Integer> {
}
