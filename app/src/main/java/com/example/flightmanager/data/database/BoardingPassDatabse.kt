package com.example.flightmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.flightmanager.data.model.BoardingPassModel


@Database(
    entities = [BoardingPassModel::class],
    version = 3
)
 abstract class BoardingPassDatabse :RoomDatabase() {
    abstract fun boardingPassDao():BoardingPassDao


    companion object {
     @Volatile private var instant:BoardingPassDatabse?=null
        private val LOCK =Any()

        operator fun invoke(context: Context)= instant ?: synchronized(LOCK){
            instant ?: buildDatabase(context).also { instant =it }
        }
        fun buildDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,BoardingPassDatabse::class.java,"boarding_pass.db")
                .fallbackToDestructiveMigration()
                .build()
    }

}
val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) { // Since we didn't alter the table, there's nothing else to do here.
    }
}