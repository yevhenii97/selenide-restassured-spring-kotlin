package com.project.db.stepdefinitions

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.db.models.Cat
import com.project.db.repository.jpa.CatRepository
import io.cucumber.java.en.When
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DbSteps(
    private val catRepository: CatRepository,
    private val objectMapper: ObjectMapper
) {

    companion object{
        private val log: Logger = LoggerFactory.getLogger(DbSteps::class.java)
    }

    @When("Get all cats from DB")
    fun getAllCatsFromDb() {
       val cats: MutableList<Cat> = catRepository.findAll()

        val stringCats: String = objectMapper.writeValueAsString(cats)
        log.info(stringCats)
    }

}