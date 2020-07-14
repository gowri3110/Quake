package com.example.android.quakereport;

public class Earthquake
{
    private String mLocation;
    private double mMagnitude;
    private long mDate;
    private String mUrl;
    public Earthquake(double magnitude,String country,long date,String url)
    {
        mMagnitude=magnitude;
        mLocation=country;
        mDate=date;
        mUrl=url;
    }
    public double getMagnitude()
    {
        return mMagnitude;
    }
    public String getLocation()
    {
        return mLocation;
    }
    public long getDate()
    {
        return mDate;
    }
    public String getUrl()
    {
        return mUrl;
    }


}
