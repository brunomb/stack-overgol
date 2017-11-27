package brunomb.github.com.stackovergol.addGame;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import brunomb.github.com.stackovergol.data.model.Player;

public class AddGamePlayerAdapter extends BaseAdapter {

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
