package com.socks.services;

import com.socks.utils.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Slf4j
public abstract class AbstractWebEndpoint {

    protected static final Logger LOGGER = LogManager.getLogger();
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    protected <T> T extractAsDto(ValidatableResponse validatableResponse, Class<T> tClass) {
        return validatableResponse.extract().body().as(tClass, ObjectMapperType.JACKSON_2);
    }

    protected ValidatableResponse post(String endpointPath, Object object, Object... pathParam) {
        return RestAssured.given()
            .contentType(ContentType.JSON).filters(configureFilters())
            .body(object)
            .when()
            .post(endpointPath, pathParam)
            .then();
    }

    private List<Filter> configureFilters() {
        boolean enable = config.enableLoggerConfiguration();
        LOGGER.info("Configure logger {}", enable);
        return enable ? Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter())
            : Collections.emptyList();
    }
}
