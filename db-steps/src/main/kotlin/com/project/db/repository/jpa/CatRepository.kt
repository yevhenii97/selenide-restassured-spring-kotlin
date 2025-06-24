package com.project.db.repository.jpa

import com.project.db.models.Cat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CatRepository: JpaRepository<Cat, Int>