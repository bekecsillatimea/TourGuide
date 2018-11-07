package com.example.beket.tourguide;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ToDoFragment extends Fragment {

    String name, rating, address, webAddress, description, imageURL;
    double latitude, longitude;
    ImageView rightButton, leftButton;

    public ToDoFragment() {
    }

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final String[][][] toDoArray = {
                {getResources().getStringArray(R.array.ToDoHikingBucegi),
                        getResources().getStringArray(R.array.ToDoHikingCiucas),
                        getResources().getStringArray(R.array.ToDoHikingFagaras),
                        getResources().getStringArray(R.array.ToDoHikingPiatraCraiului),
                        getResources().getStringArray(R.array.ToDoHikingPiatraMare),
                        getResources().getStringArray(R.array.ToDoHikingPostavaru)},

                {getResources().getStringArray(R.array.ToDoAquaticParadise),
                        getResources().getStringArray(R.array.ToDoEscapeRooms),
                        getResources().getStringArray(R.array.ToDoParcAventura)}};

        View rootView = inflater.inflate(R.layout.fragment_to_do, container, false);

        rightButton = rootView.findViewById(R.id.right_arrow_button);
        leftButton = rootView.findViewById(R.id.left_arrow_button);
        leftButton.setVisibility(View.INVISIBLE);

        ArrayList<Place> placesToDo = new ArrayList<>();
        ArrayList<Place> placesToDoHiking = new ArrayList<>();

        for (int i = 0; i < toDoArray.length; i++) {
            if (i == 0) {
                for (String[] placeToDoHiking : toDoArray[i]) {
                    for (int j = 0; j < placeToDoHiking.length; j++) {
                        getHikingInformation(placeToDoHiking, j);
                    }
                    placesToDoHiking.add(new Place(Place.HIKING_TYPE, name, webAddress, description, imageURL));
                }
            } else {
                for (String[] placeToDo : toDoArray[i]) {
                    for (int j = 0; j < placeToDo.length; j++) {
                        getToDoInformation(placeToDo, j);
                    }
                    placesToDo.add(new Place(Place.SLEEP_EAT_DO_TYPE, name, rating, address,
                            webAddress, description, latitude, longitude, imageURL));
                }
            }
        }

        PlaceAdapter hikingAdapter = new PlaceAdapter(getContext(), placesToDoHiking);
        PlaceAdapter toDoAdapter = new PlaceAdapter(getContext(), placesToDo);

        final RecyclerView hikingRecycleView = rootView.findViewById(R.id.horizontal_recycle_view);
        LinearLayoutManager hikingLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        hikingRecycleView.setLayoutManager(hikingLinearLayoutManager);
        snapHelper(hikingRecycleView);
        hikingRecycleView.setAdapter(hikingAdapter);

        RecyclerView toDoRecycleView = rootView.findViewById(R.id.recycle_view);
        LinearLayoutManager toDoLinearLayoutManager = new LinearLayoutManager(getContext());
        toDoRecycleView.setLayoutManager(toDoLinearLayoutManager);
        toDoRecycleView.setAdapter(toDoAdapter);

        return rootView;
    }

    private void snapHelper(RecyclerView recyclerView){
        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));

                if(targetPosition == firstItem){
                    leftButton.setVisibility(View.INVISIBLE);
                } else if (targetPosition == lastItem){
                    rightButton.setVisibility(View.INVISIBLE);
                } else {
                    leftButton.setVisibility(View.VISIBLE);
                    rightButton.setVisibility(View.VISIBLE);
                }
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private void getToDoInformation(String[] informationArray, int position) {

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

    private void getHikingInformation(String[] informationArray, int position){
        switch (position) {
            case 0:
                name = informationArray[position];
                break;
            case 1:
                webAddress = informationArray[position];
                break;
            case 2:
                description = informationArray[position];
                break;
            case 3:
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
