package com.example.beket.tourguide;

class Place {

    private String mPlaceName;
    private String mPlaceAddress;
    private String mPlaceWebAddress;
    private String mPlaceDescription;
    private String mPlaceRating;
    private String mPlaceImageURL;
    private double mPlaceLatitude;
    private double mPlaceLongitude;
    private int mType;

    static final int SLEEP_EAT_DO_TYPE = 0;
    static final int VISIT_TYPE = 1;
    static final int HIKING_TYPE = 2;


    Place(int type, String placeName, String placeRating, String placeAddress, String placeWebAddress,
          String placeDescription, double placeLatitude, double placeLongitude, String placeImageURL){
        mType = type;
        mPlaceName = placeName;
        mPlaceAddress = placeAddress;
        mPlaceWebAddress = placeWebAddress;
        mPlaceDescription = placeDescription;
        mPlaceRating = placeRating;
        mPlaceLatitude = placeLatitude;
        mPlaceLongitude = placeLongitude;
        mPlaceImageURL = placeImageURL;
    }

    Place(int type, String placeName, String placeWebAddress, String placeDescription, String placeImageURL){
        mType = type;
        mPlaceName = placeName;
        mPlaceWebAddress = placeWebAddress;
        mPlaceDescription = placeDescription;
        mPlaceImageURL = placeImageURL;
    }

    Place(int type, String placeName, String placeRating, String placeAddress, String placeDescription,
          double placeLatitude, double placeLongitude, String placeImageURL){
        mType = type;
        mPlaceName = placeName;
        mPlaceRating = placeRating;
        mPlaceAddress = placeAddress;
        mPlaceDescription = placeDescription;
        mPlaceLatitude = placeLatitude;
        mPlaceLongitude = placeLongitude;
        mPlaceImageURL = placeImageURL;
    }

    int getType(){ return mType; }

    String getPlaceName(){ return mPlaceName; }

    String getPlaceAddress(){ return mPlaceAddress; }

    String getPlaceWebAddress() { return  mPlaceWebAddress; }

    String getPlaceDescription(){ return mPlaceDescription; }

    String getPlaceRating(){ return mPlaceRating; }

    double getPlaceLatitude(){ return mPlaceLatitude; }

    double getPlaceLongitude() { return mPlaceLongitude; }

    String getPlaceImageURL() { return mPlaceImageURL; }
}
