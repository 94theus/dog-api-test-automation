package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetBreedsListRequest extends RequestRestBase {

    public GetBreedsListRequest() {
        requestService = "/breeds/list/all";
        method = Method.GET;
    }
}