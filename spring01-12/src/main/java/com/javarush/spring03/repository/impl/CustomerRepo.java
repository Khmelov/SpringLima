package com.javarush.spring03.repository.impl;

import com.javarush.spring03.entity.Customer;
import com.javarush.spring03.repository.ConnectionPool;
import com.javarush.spring03.repository.Repo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Slf4j
public class CustomerRepo implements Repo<Customer> {

    public static final String GET_BY_ID = """
            SELECT id, login, password
            FROM users
            WHERE id=?
            """;

    public static final String UPDATE = """
            UPDATE users
            SET login = ?,
                password = ?
            WHERE id = ?
            """;

    private final ConnectionPool pool;

    public CustomerRepo(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    @SneakyThrows
    public Customer getById(Long id) {
        Connection connection = pool.getConnection();
        try (connection) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                return customer;
            }
            throw new RuntimeException("incorrect id");
        }
    }

    @Override
    @SneakyThrows
    public void update(Customer entity) {
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
        }
    }
}
