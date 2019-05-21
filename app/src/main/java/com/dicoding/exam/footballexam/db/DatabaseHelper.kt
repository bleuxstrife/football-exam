package com.dicoding.exam.footballexam.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FootballExam.db", null, 1) {
    companion object {
        private var instance: DatabaseHelper?=null
        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if(instance==null){
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Match.TABLE_EVENT_FAVOURITE, true,
                Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Match.EVENT_ID to TEXT + UNIQUE,
                Match.HOME_TEAM to TEXT,
                Match.AWAY_TEAM to TEXT,
                Match.HOME_SCORE to TEXT,
                Match.AWAY_SCORE to TEXT,
                Match.HOME_GOAL_DETAILS to TEXT,
                Match.AWAY_GOAL_DETAILS to TEXT,
                Match.HOME_SHOTS to TEXT,
                Match.AWAY_SHOTS to TEXT,
                Match.HOME_GOAL_KEEPER to TEXT,
                Match.HOME_DEFENSE to TEXT,
                Match.HOME_MIDFIELD to TEXT,
                Match.HOME_FORWARD to TEXT,
                Match.HOME_SUBTITUTE to TEXT,
                Match.AWAY_GOAL_KEEPER to TEXT,
                Match.AWAY_DEFENSE to TEXT,
                Match.AWAY_MIDFIELD to TEXT,
                Match.AWAY_FORWARD to TEXT,
                Match.AWAY_SUBTITUTE to TEXT,
                Match.DATE_EVENT to TEXT,
                Match.ID_HOME_TEAM to TEXT,
                Match.ID_AWAY_TEAM to TEXT)
        db.createTable(Team.TABLE_TEAM_FAVORITE, true,
                Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Team.TEAM_ID to TEXT + UNIQUE,
                Team.TEAM_NAME to TEXT,
                Team.TEAM_BADGE to TEXT,
                Team.TEAM_FORMED_YEAR to TEXT,
                Team.TEAM_STADIUM to TEXT,
                Team.TEAM_DESCRIPTION to TEXT)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Match.TABLE_EVENT_FAVOURITE, true)
        db.dropTable(Team.TABLE_TEAM_FAVORITE, true)
    }

}
val Context.database: DatabaseHelper
    get()=DatabaseHelper.getInstance(applicationContext)