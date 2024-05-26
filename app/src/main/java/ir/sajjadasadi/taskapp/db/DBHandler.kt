package ir.sajjadasadi.taskapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.sajjadasadi.taskapp.db.dao.TaskDAO
import ir.sajjadasadi   .taskapp.db.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = DBHandler.DATABASE_VERSION
)
abstract class DBHandler : RoomDatabase() {

    abstract fun taskDao(): TaskDAO

    companion object {

        private const val DATABASE_NAME = "main_database"
        const val DATABASE_VERSION = 1

        const val TASK_TABLE = "taskTable"

        private var INSTANCE: DBHandler? = null

        fun getDatabase(context: Context): DBHandler {

            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    context,
                    DBHandler::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return INSTANCE!!

        }

    }

}