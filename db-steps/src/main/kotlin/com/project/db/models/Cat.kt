package com.project.db.models

import lombok.ToString
import org.codehaus.jackson.annotate.JsonProperty
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "cats")
@ToString
data class Cat (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    val name: String = "",
    val age: Int = 0,
    val weight: Int = 0,
    @JsonProperty("create_date")
    @Column(name = "create_date")
    val createDate: LocalDateTime? = null
)
