package com.dudego.diffutilssample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import static com.dudego.diffutilssample.TeamAdapter.KEY_LIKES;
import static com.dudego.diffutilssample.TeamAdapter.KEY_SCORE;

public class TeamDiffUtilCallback extends DiffUtil.Callback {

    private List<TeamModal> oldTeamList;
    private List<TeamModal> newTeamList;

    public TeamDiffUtilCallback(List<TeamModal> oldTeamList, List<TeamModal> newTeamList) {
        this.oldTeamList = oldTeamList;
        this.newTeamList = newTeamList;
    }

    @Override
    public int getOldListSize() {
        return oldTeamList != null ? oldTeamList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newTeamList != null ? newTeamList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;//newTeamList.get(newItemPosition).getId() == oldTeamList.get(oldItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newTeamList.get(newItemPosition).equals(oldTeamList.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        TeamModal newTeam = newTeamList.get(newItemPosition);
        TeamModal oldTeam = oldTeamList.get(oldItemPosition);
        Bundle diffBundle = new Bundle();
        if (!newTeam.getScore().equalsIgnoreCase(oldTeam.getScore())) {
            diffBundle.putString(KEY_SCORE, newTeam.getScore());
        }
        if (!newTeam.getLikes().equalsIgnoreCase(oldTeam.getLikes())) {
            diffBundle.putString(KEY_LIKES, newTeam.getLikes());
        }
        if (diffBundle.size() == 0) return null;
        return diffBundle;
    }


}
