package com.sectors.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sectors.test.controller.request.CreateOrUpdateUserRequest;
import com.sectors.test.dao.SectorDao;
import com.sectors.test.dao.UserDao;
import com.sectors.test.entity.User;
import com.sectors.test.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

  @Autowired
  UserDao dao;

  @Autowired
  SectorDao sectorDao;

  @Autowired
  UserService service;

  @GetMapping("{token}")
  public User find(@PathVariable String token) {
    if (!dao.exists(token)) {
      return null;
    }

    User user = dao.find(token);
    user.setSectorList(sectorDao.search(token));

    return user;
  }

  @PostMapping
  public void createOrUpdate(@Valid @RequestBody CreateOrUpdateUserRequest request) {
    service.createOrUpdate(request.getToken(), request.getName(), request.getSectorList());
  }
}
