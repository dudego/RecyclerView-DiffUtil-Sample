package com.dudego.diffutilssample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    public static final String KEY_SCORE = "key_score";
    public static final String KEY_LIKES = "key_likes";
    private List<TeamModal> teamList;

    public TeamAdapter(List<TeamModal> teamList) {
        this.teamList = teamList;
    }

    public List<TeamModal> getTeamList() {
        return teamList;
    }

    @Override
    public TeamAdapter.TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TeamViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false));
    }

    @Override
    public void onBindViewHolder(TeamAdapter.TeamViewHolder holder, int position) {
        TeamModal modal = teamList.get(position);
        holder.id.setText("id" + modal.getId());
        holder.country.setText(modal.getCountry());
        holder.likes.setText(modal.getLikes());
        holder.score.setText(modal.getScore());

    }

    @Override
    public void onBindViewHolder(TeamAdapter.TeamViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals(KEY_SCORE)) {
                    //update score textView
                    holder.score.setText(o.getString(key));
                } else if (key.equals(KEY_LIKES)) {
                    //update likes textView
                    holder.likes.setText(o.getString(key));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return teamList != null ? teamList.size() : 0;
    }

    public void updateTeamList(View progressbar, List<TeamModal> newTeamList) {
//        new CalculateDiffResult(this, progressbar, newTeamList).execute(teamList, newTeamList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new TeamDiffUtilCallback(teamList,newTeamList));
        diffResult.dispatchUpdatesTo(this);
        teamList.clear();
        teamList.addAll(newTeamList);
    }

    public static final class CalculateDiffResult extends AsyncTask<List<TeamModal>, Void, DiffUtil.DiffResult> {

        private final List<TeamModal> newTeamList;
        WeakReference<TeamAdapter> adapterWeakReference;
        WeakReference<View> progress;

        public CalculateDiffResult(TeamAdapter adapterWeakReference, View progressbar, List<TeamModal> newTeamList) {
            this.adapterWeakReference = new WeakReference<>(adapterWeakReference);
            this.progress = new WeakReference<>(progressbar);
            this.newTeamList = newTeamList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (progress.get() != null) {
                progress.get().setVisibility(View.VISIBLE);
            }
        }

        @SafeVarargs
        @Override
        protected final DiffUtil.DiffResult doInBackground(List<TeamModal>... lists) {
            return DiffUtil.calculateDiff(new TeamDiffUtilCallback(lists[0], lists[1]));
        }

        @Override
        protected void onPostExecute(DiffUtil.DiffResult diffResult) {
            super.onPostExecute(diffResult);
            if (adapterWeakReference.get() != null) {
                diffResult.dispatchUpdatesTo(adapterWeakReference.get());
                adapterWeakReference.get().getTeamList().clear();
                adapterWeakReference.get().getTeamList().addAll(newTeamList);
            }
            if (progress.get() != null) {
                progress.get().setVisibility(View.GONE);
            }
        }
    }

    class TeamViewHolder extends RecyclerView.ViewHolder {

        TextView id, country, likes, score;
        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.teamid);
            country = itemView.findViewById(R.id.teamcountry);
            likes = itemView.findViewById(R.id.teamlikes);
            score = itemView.findViewById(R.id.teamscore);
        }
    }
}
