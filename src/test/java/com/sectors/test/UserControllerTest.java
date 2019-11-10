package com.sectors.test;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sectors.test.controller.UserController;
import com.sectors.test.dao.SectorDao;
import com.sectors.test.dao.UserDao;
import com.sectors.test.entity.Sector;
import com.sectors.test.entity.User;
import com.sectors.test.service.UserService;
import com.sun.tools.javac.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserDao dao;

  @MockBean
  private SectorDao sectorDao;

  @MockBean
  private UserService service;

  @Test
  public void doesNotFindIfNotExist() throws Exception {
    when(dao.exists("token")).thenReturn(false);
    mockMvc.perform(get("/users/token")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(""));
  }

  @Test
  public void canFind() throws Exception {
    when(dao.exists("token")).thenReturn(true);
    when(dao.find("token")).thenReturn(new User("token", "demo", LocalDateTime.of(2019, 11, 11,11,11,11)));
    when(sectorDao.search("token")).thenReturn(List.of(new Sector(1, "sector", 0)));

    mockMvc.perform(get("/users/token")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").value("token"))
        .andExpect(jsonPath("$.name").value("demo"))
        .andExpect(jsonPath("$.createdAt").value("2019-11-11T11:11:11"))
        .andExpect(jsonPath("$.sectorList", hasSize(1)))
        .andExpect(jsonPath("$.sectorList[0].id").value(1))
        .andExpect(jsonPath("$.sectorList[0].name").value("sector"))
        .andExpect(jsonPath("$.sectorList[0].parentId").value(0));
  }

  @Test
  public void canCreateOrUpdate() throws Exception {
    doNothing().when(service).createOrUpdate("token", "demo", List.of(1L));

    mockMvc.perform(post("/users")
        .content("{\"token\":\"token\", \"name\":\"demo\", \"sectorList\": [1]}")
        .contentType("application/json;charset=UTF-8"))
        .andDo(print())
        .andExpect(status().isOk());

    verify(service, times(1)).createOrUpdate("token", "demo", List.of(1L));
  }
}
