package com.socks.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "with")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserRegistrationDto {

    @JsonProperty(value = "password", access = JsonProperty.Access.READ_WRITE)
    private String password;
    @JsonProperty(value = "email", access = JsonProperty.Access.READ_WRITE)
    private String email;
    @JsonProperty(value = "username", access = JsonProperty.Access.READ_WRITE)
    private String username;
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_WRITE)
    private String id;
}
