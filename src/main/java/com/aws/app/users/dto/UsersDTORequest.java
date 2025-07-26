package com.aws.app.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.aws.ccamilo.com.app.retoawsusers.domain.model.Users}
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDTORequest implements Serializable {

    private String identificacion;
    private String nombre;
    private String email;
}
