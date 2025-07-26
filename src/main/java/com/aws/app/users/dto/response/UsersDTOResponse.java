package com.aws.app.users.dto.response;

import com.aws.app.users.commons.annotations.FieldMapping;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.aws.ccamilo.com.app.retoawsusers.domain.model.Users}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDTOResponse implements Serializable {

    @FieldMapping("id")
    private Long idUsers;
    private String identificacion;
    private String nombre;
    private String email;
}
