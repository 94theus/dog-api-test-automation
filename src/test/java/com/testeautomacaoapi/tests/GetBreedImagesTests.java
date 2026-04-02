package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.requests.GetBreedImagesRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.empty;

public class GetBreedImagesTests extends TestBase {

    @Test
    public void deveRetornarImagensParaRacaValida() {

        //region Arrange
        String raca = "hound";
        int statusCodeEsperado = HttpStatus.SC_OK;
        //endregion

        //region Act
        GetBreedImagesRequest getBreedImagesRequest = new GetBreedImagesRequest(raca);
        ValidatableResponse response = getBreedImagesRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("status", equalTo("success"));
        response.body("message", notNullValue());
        response.body("message", not(empty()));
        response.body("message[0]", containsString("http"));
        //endregion
    }

    @Test
    public void deveRetornarErroParaRacaInvalida() {

        //region Arrange
        String raca = "racaInvalida123";
        //endregion

        //region Act
        GetBreedImagesRequest getBreedImagesRequest = new GetBreedImagesRequest(raca);
        ValidatableResponse response = getBreedImagesRequest.executeRequest();
        //endregion

        //region Assert
        response.body("status", notNullValue());
        response.body("message", notNullValue());
        //endregion
    }
}