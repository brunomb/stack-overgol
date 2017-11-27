package brunomb.github.com.stackovergol.addGame;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.data.model.Game;
import brunomb.github.com.stackovergol.data.model.GameType;
import brunomb.github.com.stackovergol.data.model.Team;

public class AddGameFragment extends Fragment {

    private ArrayList<Team> mTeams;
    private AddGameViewModel viewModel;
    private Button dateButton;
    private EditText nameEditText;
    private Spinner durationSpinner;
    private Spinner typeSpinner;
    private DatePickerDialog gameDatePicker;

    public static AddGameFragment newInstance() {
        return new AddGameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(AddGameViewModel.class);

        final Observer<Game> gameObserver = game -> {
            if (game != null) {
                setupName(game.getName());
                setupDatePicker(game.getDate());
                setupTypeSpinner(game.getType());
                setupDurationSpinner(game.getDuration());
            }
        };

        final Observer<ArrayList<Team>> teamsObserver = teams -> {
            mTeams = teams;
        };

        viewModel.getGame().observe(this, gameObserver);
        viewModel.getTeams().observe(this, teamsObserver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_game_frag, container, false);
        nameEditText = root.findViewById(R.id.et_add_game_name);
        dateButton = root.findViewById(R.id.bt_add_game_date);
        typeSpinner = root.findViewById(R.id.sp_add_game_type);
        durationSpinner = root.findViewById(R.id.sp_add_game_duration);
        return root;
    }

    private void setupName(String name) {
        if (nameEditText != null) {
            nameEditText.setText(name);
        }
    }

    public void setupDatePicker(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int tempDate = cal.get(Calendar.DATE);
        SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH);
        dateButton.setText(format.format(date.getTime()));

        gameDatePicker = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            cal.set(i, i1, i2);
            // TODO UPDATE HERE
//            gameDate = cal.getTime();
            SimpleDateFormat format1 = new SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH);
            dateButton.setText(format1.format(cal.getTime()));
        }, year, month, tempDate);

        dateButton.setOnClickListener(view -> gameDatePicker.show());
    }

    private void setupTypeSpinner(GameType type) {
        List<String> list = new ArrayList<>();
        list.add("Championship");
        list.add("Elimination");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapter);
        switch (type) {
            case CHAMPIONSHIP:
                typeSpinner.setSelection(0);
                break;
            case ELIMINATION:
                typeSpinner.setSelection(1);
                break;
        }

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setupDurationSpinner(int duration) {
        List<String> list = new ArrayList<>();
        list.add("7 min");
        list.add("8 min");
        list.add("9 min");
        list.add("10 min");
        list.add("11 min");
        list.add("12 min");
        list.add("13 min");
        list.add("14 min");
        list.add("15 min");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(dataAdapter);
        durationSpinner.setSelection(duration - 7);

        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}
