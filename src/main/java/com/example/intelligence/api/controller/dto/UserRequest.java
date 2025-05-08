package com.example.intelligence.api.controller.dto;

import com.example.intelligence.service.dto.UserRequestService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    public UserRequestService userRequesttoServiceUserRequest() { return new UserRequestService(); }
}
