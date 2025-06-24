package com.project.restapi.client

import io.restassured.RestAssured.given
import io.restassured.http.ContentType

abstract class AbstractApiClient {

    protected fun <T> getRequestToGetList(fullUrl: String, pathToExtract: String, referenceType: Class<T>) : MutableList<T>{
        return given()
            .`when`()
            .contentType(ContentType.JSON)
            .get(fullUrl)
            .then().log().all()
            .extract().body().jsonPath().getList(pathToExtract, referenceType)
    }

    protected fun <T, V> postRequest(fullUrl: String, body: V, responseType: Class<T>) : T{
        return given()
            .body(body)
            .`when`()
            .header("x-api-key","reqres-free-v1")
            .contentType(ContentType.JSON)
            .post(fullUrl)
            .then().log().all()
            .extract().`as`(responseType)
    }
}