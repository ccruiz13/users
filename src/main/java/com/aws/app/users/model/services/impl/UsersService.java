package com.aws.app.users.model.services.impl;

import com.aws.app.users.commons.mapper.EntityDTOBuilder;
import com.aws.app.users.dto.request.UsersDTORequest;
import com.aws.app.users.dto.response.UsersDTOResponse;
import com.aws.app.users.facade.IUsersRepositorieFacade;
import com.aws.app.users.model.domain.Users;
import com.aws.app.users.model.services.IUsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService implements IUsersService {

    private final IUsersRepositorieFacade repositorieFacade;

    @Override
    public void saveUser(UsersDTORequest usersDTORequest) {
        Users users  = EntityDTOBuilder.mapDtoToEntity(usersDTORequest, Users.class);
        repositorieFacade.save(users);
    }

    @Override
    public UsersDTOResponse findByIdentifier(String identifier) {
        Users users  = repositorieFacade.findByIdentification(identifier);
        return EntityDTOBuilder.mapEntityToDto(users, UsersDTOResponse.class);
    }
}
