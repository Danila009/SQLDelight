package com.example.sqldelight.data

import com.plcoding.sqldelightcrashcourse.PersonDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import crashcourse.persondb.PesronEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PersonSourceImpl(
    db: PersonDatabase
): PersonSource {

    private val queries = db.pesronEntityQueries

    override suspend fun getPersonById(id: Long): PesronEntity {
        return withContext(Dispatchers.IO){
            queries.getPersonId(id).executeAsOne()
        }
    }

    override fun getPerson(): Flow<List<PesronEntity>> {
        return queries.getPerson().asFlow().mapToList()
    }

    override suspend fun addPerson(id: Long?, firstName: String, age: Long) {
        withContext(Dispatchers.IO){
            queries.insertReson(id, firstName, age)
        }
    }

    override suspend fun deletePerson() {
        withContext(Dispatchers.IO){
            queries.deletePerson()
        }
    }

    override suspend fun deletePersonById(id: Long) {
        withContext(Dispatchers.IO){
            queries.deletePersonId(id)
        }
    }
}