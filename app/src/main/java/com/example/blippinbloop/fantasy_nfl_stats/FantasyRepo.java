package com.example.blippinbloop.fantasy_nfl_stats;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayer;
import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayerDao;
import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayerDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FantasyRepo {

    private FantasyPlayerDao FantasyPlayerDao;
    private LiveData<List<FantasyPlayer>> fantasy_players;

    public FantasyRepo(Application application){

        FantasyPlayerDatabase database = FantasyPlayerDatabase.getDatabase(application);
        FantasyPlayerDao = database.FantasyPlayerDao();
        fantasy_players = FantasyPlayerDao.loadTeam();
    }

    public void insert(String player_name){
        new InsertFantasyPlayerAsyncTask(FantasyPlayerDao).execute(player_name);
    }

    public void delete(FantasyPlayer player){
        new FantasyPlayerDeleteTask(FantasyPlayerDao).execute(player);
    }

    public LiveData<List<FantasyPlayer>> loadTeam(){
        return this.fantasy_players;
    }

    public void update(FantasyPlayer player){
        new FantasyPlayerUpdateTask(FantasyPlayerDao).execute(player);
    }

    public void deleteAll(){
        new FantasyRepo.deleteFantasyTeam(FantasyPlayerDao).execute();
    }


    //insert team
    public static class loadFantasyTeam extends AsyncTask<Void, Void, Void> {
        private FantasyPlayerDao fantasyPlayerDao;
        private LiveData<List<FantasyPlayer>> fantasy_players;


        private loadFantasyTeam(FantasyPlayerDao ftdao, LiveData<List<FantasyPlayer>> ft_players){
            this.fantasyPlayerDao = ftdao;
            this.fantasy_players = ft_players;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fantasy_players = fantasyPlayerDao.loadTeam();
            return null;
        }
    }

    public static class deleteFantasyTeam extends AsyncTask<Void, Void, Void> {
        private FantasyPlayerDao fantasyPlayerDao;
        private LiveData<List<FantasyPlayer>> fantasy_players;


        private deleteFantasyTeam(FantasyPlayerDao ftdao){
            this.fantasyPlayerDao = ftdao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fantasyPlayerDao.deleteALL();
            return null;
        }
    }

    //insert player
    private static class InsertFantasyPlayerAsyncTask extends AsyncTask<String, Void,Void>{
        private FantasyPlayerDao fantasyPlayerDao;

        private InsertFantasyPlayerAsyncTask(FantasyPlayerDao ftdao){
            this.fantasyPlayerDao = ftdao;
        }


        @Override
        protected Void doInBackground(String... strings) {
            //fantasyPlayerDao.deleteALL();

            String player_name = strings[0];
            String myResults;
            FantasyPlayer player = new FantasyPlayer();

            try {
                myResults = NETWORKCALLS.getResponseFromHttpUrl(player_name);
                player = JSONUtilities.parsePlayer(myResults);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (player.getLname() != null) {
                fantasyPlayerDao.insert(player);
            }
            return null;
        }
    }

    //update
    public static class FantasyPlayerUpdateTask extends AsyncTask<FantasyPlayer, Void, Void>{
        private  FantasyPlayerDao fantasyPlayerdao;

        private FantasyPlayerUpdateTask(FantasyPlayerDao fantasyPlayerDao){
            this.fantasyPlayerdao = fantasyPlayerDao;
        }

        @Override
        protected Void doInBackground(FantasyPlayer... fantasyPlayer) {
            fantasyPlayerdao.update(fantasyPlayer[0]);
            return null;
        }
    }

    //delete
    public static class FantasyPlayerDeleteTask extends AsyncTask<FantasyPlayer, Void, Void>{
        private  FantasyPlayerDao fantasyPlayerdao;

        private FantasyPlayerDeleteTask(FantasyPlayerDao fantasyPlayerDao){
            this.fantasyPlayerdao = fantasyPlayerDao;
        }

        @Override
        protected Void doInBackground(FantasyPlayer... fantasyPlayer) {
            fantasyPlayerdao.delete(fantasyPlayer[0]);
            return null;
        }
    }
}

