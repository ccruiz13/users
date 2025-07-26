package com.aws.app.users.facade;

import com.aws.app.users.model.domain.Users;

public interface IUsersRepositorieFacade {

    Users save(Users users);
    Users findByIdentification(String identificacion);
}
