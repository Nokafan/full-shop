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
        if (optionalUser.isPresent() && isPasswordValid(optionalUser.get(), password)) {
            return optionalUser.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    private boolean isPasswordValid(User user, String password) {
        String saltedPassword = HashUtil.hashPassword(password, user.getSalt());
        return user.getPassword().equals(saltedPassword);
    }
}
