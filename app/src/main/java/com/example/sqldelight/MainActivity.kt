package com.example.sqldelight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val getPersonCheck = remember { mutableStateOf(false) }
            val firstName = remember { mutableStateOf("") }
            val age = remember { mutableStateOf("") }

            mainViewModel = hiltViewModel()

            val person = mainViewModel.getPerson
                .collectAsState(initial = emptyList())
                .value

            if (getPersonCheck.value){
                Column {
                    OutlinedButton(
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            getPersonCheck.value = false
                        }) {
                        Text(text = "<- Get person")
                    }

                    LazyColumn(content = {
                        items(person){ item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .border(1.dp, Color.Black),
                                shape = AbsoluteRoundedCornerShape(15.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "id: ${item.id}",
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "firstName: ${item.firstName}",
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "age: ${item.age}",
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Row {
                                       TextButton(onClick = {
                                           mainViewModel.deletePersonById(item.id)
                                       }) {
                                           Text(text = "Delete")
                                       }
                                    }
                                }
                                Divider()
                            }
                        }
                    })
                }
            }else{
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        modifier = Modifier.padding(5.dp),
                        value = firstName.value,
                        onValueChange = { firstName.value = it },
                        placeholder = {
                            Text(text = "firstName")
                        }
                    )

                    TextField(
                        modifier = Modifier.padding(5.dp),
                        value = age.value,
                        onValueChange = { age.value = it },
                        placeholder = {
                            Text(text = "age")
                        }
                    )

                    OutlinedButton(
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            mainViewModel.addPerson(
                                id = null,
                                firstName = firstName.value,
                                age = age.value.toLong()
                            )
                        }) {
                        Text(text = "Add")
                    }

                    OutlinedButton(
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            getPersonCheck.value = true
                        }) {
                        Text(text = "Get person ->")
                    }
                }
            }
        }
    }
}
