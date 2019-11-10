package com.sectors.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SectorUserDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void create(String userToken, long id) {
    jdbcTemplate.update("INSERT INTO sector_user AS su (sector_id, user_id) VALUES " +
        "(?, (SELECT id FROM \"user\" WHERE token = ?))", id, userToken);
  }

  public void delete(String userToken) {
    jdbcTemplate.update("DELETE FROM sector_user su USING \"user\" u WHERE su.user_id = u.id AND u.token = ?", userToken);
  }
}
