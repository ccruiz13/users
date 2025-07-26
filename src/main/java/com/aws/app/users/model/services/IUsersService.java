package com.aws.app.users.model.services;

import com.aws.app.users.dto.request.UsersDTORequest;
import com.aws.app.users.dto.response.UsersDTOResponse;

public interface IUsersService {
    void  saveUser(UsersDTORequest usersDTORequest);
    UsersDTOResponse findByIdentifier(String identifier);
}
