package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_db")
data class EmployeeDb(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var number: String,
    var email: String,
    var role: String,
    var imgId: Long

) {

}
