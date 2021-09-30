package com.wzq.room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wzq.room.App
import com.wzq.room.room.dao.entity.Student
import com.wzq.room.room.dao.entity.StudentDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * ProjectName: Jetpack
 * Package: com.wzq.room.room.dao
 * ClassName: MyDB
 * Description: java类作用描述
 * Author: WZQ
 * CreateDate: 2021/9/30 14:28
 * Version: 1.0
 */
@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao


    companion object {
        val appDatabase by lazy {
            Room.databaseBuilder(App.getInstall(), AppDatabase::class.java, "my_db")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()

        }
    }

}