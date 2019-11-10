package com.sectors.test.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sectors.test.entity.Sector;

@Component
public class SectorDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Sector> findAll() {
    return jdbcTemplate.query("SELECT id, name, parent_id FROM sector s",
        (rs, num) -> new Sector(rs.getLong("id"), rs.getString("name"), rs.getLong("parent_id")));
  }

  public List<Sector> search(String userToken) {
    return jdbcTemplate.query("SELECT s.id, s.name, s.parent_id FROM sector s JOIN sector_user su ON su.sector_id = s.id JOIN \"user\" u ON su.user_id = u.id WHERE u.token = ?",
        (rs, num) -> new Sector(rs.getLong("id"), rs.getString("name"), rs.getLong("parent_id")),
        userToken);
  }
}
