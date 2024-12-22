package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MataKuliah")
data class MataKuliah (
    @PrimaryKey
    val kode: String,
    val Nama: String,
    val Sks: String,
    val Semester: String,
    val Jenis: String,
    val DosenPengampu: String
)