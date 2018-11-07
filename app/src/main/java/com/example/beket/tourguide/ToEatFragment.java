package com.example.beket.tourguide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ToEatFragment extends Fragment {

    String name, rating, address, webAddress, description, imageURL;
    double latitude, longitude;

    public ToEatFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final String[][] toEatArray = {
                getResources().getStringArray(R.array.ToEatGott),
                getResources().getStringArray(R.array.ToEatLaCeaun),
                getResources().getStringArray(R.array.ToEatOldJackBurgerHouse),
                getResources().getStringArray(R.array.ToEatPilvax),
                getResources().getStringArray(R.array.ToEatRawdia),
                getResources().getStringArray(R.array.ToEatSergiana),
                getResources().getStringArray(R.array.ToEatSimone),
                getResources().getStringArray(R.array.ToEatTerroirsBoutiqueDuVin),
                getResources().getStringArray(R.array.ToEatTheHockeyPub)};

        View rootView = inflater.inflate(R.layout.fragment_sleep_eat_visit, container, false);

        ArrayList<Place> placesToEat = new ArrayList<>();

        for (String[] placeToEatArray : toEatArray) {
            for (int j = 0; j < toEatArray.length; j++) {
                getToEatInformation(placeToEatArray, j);

            }
            placesToEat.add(new Place(Place.SLEEP_EAT_DO_TYPE, name, rating, address, webAddress,
                    description, latitude, longitude, imageURL));
        }

        PlaceAdapter placeAdapter = new PlaceAdapter(getContext(), placesToEat);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(placeAdapter);

        return rootView;
    }

    private void getToEatInformation(String[] informationArray, int position) {
        switch (position) {
            case 0:
                name = informationArray[position];
                break;
            case 1:
                rating = informationArray[position];
                break;
            case 2:
                address = informationArray[position];
                break;
            case 3:
                webAddress = informationArray[position];
                break;
            case 4:
                description = informationArray[position];
                break;
            case 5:
                String latAndLong = informationArray[position];
                getLatAndLong(latAndLong);
                break;
            case 6:
                imageURL = informationArray[position];
                break;
        }
    }

    private void getLatAndLong(String latAndLong) {
        String[] latAndLongArray = latAndLong.split(",");
        latitude = Double.parseDouble(latAndLongArray[0]);
        longitude = Double.parseDouble(latAndLongArray[1]);
    }
}
