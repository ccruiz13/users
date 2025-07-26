package com.aws.app.users.facade.impl;

import com.aws.app.users.commons.constast.ErrorException;
import com.aws.app.users.exception.UserAlreadyExistsException;
import com.aws.app.users.exception.UserNotFoundException;
import com.aws.app.users.exception.UserPersistenceException;
import com.aws.app.users.facade.IUsersRepositorieFacade;
import com.aws.app.users.model.domain.Users;
import com.aws.app.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UsersRepositorieFacade implements IUsersRepositorieFacade {

    private final UsersRepository usersRepository;

    @Override
    public Users save(Users users) {
        try {
            log.info("save user {}", users);
            usersRepository.findByIdentificacion(users.getIdentificacion()).ifPresent(existing ->{
                throw new UserAlreadyExistsException(ErrorException.USER_WITH_IDENTIFICATION_EXISTS.getMessage() + users.getIdentificacion() + " ya existe.");
            });
            return usersRepository.save(users);
        } catch (Exception e) {
            log.error("Error al guardar el usuario: {}", users, e);
            throw new UserPersistenceException(ErrorException.USER_SAVE_ERROR_MESSAGE.getMessage(), e);
        }

    }

    @Override
    public Users findByIdentification(String identificacion) {
        try {
            log.info("ingreso a consultar");
            return usersRepository.findByIdentificacion(identificacion)
                    .orElseThrow(()-> new UserNotFoundException(ErrorException.USER_QUERY_ERROR_MESSAGE.getMessage() + identificacion));
        }catch (UserNotFoundException e) {
            throw  e;
        }
        catch (Exception e) {
            log.error("Error al consultar usuario por identificador: {}", identificacion, e);
            throw new UserPersistenceException(ErrorException.USER_QUERY_ERROR_MESSAGE.getMessage(), e);
        }
    }
}
