package com.github.brunomb.stackovergol.activity.matches;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.brunomb.stackovergol.R;
import com.github.brunomb.stackovergol.adapter.MatchesAdapter;
import com.github.brunomb.stackovergol.model.Match;
import com.github.brunomb.stackovergol.utils.MyLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {
//    private GridLayoutManager lLayout;
    private MatchesAdapter adapter;

    private TextView noMatches;
    private RecyclerView mRecyclerView;

    public MatchesFragment() {} // Required

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View mView = inflater.inflate(R.layout.fragment_matches, container, false);
        noMatches = (TextView) mView.findViewById(R.id.sog_matches_tv_empty);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.sog_matches_rv);

        updateAdapter(mView);

        return mView;
    }

    private void updateAdapter(View mView) {
        //TODO REFACTOR
        List<Match> matches = new ArrayList<>();
        String date1 = "07,01,17";
        String date2 = "14,01,17";
        String date3 = "21,01,17";

        DateFormat format = new SimpleDateFormat("dd,mm,yy");

        try {
            matches.add(new Match("Partida comum", format.parse(date1)));
            matches.add(new Match("Partida comum", format.parse(date2)));
            matches.add(new Match("Partida comum", format.parse(date3)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Match m : matches) {
            MyLog.i(m.getName() + ", " + m.getTimeStamp());
        }

        if (matches.isEmpty()) {
            noMatches.setVisibility(View.VISIBLE);
        } else {
            noMatches.setVisibility(View.GONE);
//            lLayout = new GridLayoutManager(mView.getContext(), 2);
            adapter = new MatchesAdapter(matches);
            LinearLayoutManager llm = new LinearLayoutManager(mView.getContext());
            mRecyclerView.setLayoutManager(llm);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
