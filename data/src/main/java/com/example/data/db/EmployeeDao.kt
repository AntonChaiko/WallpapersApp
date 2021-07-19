package com.example.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface EmployeeDao {
    @Query("select * from employee_db")
    fun getEmployees(): LiveData<List<EmployeeDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(employees: List<EmployeeDb>)

    @Query("DELETE FROM employee_db")
    fun nukeTable()
}