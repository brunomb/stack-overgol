package brunomb.github.com.stackovergol.games;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.data.model.Game;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private Game[] mGameDataset;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mGameNameTextView;
        TextView mGameDateTextView;
        TextView mGameTypeTextView;
        ImageView mGameTypeImageVIew;

        ViewHolder(LinearLayout viewHolder) {
            super(viewHolder);
            mGameNameTextView = viewHolder.findViewById(R.id.game_card_tv_name);
            mGameDateTextView = viewHolder.findViewById(R.id.game_card_tv_date);
            mGameTypeImageVIew = viewHolder.findViewById(R.id.game_card_iv_type);
            mGameTypeTextView = viewHolder.findViewById(R.id.game_card_tv_type);
        }
    }

    GameAdapter(Game[] gameDataset) {
        mGameDataset = gameDataset;
    }

    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout cardView = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.games_card, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mGameNameTextView.setText(mGameDataset[position].getName());
        holder.mGameDateTextView.setText("10/11/2017");
        holder.mGameTypeTextView.setText(mGameDataset[position].getType().getValue());
        switch (mGameDataset[position].getType()) {
            case ELIMINATION:
                holder.mGameTypeImageVIew.setImageResource(R.mipmap.elimination_icon);
                break;
            case CHAMPIONSHIP:
                holder.mGameTypeImageVIew.setImageResource(R.mipmap.championship_icon);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mGameDataset.length;
    }
}
