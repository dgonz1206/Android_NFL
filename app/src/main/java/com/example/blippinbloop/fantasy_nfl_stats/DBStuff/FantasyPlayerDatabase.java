package com.example.blippinbloop.fantasy_nfl_stats.DBStuff;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {FantasyPlayer.class}, version = 1)
public abstract class FantasyPlayerDatabase extends RoomDatabase {
    private static FantasyPlayerDatabase instance;

    public abstract FantasyPlayerDao FantasyPlayerDao();

    public static synchronized FantasyPlayerDatabase getDatabase(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FantasyPlayerDatabase.class, "FantasyPlayer_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
