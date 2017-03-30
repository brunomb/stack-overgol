package com.github.brunomb.stackovergol.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.model.User;

import java.util.List;

/**
 * Created by brunomb on 3/29/2017
 */

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {

    private final List<User> playerList;

    public PlayersAdapter(List<User> myData) {
        this.playerList = myData;
    }

    @Override
    public PlayersAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View matchView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_player, parent, false);
        return new PlayerViewHolder(matchView);
    }

    @Override
    public void onBindViewHolder(PlayersAdapter.PlayerViewHolder holder, int position) {
        String name = playerList.get(position).getFirstName() + " " + playerList.get(position).getLastName();
        holder.userName.setText(name);
        holder.userID.setText(playerList.get(position).getId().toString());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView userName;
        TextView userID;

        PlayerViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            userName = (TextView) itemView.findViewById(R.id.sog_card_player_tv_name);
            userID = (TextView) itemView.findViewById(R.id.sog_card_player_tv_id);
        }
    }
}
