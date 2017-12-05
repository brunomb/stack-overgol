package brunomb.github.com.stackovergol.games;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.addGame.AddGameActivity;
import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;

public class GamesFragment extends Fragment {

    private RecyclerView gamesRecyclerView;
    private GameAdapter gamesAdapter;
    private LinearLayout emptyGamesMessage;

    public static GamesFragment newInstance() {
        return new GamesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.games_frag, container, false);

        GamesViewModel viewModel = ViewModelProviders.of(this).get(GamesViewModel.class);

        gamesRecyclerView = root.findViewById(R.id.games_rv);
        emptyGamesMessage = root.findViewById(R.id.ll_games_empty_games);
        gamesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        gamesRecyclerView.setLayoutManager(mLayoutManager);

        final Observer<Game[]> gamesObserver = games -> {
            if (games != null && games.length > 0) {
                gamesRecyclerView.setVisibility(View.VISIBLE);
                emptyGamesMessage.setVisibility(View.INVISIBLE);
                gamesAdapter = new GameAdapter(games);
                gamesRecyclerView.setAdapter(gamesAdapter);
            } else {
                gamesRecyclerView.setVisibility(View.INVISIBLE);
                emptyGamesMessage.setVisibility(View.VISIBLE);
            }
        };

        viewModel.getGames().observe(this, gamesObserver);

        FloatingActionButton addGameFloatActionButton = root.findViewById(R.id.games_fab_add);
        addGameFloatActionButton.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            cal.set(2017, 11, 6);
            Date dateA = cal.getTime();
            Game gameChampionShip = new Game("Game A", dateA, GameType.CHAMPIONSHIP, 8);
            viewModel.addGame(gameChampionShip);
            Intent intent = new Intent(getContext(), AddGameActivity.class);
            startActivity(intent);
        });
        return root;
    }
}
