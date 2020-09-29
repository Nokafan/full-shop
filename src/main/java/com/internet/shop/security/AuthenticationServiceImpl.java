package com.internet.shop.security;

import com.internet.shop.exceptions.AuthenticationException;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import com.internet.shop.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByLogin(login);
        byte[] salt = optionalUser.get().getSalt();
        String saltedPassword = HashUtil.hashPassword(password, salt);
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(saltedPassword)) {
            return optionalUser.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
