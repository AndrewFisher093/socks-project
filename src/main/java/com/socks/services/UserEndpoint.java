package com.socks.services;

import com.socks.enums.HttpStatus;
import com.socks.models.UserRegistrationDto;
import io.restassured.response.ValidatableResponse;

public class UserEndpoint extends AbstractWebEndpoint {

    private static final String CREATE_USER = "register";

    public UserRegistrationDto createUser(UserRegistrationDto userRegistrationDto) {
        ValidatableResponse validatableResponse = createUser(userRegistrationDto, HttpStatus.OK);
        return extractAsDto(validatableResponse, UserRegistrationDto.class);
    }

    public ValidatableResponse createUser(UserRegistrationDto userRegistrationDto, HttpStatus status) {
        return post(CREATE_USER, userRegistrationDto).statusCode(status.getCode());
    }
}
