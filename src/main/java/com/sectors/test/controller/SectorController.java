package com.sectors.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sectors.test.dao.SectorDao;
import com.sectors.test.entity.Sector;

@RestController
@RequestMapping("sectors")
public class SectorController {

  @Autowired
  private SectorDao dao;

  @GetMapping
  public List<Sector> search() {
    return dao.findAll();
  }
}
