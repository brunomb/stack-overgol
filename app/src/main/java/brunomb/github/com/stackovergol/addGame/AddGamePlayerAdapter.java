package brunomb.github.com.stackovergol.addGame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.data.model.Player;

public class AddGamePlayerAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView name;
        TextView score;

        public ViewHolder(View view) {
            name = view.findViewById(R.id.tv_add_game_player_name);
            score = view.findViewById(R.id.tv_add_game_player_score);
        }
    }

    private final ArrayList<Player> players;

    public AddGamePlayerAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return this.players.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View playerView;
        ViewHolder viewHolder;

        if (view == null) {
            playerView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_game_player, viewGroup, false);
            viewHolder = new ViewHolder(playerView);
            playerView.setTag(viewHolder);
        } else {
            playerView = view;
            viewHolder = (ViewHolder) playerView.getTag();
        }

        Player player = (Player) getItem(position);

        viewHolder.name.setText(player.getName());
        viewHolder.score.setText(player.getRating().toString());
        return playerView;
    }
}
