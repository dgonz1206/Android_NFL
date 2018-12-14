package com.example.blippinbloop.fantasy_nfl_stats;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayer;

import java.util.List;

public class FantasyPlayerViewModel extends AndroidViewModel {
    private FantasyRepo repository;
    private LiveData<List<FantasyPlayer>> fantasyplayers;

    public  FantasyPlayerViewModel(@NonNull Application application){
        super(application);
        repository = new FantasyRepo(application);
        fantasyplayers = repository.loadTeam();
    }

    public void insert(String player_name){
        repository.insert(player_name);
    }
    public void update(FantasyPlayer player){
        repository.update(player);
    }
    public void delete(FantasyPlayer player){
        repository.delete(player);
    }

    public LiveData<List<FantasyPlayer>> getFantasyplayers() {
        return fantasyplayers;
    }
}
