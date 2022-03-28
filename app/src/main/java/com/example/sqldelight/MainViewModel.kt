package com.example.sqldelight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelight.data.PersonSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personSource: PersonSource
):ViewModel() {

    val getPerson = personSource.getPerson()

    fun addPerson(
        id: Long?,
        firstName:String,
        age:Long
    ){
        viewModelScope.launch {
            personSource.addPerson(id, firstName, age)
        }
    }

    fun deletePersonById(id: Long){
        viewModelScope.launch {
            personSource.deletePersonById(id)
        }
    }

    fun deletePerson(){
        viewModelScope.launch {
            personSource.deletePerson()
        }
    }
}