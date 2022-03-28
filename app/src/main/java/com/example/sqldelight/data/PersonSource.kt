package com.example.sqldelight.data

import crashcourse.persondb.PesronEntity
import kotlinx.coroutines.flow.Flow

interface PersonSource {

    suspend fun getPersonById(id:Long): PesronEntity?

    fun getPerson(): Flow<List<PesronEntity>>

    suspend fun addPerson(
        id: Long?,
        firstName:String,
        age:Long
    )

    suspend fun deletePerson()

    suspend fun deletePersonById(id: Long)
}