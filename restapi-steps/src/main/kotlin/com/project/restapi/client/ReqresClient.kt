package com.project.restapi.client

import com.project.restapi.models.CreateUserPayload
import com.project.restapi.models.CreateUserResponse
import com.project.restapi.models.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ReqresClient : AbstractApiClient() {

    @Value("\${rest.api.base-url}")
    private lateinit var baseUrl: String
    @Value("\${rest.api.list-users}")
    private lateinit var getUsers: String
    @Value("\${rest.api.create-user}")
    private lateinit var createUser: String

    fun sendRequestToGetListUsers(): MutableList<User> {
        return getRequestToGetList(baseUrl + getUsers, "data", User::class.java)
    }

    fun sendCreateRequestToService(body: CreateUserPayload): CreateUserResponse {
        return postRequest(baseUrl + createUser, body, CreateUserResponse::class.java)
    }
}