package com.github.brunomb.stackovergol.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.model.Match;

import java.util.List;

/**
 * Created by brunomb on 3/16/2017
 */
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {

    private final List<Match> matchList;

    public MatchesAdapter(List<Match> myData) {
        this.matchList = myData;
    }

    @Override
    public MatchesAdapter.MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View matchView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_match, parent, false);
        return new MatchesViewHolder(matchView);
    }

    @Override
    public void onBindViewHolder(MatchesAdapter.MatchesViewHolder holder, int position) {
        holder.matchName.setText(matchList.get(position).getName());
        holder.matchDate.setText(matchList.get(position).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MatchesViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView matchName;
        TextView matchDate;

        MatchesViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            matchName = (TextView) itemView.findViewById(R.id.sog_card_match_tv_name);
            matchDate = (TextView) itemView.findViewById(R.id.sog_card_match_tv_date);
        }
    }
}
