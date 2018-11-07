package com.example.beket.tourguide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter {

    private ArrayList<Place> places;
    private Context mContext;
    private final SparseBooleanArray mCollapsedStatus;

    PlaceAdapter(Context context, ArrayList<Place> places) {
        this.places = places;
        this.mContext = context;
        mCollapsedStatus = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View itemView;
        switch (viewType) {
            case 0:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new Holder(itemView);
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hiking_list_item, parent, false);
                return new HikingHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Place currentPlace = places.get(position);
        String description = currentPlace.getPlaceDescription();

        switch (currentPlace.getType()) {
            case Place.SLEEP_EAT_DO_TYPE:
                ((Holder) holder).name.setText(currentPlace.getPlaceName());
                ((Holder) holder).rating.setText(currentPlace.getPlaceRating());
                if (!currentPlace.getPlaceRating().contains("No")) {
                    ((Holder) holder).ratingBar.setRating(Float.parseFloat(currentPlace.getPlaceRating()));
                } else {
                    ((Holder) holder).ratingBar.setVisibility(View.GONE);
                }
                ((Holder) holder).address.setText(currentPlace.getPlaceAddress());
                ((Holder) holder).expandableTextView.setText(description, mCollapsedStatus, position);
                Glide.with(mContext)
                        .load(currentPlace.getPlaceImageURL())
                        .into(((Holder) holder).placeImage);
                break;
            case Place.VISIT_TYPE:
                ((Holder) holder).name.setText(currentPlace.getPlaceName());
                ((Holder) holder).rating.setText(currentPlace.getPlaceRating());
                if (!currentPlace.getPlaceRating().contains("No")) {
                    ((Holder) holder).ratingBar.setRating(Float.parseFloat(currentPlace.getPlaceRating()));
                } else {
                    ((Holder) holder).ratingBar.setVisibility(View.GONE);
                }
                ((Holder) holder).address.setText(currentPlace.getPlaceAddress());
                ((Holder) holder).expandableTextView.setText(description, mCollapsedStatus, position);
                Glide.with(mContext)
                        .load(currentPlace.getPlaceImageURL())
                        .into(((Holder) holder).placeImage);
                ((Holder) holder).webIcon.setVisibility(View.GONE);
                ((Holder) holder).webAddress.setVisibility(View.GONE);
                break;
            case Place.HIKING_TYPE:
                ((HikingHolder) holder).name.setText(currentPlace.getPlaceName());
                ((HikingHolder) holder).expandableTextView.setText(description, mCollapsedStatus, position);
                Glide.with(mContext)
                        .load(currentPlace.getPlaceImageURL())
                        .into(((HikingHolder) holder).placeImage);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (places.get(position).getType()) {
            case 0:
                return Place.SLEEP_EAT_DO_TYPE;
            case 1:
                return Place.SLEEP_EAT_DO_TYPE;
            case 2:
                return Place.HIKING_TYPE;
            default:
                return 0;
        }
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, rating, address, webAddress;
        ExpandableTextView expandableTextView;
        ImageView webIcon, placeImage;
        RatingBar ratingBar;

        private Holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text_view);
            rating = itemView.findViewById(R.id.rating_text_view);
            address = itemView.findViewById(R.id.address_text_view);
            webAddress = itemView.findViewById(R.id.web_text_view);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            webIcon = itemView.findViewById(R.id.web_icon);
            placeImage = itemView.findViewById(R.id.place_image_view);
            expandableTextView = itemView.findViewById(R.id.description_expandable_text_view);

            webAddress.setOnClickListener(this);
            address.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.web_text_view:
                    Intent webPageIntent = new Intent(Intent.ACTION_VIEW);
                    String webAddress = places.get(getAdapterPosition()).getPlaceWebAddress();
                    webPageIntent.setData(Uri.parse(webAddress));
                    if (webPageIntent.resolveActivity(mContext.getPackageManager()) != null) {
                        mContext.startActivity(webPageIntent);
                    }
                    break;
                case R.id.address_text_view:
                    double latitude = places.get(getAdapterPosition()).getPlaceLatitude();
                    double longitude = places.get(getAdapterPosition()).getPlaceLongitude();
                    String placeName = places.get(getAdapterPosition()).getPlaceName();
                    String uriString = "geo:0,0?q=" + latitude + "," + longitude + "(" + placeName + ")";
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
                    if (mapIntent.resolveActivity(mContext.getPackageManager()) != null) {
                        mContext.startActivity(mapIntent);
                    }
            }

        }
    }

    class HikingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, webAddress;
        ImageView placeImage;
        ExpandableTextView expandableTextView;

        private HikingHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.hiking_name_text_view);
            webAddress = itemView.findViewById(R.id.hiking_web_text_view);
            placeImage = itemView.findViewById(R.id.hiking_place_image_view);
            expandableTextView = itemView.findViewById(R.id.hiking_description_expandable_text_view);

            webAddress.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent webPageIntent = new Intent(Intent.ACTION_VIEW);
            String webAddress = places.get(getAdapterPosition()).getPlaceWebAddress();
            webPageIntent.setData(Uri.parse(webAddress));
            if (webPageIntent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(webPageIntent);
            }

        }

    }
}

