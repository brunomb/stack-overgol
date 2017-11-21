package brunomb.github.com.stackovergol.addGame;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.util.ActivityUtils;

public class AddGameActivity extends AppCompatActivity {

    private Button nextButton;
    private Button previousButton;
    private AddGameFragment addGameFragment;
    private AddTeamFragment teamRedFragment;
    private AddTeamFragment teamBlueFragment;
    private AddTeamFragment teamWhiteFragment;
    private AddTeamFragment teamGreenFragment;
    private static int actualState;
    private static final int ADD_GAME = 0;
    private static final int RED_TEAM = 1;
    private static final int BLUE_TEAM = 2;
    private static final int WHITE_TEAM = 3;
    private static final int GREEN_TEAM = 4;

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

        actualState = ADD_GAME;

        nextButton.setOnClickListener(view -> nextFragment());

        previousButton.setOnClickListener(view -> previousFragment());
    }

    public void nextFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (actualState) {
            case ADD_GAME:
                actualState = RED_TEAM;
                teamRedFragment = AddTeamFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, teamRedFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                previousButton.setText(R.string.add_game_bt_previous);
                break;
            case RED_TEAM:
                actualState = BLUE_TEAM;
                teamBlueFragment = AddTeamFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, teamBlueFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                break;
            case BLUE_TEAM:
                actualState = WHITE_TEAM;
                teamWhiteFragment = AddTeamFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, teamWhiteFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                break;
            case WHITE_TEAM:
                actualState = GREEN_TEAM;
                teamGreenFragment = AddTeamFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, teamGreenFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                nextButton.setText(R.string.add_game_bt_finish);
                break;
            case GREEN_TEAM:
                break;
            default:
                break;
        }
    }

    public void previousFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (actualState) {
            case ADD_GAME:
                super.onBackPressed();
            case RED_TEAM:
                actualState = ADD_GAME;
                addGameFragment = AddGameFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, addGameFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                previousButton.setText(R.string.add_game_bt_cancel);
                break;
            case BLUE_TEAM:
                actualState = RED_TEAM;
                teamRedFragment = AddTeamFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, teamRedFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                break;
            case WHITE_TEAM:
                actualState = BLUE_TEAM;
                teamBlueFragment = AddTeamFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, teamBlueFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                break;
            case GREEN_TEAM:
                actualState = WHITE_TEAM;
                teamWhiteFragment = AddTeamFragment.newInstance();
                // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.add_game_frag, teamWhiteFragment, "fragment");
                // Start the animated transition.
                ft.commit();
                nextButton.setText(R.string.add_game_bt_next);
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