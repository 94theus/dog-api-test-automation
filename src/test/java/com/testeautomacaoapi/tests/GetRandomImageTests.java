package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.requests.GetRandomImageRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetRandomImageTests extends TestBase {

    @Test
    public void deveRetornarImagemAleatoriaComSucesso() {

        //region Arrange
        int statusCodeEsperado = HttpStatus.SC_OK;
        //endregion

        //region Act
        GetRandomImageRequest getRandomImageRequest = new GetRandomImageRequest();
        ValidatableResponse response = getRandomImageRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("status", equalTo("success"));
        response.body("message", notNullValue());
        response.body("message", containsString("http"));
        //endregion
    }
}