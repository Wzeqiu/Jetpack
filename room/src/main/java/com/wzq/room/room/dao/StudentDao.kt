package com.wzq.room.room.dao.entity

import androidx.paging.PagingSource
import androidx.room.*

/**
 *
 * ProjectName: Jetpack
 * Package: com.wzq.room.room.dao.entity
 * ClassName: StudentDao
 * Description: java类作用描述
 * Author: WZQ
 * CreateDate: 2021/9/30 11:45
 * Version: 1.0
 */
@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudents(vararg students: Student)

    @Update
    fun updateStudents(vararg students: Student)

    @Delete
    fun deleteStudents(vararg student: Student)

    @Query("SELECT * FROM  student")
    fun query(): PagingSource<Int, Student>

    @Query("SELECT * FROM  student WHERE age > :age")
    fun queryAge(age: Int): Array<Student>

}