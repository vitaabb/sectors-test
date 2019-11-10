package com.sectors.test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sectors.test.controller.SectorController;
import com.sectors.test.dao.SectorDao;
import com.sectors.test.entity.Sector;
import com.sun.tools.javac.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(SectorController.class)
class SectorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SectorDao dao;

  @Test
  public void canGet() throws Exception {
    when(dao.findAll()).thenReturn(List.of(new Sector(1, "sector", 0)));
    mockMvc.perform(get("/sectors")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[*].id").value(1))
        .andExpect(jsonPath("$.[*].name").value("sector"))
        .andExpect(jsonPath("$.[*].parentId").value(0));
  }
}
