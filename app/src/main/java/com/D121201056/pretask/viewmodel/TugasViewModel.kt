package com.D121201056.pretask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.D121201056.pretask.data.TugasDatabase
import com.D121201056.pretask.model.Tugas
import com.D121201056.pretask.repository.TugasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TugasViewModel(application: Application):AndroidViewModel(application) {
    val readAllData:LiveData<List<Tugas>>
    val readAllDataPentingMendesak: LiveData<List<Tugas>>
    val readAllDataPentingTidakMendesak: LiveData<List<Tugas>>
    val readAllDataTidakPentingMendesak: LiveData<List<Tugas>>
    val readAllDataTidakPentingTidakMendesak: LiveData<List<Tugas>>

    private val repository:TugasRepository
    init {
        val tugasDao = TugasDatabase.getDatabase(application).tugasDao()
        repository = TugasRepository(tugasDao)
        readAllData = repository.readAllData
        readAllDataPentingMendesak = repository.readAllDataPentingMendesak
        readAllDataPentingTidakMendesak = repository.readAllDataPentingTidakMendesak
        readAllDataTidakPentingMendesak = repository.readAllDataTidakPentingMendesak
        readAllDataTidakPentingTidakMendesak = repository.readAllDataTidakPentingTidakMendesak

    }

    fun addTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTugas(tugas)
        }
    }
    fun updatetugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatetugas(tugas)
        }
    }
    fun deleteTugas(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTugas(tugas)
        }
    }
}