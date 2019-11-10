package com.sectors.test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sectors.test.dao.SectorUserDao;
import com.sectors.test.dao.UserDao;
import com.sectors.test.service.UserService;
import com.sun.tools.javac.util.List;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

  @Mock
  private UserDao dao;

  @Mock
  private SectorUserDao sectorUserDao;

  @InjectMocks
  private UserService service;

  @Test
  public void canUpdate() {
    MockitoAnnotations.initMocks(this);
    when(dao.exists("token")).thenReturn(true);
    doNothing().when(dao).update("token", "demo");
    doNothing().when(sectorUserDao).delete("token");
    doNothing().when(sectorUserDao).create("token", 1);

    service.createOrUpdate("token", "demo", List.of(1L));

    verify(dao, times(1)).update("token", "demo");
    verify(sectorUserDao, times(1)).delete("token");
    verify(sectorUserDao, times(1)).create("token", 1);
  }
}
