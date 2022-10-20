package io.itsydv.quizapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.itsydv.quizapp.models.QuestionModel

// Annotates class to be a Room Database with a table (entity)
@Database(entities = [QuestionModel::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class QuestionsDatabase: RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao

    // using companion object to make the database a singleton
    companion object {
        @Volatile
        private var INSTANCE: QuestionsDatabase? = null

        fun getDatabase(context: Context): QuestionsDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionsDatabase::class.java,
                    "questions_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}