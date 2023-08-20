package com.socks.api;

import com.github.javafaker.Faker;
import com.socks.utils.ProjectConfig;
import com.socks.models.UserRegistrationDto;
import com.socks.enums.HttpStatus;
import com.socks.services.UserEndpoint;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    private final UserEndpoint userEndpoint = new UserEndpoint();
    private final Faker faker = new Faker();

    @BeforeClass(alwaysRun = true)
    public void setBaseUri() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
        RestAssured.baseURI = config.baseURI();
    }

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
            .username(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .password(faker.internet().password())
            .build();
    }
}