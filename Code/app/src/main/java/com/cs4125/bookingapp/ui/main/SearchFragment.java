package com.cs4125.bookingapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cs4125.bookingapp.R;
import com.cs4125.bookingapp.entities.Route;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.sql.Timestamp;
import java.util.Calendar;

public class SearchFragment extends Fragment
{
    private SearchViewModel searchViewModel;
    private EditText location;
    private EditText destination;
    private Button time;
    private Button date;
    private Button searchBtn;
    private NavController navController;
    private TextView dateText;
    private TextView timeText;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int userId;
    private ChipGroup filterGroup;

    public static SearchFragment newInstance()
    {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        configureUiItems(view);
        //searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.init();
        userId = SearchFragmentArgs.fromBundle(getArguments()).getUserId();

        return view;
    }

    private void configureUiItems(View view) {
        bindUiItems(view);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                timeText.setText(String.format("%02d:%02d:00", hourOfDay, minute));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        date.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dateText.setText(String.format("%04d-%02d-%02d", year, (monthOfYear + 1), dayOfMonth));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        }));
        searchBtn.setOnClickListener(view1 -> search());
    }

    private void bindUiItems(View view){
        dateText = view.findViewById(R.id.dateText);
        timeText = view.findViewById(R.id.timeText);

        location = view.findViewById(R.id.locIn);
        destination = view.findViewById(R.id.desIn);
        time = view.findViewById(R.id.timePicker);
        date = view.findViewById(R.id.datePicker);
        searchBtn = view.findViewById(R.id.searchBtn);

        filterGroup = view.findViewById(R.id.filterChipGroup);
    }

    private void search() {

        String flocation = "";
        String fdestination = "";
        String fdate = "";
        if (location.getText() != null && location.getText().length() != 0)
            flocation = location.getText().toString();
        if (destination.getText() != null && destination.getText().length() != 0)
            fdestination = destination.getText().toString();
//        if (mYear + mMonth + mDay != 0 && mMinute + mHour != 0)
//            fdate = String.format("%04d-%02d-%02d %02d:%02d:00", mYear, mMonth, mDay, mHour, mMinute);
//        Timestamp stamp = null;
//        try
//        {
//            stamp = Timestamp.valueOf(fdate);
//        }
//        catch (IllegalArgumentException e)
//        {
//            System.out.println(e.toString());
//        }
        Route routeToSearch = new Route.RouteBuilder()
                .setStartStation(flocation)
                .setEndStation(fdestination)
                .build();

//        LiveData<String> response = searchViewModel.searchAll(routeToSearch);
        LiveData<String> response;
        if(filterGroup.getCheckedChipId() == R.id.defaultChip)
        {
            Utilities.showToast(this.getContext(), "Searching!");
            response = searchViewModel.searchAll(routeToSearch.getStartStation(), routeToSearch.getEndStation());
        }
        else
        {
            Chip c = getView().findViewById(filterGroup.getCheckedChipId());
            Utilities.showToast(this.getContext(), "Searching!");
            response = searchViewModel.searchAllFiltered(routeToSearch.getStartStation(), routeToSearch.getEndStation(), c.getText().toString().toUpperCase());
        }
//        if(filterGroup.getCheckedChipId() == View.R.)
//            response = searchViewModel.searchAll(routeToSearch.getStartStation(), routeToSearch.getEndStation());
//        else
//            response = searchViewModel.searchAllFiltered(routeToSearch.getStartStation(), routeToSearch.getEndStation(), filterGroup.);

        response.observe(getViewLifecycleOwner(), this::observeResponse);
    }

    private void observeResponse(String s)
    {
        String[] temp = s.split(": ");
        // TODO: remove after implementing strategy pattern
        if(true)
        {
            Route route = new Route.RouteBuilder()
                    .setRouteID(1)
                    .setStartStation("N1")
                    .setEndStation("N2")
                    .setConnectionPath("C1")
                    .build();

            Route route2 = new Route.RouteBuilder()
                    .setRouteID(2)
                    .setStartStation("N2")
                    .setEndStation("N3")
                    .setConnectionPath("C1&&C2")
                    .build();
            String routes = route.toString() + route2.toString();
            SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment action = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(userId, routes);
            navController.navigate(action);
        }
        else
        {
            if (temp[0].equals("SUCCESS"))
            {
                String routes = temp[1];
                SearchFragmentDirections.ActionSearchFragmentToSearchResultFragment action = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(userId, routes);
                navController.navigate(action);
            }
            else
            {
                Utilities.showToast(this.getContext(), "Search Failed");
            }
        }
    }

}