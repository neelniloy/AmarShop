package com.braineer.amarshopbyseo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.braineer.amarshopbyseo.daos.UserDao
import com.braineer.amarshopbyseo.entities.UserModel

@Database(entities = [ UserModel::class], version = 1)
abstract class AmarDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object {
        private var db: AmarDatabase? = null
        fun getDB(context: Context) : AmarDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AmarDatabase::class.java,
                    "amar_db"
                ).build()
                return db!!
            }
            return db!!
        }
    }
}