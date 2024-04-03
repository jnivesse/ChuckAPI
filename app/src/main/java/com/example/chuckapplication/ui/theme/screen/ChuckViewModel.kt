package com.example.chuckapplication.ui.theme.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuckapplication.ui.theme.model.ChuckAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class ChuckViewModel: ViewModel() {
    var chucktext= mutableStateOf("Clique sur le kicker")
    fun loadData() {

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val newData = ChuckAPI.chuckSpeaksFrench()

                chucktext.value=newData
                launch(Dispatchers.Main) {
                }
            }catch(e: IOException) {
                println("catch")
                e.printStackTrace()
            }
        }
    }
}