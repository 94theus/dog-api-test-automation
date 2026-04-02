package com.testeautomacaoapi.requests;

import com.testeautomacaoapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetBreedImagesRequest extends RequestRestBase {

    public GetBreedImagesRequest(String breed) {
        requestService = "/breed/" + breed + "/images";
        method = Method.GET;
    }
}