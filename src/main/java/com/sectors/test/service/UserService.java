package com.sectors.test.service;

import static java.time.LocalDateTime.now;
import static java.time.ZoneOffset.UTC;

import java.time.Clock;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sectors.test.dao.SectorUserDao;
import com.sectors.test.dao.UserDao;

@Component
public class UserService {

  @Autowired
  public Clock clock;

  @Autowired
  UserDao dao;

  @Autowired
  SectorUserDao sectorUserDao;

  public void createOrUpdate(String token, String name, List<Long> sectors) {
    if (dao.exists(token)) {
      dao.update(token, name);
      sectorUserDao.delete(token);
    }
    else {
      dao.create(token, name, now(clock));
    }
    sectors.forEach(id -> sectorUserDao.create(token, id));
  }
}
