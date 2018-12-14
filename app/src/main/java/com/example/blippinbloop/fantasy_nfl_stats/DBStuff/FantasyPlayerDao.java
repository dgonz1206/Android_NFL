package com.example.blippinbloop.fantasy_nfl_stats.DBStuff;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FantasyPlayerDao {
    @Query("SELECT * FROM Fantasy_Player")
    LiveData<List<FantasyPlayer>> loadTeam();

    @Insert
    void insert(FantasyPlayer player);

    @Delete
    void delete(FantasyPlayer player);

    @Update
    void update(FantasyPlayer player);

    @Query("DELETE FROM Fantasy_Player")
    void deleteALL();
}
