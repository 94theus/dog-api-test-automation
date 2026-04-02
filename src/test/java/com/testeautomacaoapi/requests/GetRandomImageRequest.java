package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetRandomImageRequest extends RequestRestBase {

    public GetRandomImageRequest() {
        requestService = "/breeds/image/random";
        method = Method.GET;
    }
}