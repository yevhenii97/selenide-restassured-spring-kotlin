package com.project.restapi.stepdefinitions

import com.project.context.ScenarioContext
import com.project.restapi.client.ReqresClient
import com.project.restapi.models.CreateUserPayload
import com.project.restapi.models.User
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ReqresApiSteps(
    private val reqresClient: ReqresClient,
    private val scenarioContext: ScenarioContext
) {

    companion object{
        private val log: Logger = LoggerFactory.getLogger(ReqresApiSteps::class.java)
    }

    @When("Send request to service to get list of users")
    fun getInitialData() {
        val users:MutableList<User> = reqresClient.sendRequestToGetListUsers()
        log.info("Got users: {}", users)

        scenarioContext.put("LIST_USERS", users)
    }

    @When("Verify that list contains data")
    fun verifyThatListContainsData(values: MutableList<MutableMap<String,String>>) {

        val expectedUsersList: MutableList<User> = mutableListOf();

        values.forEach { e->
            expectedUsersList.add(
                User(
                    id = e["id"]!!.toInt(),
                    email = e["email"]!!,
                    first_name = e["firstName"]!!,
                    last_name = e["lastName"]!!,
                    avatar = e["avatar"]!!
                )
            )
        }

        val actualUsers = scenarioContext["LIST_USERS"] as List<*>

        Assertions.assertThat(expectedUsersList)
            .`as`("Actual list of users does not contain expected")
            .isEqualTo(actualUsers)
    }

    @When("Create user with values")
    fun createUserWithValues(values: MutableList<MutableMap<String,String>>) {

        val createUserPayload = CreateUserPayload(
            name = values[0]["name"]!!,
            job = values[0]["job"]!!
        )

        val response = reqresClient.sendCreateRequestToService(createUserPayload)
        log.info("Response: {}", response)

    }
}