package com.sectors.test.controller.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateOrUpdateUserRequest {

  @NotBlank
  private String token;

  @NotBlank
  private String name;

  @NotNull
  private List<Long> sectorList;

  public CreateOrUpdateUserRequest() {
  }

  public CreateOrUpdateUserRequest(String token, String name, List<Long> sectorList) {
    this.token = token;
    this.name = name;
    this.sectorList = sectorList;
  }

  public String getToken() {
    return token;
  }

  public String getName() {
    return name;
  }

  public List<Long> getSectorList() {
    return sectorList;
  }
}
