package com.D121201056.pretask.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "Tugas")
data class Tugas (
    @PrimaryKey (autoGenerate = true)
    val id:Int,
    val judul:String,
    val deskripsi:String,
    val deadline:String,
    val kategori:String,
    val status:String
):Parcelable