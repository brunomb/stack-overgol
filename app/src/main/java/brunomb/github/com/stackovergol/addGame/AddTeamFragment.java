package brunomb.github.com.stackovergol.addGame;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.data.model.Team;
import brunomb.github.com.stackovergol.data.model.TeamColor;

public class AddTeamFragment extends Fragment {

    private static final String TEAM_NAME = "team_name";
    private static final String TEAM_COLOR = "team_color";
    private TeamColor teamColor;
    private AddGameViewModel viewModel;
    private Team team;
    private TextView title;
    private ImageView color;
    private LinearLayout nameBg;
    private ListView playersListView;

    public AddTeamFragment() {}

    public static AddTeamFragment newInstance(String name, String color) {
        AddTeamFragment fragment = new AddTeamFragment();
        Bundle args = new Bundle();
        args.putString(TEAM_NAME, name);
        args.putString(TEAM_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamColor = TeamColor.getColor(getArguments().getString(TEAM_COLOR));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_team_frag, container, false);
        title = root.findViewById(R.id.tv_add_team_name);
//        color = root.findViewById(R.id.iv_add_team_color);
        nameBg = root.findViewById(R.id.ll_add_team_name_bg);
        playersListView = root.findViewById(R.id.lv_add_team_players);
        viewModel = ViewModelProviders.of(getActivity()).get(AddGameViewModel.class);

        viewModel.getTeams().observe(this, teams -> {
            switch (teamColor) {
                case RED:
                    team = teams.get(2);
                    break;
                case BLUE:
                    team = teams.get(1);
                    break;
                case GREEN:
                    team = teams.get(0);
                    break;
                case WHITE:
                    team = teams.get(3);
                    break;
            }
            prepareView();
        });
        return root;
    }

    public void prepareView() {
        AddGamePlayerAdapter adapter = new AddGamePlayerAdapter(team.getPlayers());
        playersListView.setAdapter(adapter);

        title.setText(team.getName());
        switch (team.getColor()) {
            case RED:
                nameBg.setBackgroundColor(getActivity().getResources().getColor(R.color.team_red));
//                color.setImageResource(R.mipmap.team_red);
                break;
            case BLUE:
                nameBg.setBackgroundColor(getActivity().getResources().getColor(R.color.team_blue));
//                color.setImageResource(R.mipmap.team_blue);
                break;
            case GREEN:
                nameBg.setBackgroundColor(getActivity().getResources().getColor(R.color.team_green));
//                color.setImageResource(R.mipmap.team_green);
                break;
            case WHITE:
                nameBg.setBackgroundColor(getActivity().getResources().getColor(R.color.team_white));
//                color.setImageResource(R.mipmap.team_white);
                break;
        }
    }
}
