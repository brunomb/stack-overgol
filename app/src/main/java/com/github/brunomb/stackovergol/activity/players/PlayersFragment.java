package com.github.brunomb.stackovergol.activity.players;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.activity.main.MainScreenActivity;
import com.github.brunomb.stackovergol.adapter.PlayersAdapter;
import com.github.brunomb.stackovergol.model.User;

import java.util.List;

/**
 * Created by brunomb on 3/29/2017
 */
public class PlayersFragment extends Fragment implements PlayersMVP.ViewOps {

    //    private GridLayoutManager lLayout;
    private PlayersAdapter adapter;
    private PlayersMVP.PresenterOps mPresenter;

    private TextView noPlayers;
    private RecyclerView mRecyclerView;

    public PlayersFragment() {} // Required

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter = new PlayersPresenter(this);
        mPresenter.setService(((MainScreenActivity) getActivity()).getService());
        mPresenter.getUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View mView = inflater.inflate(R.layout.fragment_players, container, false);
        noPlayers = (TextView) mView.findViewById(R.id.sog_players_tv_empty);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.sog_players_rv);
        return mView;
    }

    @Override
    public void showUsers(List<User> users) {
        if (users.isEmpty()) {
            noPlayers.setVisibility(View.VISIBLE);
        } else {
            noPlayers.setVisibility(View.GONE);
            adapter = new PlayersAdapter(users);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(llm);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
