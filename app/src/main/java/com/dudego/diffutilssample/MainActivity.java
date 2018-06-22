package com.dudego.diffutilssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button;
    TeamAdapter teamAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progress);
        teamAdapter = new TeamAdapter(getTeamList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(teamAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressBar.getVisibility() != View.VISIBLE) {
                    teamAdapter.updateTeamList(progressBar, updateTeamModal(teamAdapter.getTeamList()));
                }
            }
        });
    }

    public List<TeamModal> getTeamList() {
        List<TeamModal> list = new ArrayList<>();
        for (int i = 0; i<=1000; i++) {
            TeamModal teamModal = new TeamModal();
            teamModal.setCountry("Country" + i);
            teamModal.setId(i);
            teamModal.setLikes("Likes" + i);
            teamModal.setScore("Score" + i);
            list.add(teamModal);
        }
        return list;
    }

    public List<TeamModal> updateTeamModal(List<TeamModal> old) {
        List<TeamModal> newTeam = new ArrayList<>(old.size());
        for (int i = 0; i < old.size(); i++) {
            TeamModal teamModal = old.get(i);
            TeamModal newModal = new TeamModal();
            if (i%5 ==0) {
                newModal.setId(1000+i);
            }
            if (i%3==0) {
                newModal.setLikes(teamModal.getLikes() + "new" + i);
                newModal.setScore(teamModal.getScore() + "new" + i);
            } else {
                newModal.setScore(teamModal.getScore());
                newModal.setLikes(teamModal.getLikes());
            }
            newModal.setCountry(teamModal.getCountry());
            newTeam.add(newModal);
        }

        return newTeam;
    }

}
