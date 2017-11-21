package brunomb.github.com.stackovergol.games;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.addGame.AddGameActivity;
import brunomb.github.com.stackovergol.data.model.Game;

public class GamesFragment extends Fragment {

    private GamesViewModel viewModel;
    private RecyclerView gamesRecyclerView;
    private GameAdapter gamesAdapter;
//    private GamesContract.Presenter mPresenter;

    public static GamesFragment newInstance() {
        return new GamesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.games_frag, container, false);

        viewModel = ViewModelProviders.of(this).get(GamesViewModel.class);

        gamesRecyclerView = root.findViewById(R.id.games_rv);
        gamesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        gamesRecyclerView.setLayoutManager(mLayoutManager);

        final Observer<ArrayList<Game>> gamesObserver = games -> {
            if (games != null && !games.isEmpty()) {
                Log.i("brunomb", "UPDATED!");
                gamesAdapter = new GameAdapter(games);
                gamesRecyclerView.setAdapter(gamesAdapter);
            }
        };

        viewModel.getGames().observe(this, gamesObserver);

        FloatingActionButton addGameFloatActionButton = root.findViewById(R.id.games_fab_add);
        addGameFloatActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AddGameActivity.class);
            startActivity(intent);
        });
        return root;
    }
}
