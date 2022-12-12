package com.D121201056.pretask.repository

import androidx.lifecycle.LiveData
import com.D121201056.pretask.data.TugasDao
import com.D121201056.pretask.model.Tugas

class TugasRepository(private val tugasDao: TugasDao) {
    val readAllData: LiveData<List<Tugas>> = tugasDao.readAllData()
    val readAllDataPentingMendesak: LiveData<List<Tugas>> = tugasDao.readAllDataPentingMendesak()
    val readAllDataPentingTidakMendesak: LiveData<List<Tugas>> = tugasDao.readAllDataPentingTidakMendesak()
    val readAllDataTidakPentingMendesak: LiveData<List<Tugas>> = tugasDao.readAllDataTidakPentingMendesak()
    val readAllDataTidakPentingTidakMendesak: LiveData<List<Tugas>> = tugasDao.readAllDataTidakPentingTidakMendesak()

    suspend fun addTugas(tugas:Tugas){
        tugasDao.addTugas(tugas)
    }

    suspend fun updatetugas(tugas: Tugas){
        tugasDao.updatetugas(tugas)
    }

    suspend fun deleteTugas(tugas: Tugas){
        tugasDao.deleteTugas(tugas)
    }
}