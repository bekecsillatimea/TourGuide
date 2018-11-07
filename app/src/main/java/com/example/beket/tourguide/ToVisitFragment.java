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

public class ToVisitFragment extends Fragment {

    String name, rating, address, description, imageURL;
    double latitude, longitude;

    public ToVisitFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final String[][] toVisitArray = {
                getResources().getStringArray(R.array.ToVisitBlackAndWhiteTowers),
                getResources().getStringArray(R.array.ToVisitBlackChurch),
                getResources().getStringArray(R.array.ToVisitBranCastle),
                getResources().getStringArray(R.array.ToVisitCatherineGate),
                getResources().getStringArray(R.array.ToVisitFirstRomanianSchool),
                getResources().getStringArray(R.array.ToVisitNarrowStreet),
                getResources().getStringArray(R.array.ToVisitParcCentral),
                getResources().getStringArray(R.array.ToVisitPelesCastle)};

        View rootView = inflater.inflate(R.layout.fragment_sleep_eat_visit, container, false);

        ArrayList<Place> placesToVisit = new ArrayList<>();

        for (String[] placeToVisitArray : toVisitArray) {
            for (int j = 0; j < placeToVisitArray.length; j++) {
                getToVisitInformation(placeToVisitArray, j);

            }
            placesToVisit.add(new Place(Place.VISIT_TYPE, name, rating, address, description,
                    latitude, longitude, imageURL));
        }

        PlaceAdapter placeAdapter = new PlaceAdapter(getContext(), placesToVisit);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(placeAdapter);

        return rootView;
    }

    private void getToVisitInformation(String[] informationArray, int position) {
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
                description = informationArray[position];
                break;
            case 4:
                String latAndLong = informationArray[position];
                getLatAndLong(latAndLong);
                break;
            case 5:
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
