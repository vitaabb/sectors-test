package com.sectors.test.entity;

import java.time.LocalDateTime;
import java.util.List;

public class User {

  private String token;
  private String name;
  private LocalDateTime createdAt;
  private List<Sector> sectorList;

  public User() {
  }

  public User(String token, String name, LocalDateTime createdAt) {
    this(token, name, createdAt, null);
  }

  public User(String token, String name, LocalDateTime createdAt, List<Sector> sectorList) {
    this.token = token;
    this.name = name;
    this.createdAt = createdAt;
    this.sectorList = sectorList;
  }

  public String getToken() {
    return token;
  }

  public String getName() {
    return name;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public List<Sector> getSectorList() {
    return sectorList;
  }

  public void setSectorList(List<Sector> sectorList) {
    this.sectorList = sectorList;
  }
}
