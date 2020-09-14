package com.internet.shop.service.interfaces;

import com.internet.shop.model.User;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {
    Optional<User> findByLogin(String login);
}
