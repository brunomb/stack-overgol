package brunomb.github.com.stackovergol.addGame;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import brunomb.github.com.stackovergol.R;
import brunomb.github.com.stackovergol.data.model.GameType;

public class AddGameFragment extends Fragment {

    private Button dateButton;
    private static AddGameContract.Presenter addGamePresenter;
    private Button saveButton;
    private EditText nameEditText;
    private Spinner durationSpinner;
    private Spinner typeSpinner;
    private DatePickerDialog gameDatePicker;
    private Date gameDate;
    private GameType gameType;
    private Integer duration;

    public static AddGameFragment newInstance(AddGamerPresenter presenter) {
        addGamePresenter = presenter;
        return new AddGameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.add_game_frag, container, false);
        nameEditText = root.findViewById(R.id.et_add_game_name);
        nameEditText.setText("Stack OverGol");
        dateButton = root.findViewById(R.id.bt_add_game_date);
        typeSpinner = root.findViewById(R.id.sp_add_game_type);
        durationSpinner = root.findViewById(R.id.sp_add_game_duration);
        saveButton = root.findViewById(R.id.bt_add_game_save);
        setupDatePicker();
        setupTypeSpinner();
        setupDurationSpinner();

        addGamePresenter.showPlayers();
        return root;
    }

//    private void setupSaveButton() {
//        final String name = nameEditText.getText().toString();
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPresenter.saveGame(name, gameDate, duration, gameType);
//            }
//        });
//    }

    public void setupDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        gameDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH);
        dateButton.setText(format.format(calendar.getTime()));

        gameDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar cal = Calendar.getInstance();
                cal.set(i, i1, i2);
                gameDate = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH);
                dateButton.setText(format.format(gameDate.getTime()));
            }
        }, year, month, date);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDatePicker.show();
            }
        });
    }

    private void setupTypeSpinner() {
        List<String> list = new ArrayList<>();
        list.add("Championship");
        list.add("Elimination");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapter);
        typeSpinner.setSelection(0);
        gameType = GameType.CHAMPIONSHIP;

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        gameType = GameType.CHAMPIONSHIP;
                        break;
                    case 1:
                        gameType = GameType.ELIMINATION;
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

    private void setupDurationSpinner() {
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
        durationSpinner.setSelection(1);
        duration = 8;

        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                duration = i + 7;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}
