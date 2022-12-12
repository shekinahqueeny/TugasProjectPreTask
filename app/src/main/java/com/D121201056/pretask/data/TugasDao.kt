package com.D121201056.pretask.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.D121201056.pretask.model.Tugas

@Dao
interface TugasDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTugas(tugas: Tugas)

    @Query("SELECT * FROM Tugas WHERE status='Selesai'")
    fun readAllData(): LiveData<List<Tugas>>

    @Query("SELECT * FROM Tugas WHERE kategori='Penting Mendesak' AND status='Belum Selesai'")
    fun readAllDataPentingMendesak(): LiveData<List<Tugas>>

    @Query("SELECT * FROM Tugas WHERE kategori='Penting Tidak Mendesak'AND status='Belum Selesai'")
    fun readAllDataPentingTidakMendesak(): LiveData<List<Tugas>>

    @Query("SELECT * FROM Tugas WHERE kategori='Tidak Penting Mendesak'AND status='Belum Selesai'")
    fun readAllDataTidakPentingMendesak(): LiveData<List<Tugas>>

    @Query("SELECT * FROM Tugas WHERE kategori='Tidak Penting Tidak Mendesak'AND status='Belum Selesai'")
    fun readAllDataTidakPentingTidakMendesak(): LiveData<List<Tugas>>

    @Update
    suspend fun updatetugas(tugas: Tugas)

    @Delete
    suspend fun deleteTugas(tugas: Tugas)
}