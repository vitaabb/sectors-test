package com.sectors.test.dao;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sectors.test.entity.User;

@Component
public class UserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public User find(String token) {
    return requireNonNull(jdbcTemplate.queryForObject(
        "SELECT token, name, created_at FROM \"user\" WHERE token = ?",
        (row, num) -> new User(row.getString("token"), row.getString("name"), row.getObject("created_at", LocalDateTime.class)),
        token));
  }

  public boolean exists(String token) {
    return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT 1 FROM \"user\" WHERE token = ?)", Boolean.class, token);
  }

  public void create(String token, String name, LocalDateTime createdAt) {
    jdbcTemplate.update("INSERT INTO \"user\" (name, token, created_at) VALUES (?, ?, ?)", name, token, createdAt);
  }

  public void update(String token, String name) {
    jdbcTemplate.update("UPDATE \"user\" SET name = ? WHERE token = ?", name, token);
  }
}
