package com.github.brunomb.stackovergol.activity.matches;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchesFragment extends Fragment {
//    private GridLayoutManager lLayout;
    private MatchesAdapter adapter;

    private TextView noMatches;
    private RecyclerView mRecyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MatchesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchesFragment newInstance(String param1, String param2) {
        MatchesFragment fragment = new MatchesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View mView = inflater.inflate(R.layout.fragment_matches, container, false);
        noMatches = (TextView) mView.findViewById(R.id.sog_matches_tv_empty);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.sog_matches_rv);

        updateAdapter(mView);

        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
