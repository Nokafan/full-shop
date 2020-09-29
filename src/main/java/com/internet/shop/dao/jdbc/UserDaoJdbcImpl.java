package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.UserDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import com.internet.shop.util.HashUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    @Override
    public User create(User user) {
        String query = "INSERT INTO users ("
                + "user_name, login, user_password, salt) VALUES (?, ?, ?, ?);";
        byte[] salt = HashUtil.getSalt();
        String saltedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, saltedPassword);
                preparedStatement.setBytes(4, salt);
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't create user", e);
        }
        return insertRoles(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ? AND user_deleted = FALSE; ";
        User tempUser = new User();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tempUser = getUserFomResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user with login=" + login, e);
        }
        tempUser.setRoles(requestRoles(tempUser.getId()));
        return Optional.of(tempUser);
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users "
                + "WHERE user_id = ? AND user_deleted = FALSE;";
        User tempUser = new User();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tempUser = getUserFomResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get user id=" + id, e);
        }
        tempUser.setRoles(requestRoles(id));
        return Optional.of(tempUser);
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users WHERE user_deleted = FALSE;";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFomResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get all users.", e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users  "
                + "SET user_name = ?, login = ?, user_password = ? "
                + "WHERE user_id = ? AND user_deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update user id=" + user.getId(), e);
        }
        return insertRoles(user);
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE users "
                + "SET user_deleted = TRUE "
                + "WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete user id=" + id, e);
        }
    }

    private User getUserFomResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("user_id");
        String name = resultSet.getString("user_name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("user_password");
        byte[] salt = resultSet.getBytes("salt");
        return new User(id, name, login, password, salt);
    }

    private Set<Role> requestRoles(long userId) {
        String query = "SELECT user_id, roles.role_id, roles.role_name "
                + "FROM user_roles "
                + "INNER JOIN roles USING (role_id)"
                + "WHERE user_roles.user_id = ? AND roles.role_deleted = FALSE;";
        Set<Role> roles = new HashSet<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    long roleId = resultSet.getLong("role_id");
                    String roleName = resultSet.getString("role_name");
                    roles.add(new Role(roleId, Role.RoleName.valueOf(roleName)));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get roles", e);
        }
        return roles;
    }

    private User insertRoles(User user) {
        deleteRoles(user.getId());
        String query = "INSERT INTO user_roles (user_id, role_id) "
                + "VALUES (?, (SELECT role_id FROM roles "
                + "WHERE role_name = ? AND role_deleted = FALSE));";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Role role : user.getRoles()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setLong(1, user.getId());
                    preparedStatement.setString(2, role.getRoleName().name());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't inject roles to DB.", e);
        }
        user.setRoles(requestRoles(user.getId()));
        return user;
    }

    private int deleteRoles(long userId) {
        String query = "DELETE FROM user_roles WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, userId);
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete roles from user_roles, "
                    + "for user id=" + userId, e);
        }
    }
}
