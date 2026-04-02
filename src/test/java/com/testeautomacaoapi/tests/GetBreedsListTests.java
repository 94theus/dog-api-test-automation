package com.testeautomacaoapi.tests;

import com.testeautomacaoapi.bases.TestBase;
import com.testeautomacaoapi.requests.GetBreedsListRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;

public class GetBreedsListTests extends TestBase {

    @Test
    public void deveRetornarListaDeRacasComSucesso() {

        //region Arrange
        int statusCodeEsperado = HttpStatus.SC_OK;
        //endregion

        //region Act
        GetBreedsListRequest getBreedsListRequest = new GetBreedsListRequest();
        ValidatableResponse response = getBreedsListRequest.executeRequest();
        //endregion

        //region Assert
        response.statusCode(statusCodeEsperado);
        response.body("status", equalTo("success"));
        response.body("message", notNullValue());
        response.body("message.keySet()", hasItems("hound", "bulldog", "retriever"));
        //endregion
    }
}