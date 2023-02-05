package com.revature.repository;

import com.revature.model.User;
import com.revature.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements DAO<User, Integer> {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);


    @Override
    public Optional<User> create(User user) {
        Optional<User> optionalUser = Optional.empty();

        try(Connection connection = ConnectionManager.getConnection()){
            String sql = "insert into users(email, password, address) values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getAddress());

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0) throw new SQLException("Create user failed, no rows affected.");

            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    optionalUser = getById(generatedKeys.getInt(1));
                }else{
                    throw new SQLException("Create user failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return optionalUser;
    }

    @Override
    public Optional<User> getById(Integer id) {
        Optional<User> optionalUser = Optional.empty();
        try(Connection connection = ConnectionManager.getConnection()) {
            String sql = "select * from users where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                optionalUser = Optional.of(new User()
                        .setId(rs.getInt(1))
                        .setEmail(rs.getString(2))
                        .setPassword(rs.getString(3))
                        .setAddress(rs.getString(4))
                );
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return optionalUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()) {
            String sql = "select * from users order by id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                users.add(new User()
                        .setId(rs.getInt(1))
                        .setEmail(rs.getString(2))
                        .setPassword(rs.getString(3))
                        .setAddress(rs.getString(4)));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return users;
    }

    @Override
    public Optional<User> updateById(User user, Integer id) {
        Optional<User> optionalUser = Optional.empty();
        try(Connection connection = ConnectionManager.getConnection()) {
            String sql = "update users set email = ?, password = ?, address = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getAddress());
            stmt.setInt(4, id);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0) throw new SQLException("Update user failed, no rows affected.");

            optionalUser = getById(id);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return optionalUser;
    }

    @Override
    public Optional<User> deleteById(Integer id) {
        Optional<User> optionalUser = getById(id);

        try(Connection connection = ConnectionManager.getConnection()) {
            if(!optionalUser.isPresent()) return optionalUser;

            String sql = "delete from users where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) throw new SQLException("Delete user failed, no rows affected.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return optionalUser;
    }
}
