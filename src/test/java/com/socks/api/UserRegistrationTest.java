package com.socks.api;

import com.github.javafaker.Faker;
import com.socks.enums.HttpStatus;
import com.socks.models.UserRegistrationDto;
import com.socks.services.UserEndpoint;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest {

    private final UserEndpoint userEndpoint = new UserEndpoint();
    private final Faker faker = new Faker();

    @Test
    public void testCanRegisterNewUser() {
        UserRegistrationDto newUser = buildUser();

        UserRegistrationDto actual = userEndpoint.createUser(newUser);
        Assert.assertNotNull(actual.getId(), "User wasn't created");
    }

    @Test
    public void testCanNotRegisterSameUserTwice() {
        UserRegistrationDto newUser = buildUser();

        userEndpoint.createUser(newUser);
        userEndpoint.createUser(newUser, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private UserRegistrationDto buildUser() {
        return UserRegistrationDto.builder()
            .withUsername(faker.name().fullName())
            .withEmail(faker.internet().emailAddress())
            .withPassword(faker.internet().password())
            .build();
    }
}