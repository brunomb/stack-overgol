package brunomb.github.com.stackovergol.games;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.addGame.AddGameActivity;
import brunomb.github.com.stackovergol.data.model.Game;

public class GamesFragment extends Fragment implements GamesContract.View {

    private RecyclerView gamesRecyclerView;
    private GameAdapter gamesAdapter;
    private GamesContract.Presenter mPresenter;

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

        gamesRecyclerView = root.findViewById(R.id.games_rv);

        gamesRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        gamesRecyclerView.setLayoutManager(mLayoutManager);

        FloatingActionButton addGameFloatActionButton = root.findViewById(R.id.games_fab_add);
        addGameFloatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddGameActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void setPresenter(GamesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void listGames(ArrayList<Game> games) {
        gamesAdapter = new GameAdapter(games);
        gamesRecyclerView.setAdapter(gamesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter == null) {
            mPresenter = new GamesPresenter(this);
        } else {
            mPresenter.start();
        }
    }
}
