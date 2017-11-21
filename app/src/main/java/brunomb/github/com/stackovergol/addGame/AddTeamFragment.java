package brunomb.github.com.stackovergol.addGame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brunomb.github.com.stackovergol.R;

public class AddTeamFragment extends Fragment {

    public static AddTeamFragment newInstance() {
        return new AddTeamFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_team_frag, container, false);
    }
}
