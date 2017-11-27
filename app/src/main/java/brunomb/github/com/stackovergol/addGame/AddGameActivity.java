package brunomb.github.com.stackovergol.addGame;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.data.model.Team;
import brunomb.github.com.stackovergol.util.ActivityUtils;

public class AddGameActivity extends AppCompatActivity {

    private ArrayList<Team> mTeams;
    private Button nextButton;
    private Button previousButton;
    private AddGameFragment addGameFragment;

    private AddGameViewModel viewModel;

    private static final int ADD_GAME = 0;
    private static final int RED_TEAM = 1;
    private static final int BLUE_TEAM = 2;
    private static final int WHITE_TEAM = 3;
    private static final int GREEN_TEAM = 4;
    private static int actualState = ADD_GAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game_act);

        nextButton = findViewById(R.id.bt_add_game_next);
        previousButton = findViewById(R.id.bt_add_game_cancel);

        previousButton.setText(R.string.add_game_bt_cancel);
        nextButton.setText(R.string.add_game_bt_next);

        if (addGameFragment == null) {
            addGameFragment = AddGameFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addGameFragment,
                    R.id.add_game_frag);
        }

        viewModel = ViewModelProviders.of(this).get(AddGameViewModel.class);

        final Observer<ArrayList<Team>> teamsObserver = teams -> {
            mTeams = teams;
        };

        viewModel.getTeams().observe(this, teamsObserver);

        nextButton.setOnClickListener(view -> nextFragment());

        previousButton.setOnClickListener(view -> previousFragment());
    }

    private void teamRedState() {
        actualState = RED_TEAM;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddTeamFragment teamRedFragment = AddTeamFragment.newInstance(mTeams.get(2).getName(),
                mTeams.get(2).getColor().getValue());
        // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.add_game_frag, teamRedFragment, "fragment");
        // Start the animated transition.
        ft.commit();
        previousButton.setText(R.string.add_game_bt_previous);
        nextButton.setText(R.string.add_game_bt_next);
    }

    private void teamGreenState() {
        actualState = GREEN_TEAM;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddTeamFragment teamGreenFragment = AddTeamFragment.newInstance(mTeams.get(0).getName(),
                mTeams.get(0).getColor().getValue());
        // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.add_game_frag, teamGreenFragment, "fragment");
        // Start the animated transition.
        ft.commit();
        previousButton.setText(R.string.add_game_bt_previous);
        nextButton.setText(R.string.add_game_bt_finish);
    }

    private void teamBlueState() {
        actualState = BLUE_TEAM;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddTeamFragment teamBlueFragment = AddTeamFragment.newInstance(mTeams.get(1).getName(),
                mTeams.get(1).getColor().getValue());
        // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.add_game_frag, teamBlueFragment, "fragment");
        // Start the animated transition.
        ft.commit();
        previousButton.setText(R.string.add_game_bt_previous);
        nextButton.setText(R.string.add_game_bt_next);
    }

    private void teamWhiteState() {
        actualState = WHITE_TEAM;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddTeamFragment teamWhiteFragment = AddTeamFragment.newInstance(mTeams.get(3).getName(),
                mTeams.get(3).getColor().getValue());
        // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.add_game_frag, teamWhiteFragment, "fragment");
        // Start the animated transition.
        ft.commit();
        previousButton.setText(R.string.add_game_bt_previous);
        nextButton.setText(R.string.add_game_bt_next);
    }

    private void addGameState() {
        actualState = ADD_GAME;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        addGameFragment = AddGameFragment.newInstance();
        // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.add_game_frag, addGameFragment, "fragment");
        // Start the animated transition.
        ft.commit();
        previousButton.setText(R.string.add_game_bt_cancel);
        previousButton.setText(R.string.add_game_bt_next);
    }

    public void nextFragment() {
        switch (actualState) {
            case ADD_GAME:
                teamRedState();
                break;
            case RED_TEAM:
                teamBlueState();
                break;
            case BLUE_TEAM:
                teamWhiteState();
                break;
            case WHITE_TEAM:
                teamGreenState();
                break;
            case GREEN_TEAM:
                break;
            default:
                break;
        }
    }

    public void previousFragment() {
        switch (actualState) {
            case ADD_GAME:
                super.onBackPressed();
            case RED_TEAM:
                addGameState();
                break;
            case BLUE_TEAM:
                teamRedState();
                break;
            case WHITE_TEAM:
                teamBlueState();
                break;
            case GREEN_TEAM:
                teamWhiteState();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}