package com.sectors.test.entity;

public class Sector {

  private long id;
  private String name;
  private long parentId;

  public Sector(long id, String name, long parentId) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public long getParentId() {
    return parentId;
  }
}
