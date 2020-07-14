package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.graphics.drawable.GradientDrawable;
public class EarthquakeAdapter extends ArrayAdapter<Earthquake>
{
    private static final String LOCATION_SEPARATOR="of";
    public EarthquakeAdapter(@NonNull Context context, List<Earthquake> earthquakes)
    {
        super( context, 0,earthquakes );
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from( getContext()).inflate( R.layout.earthquake_list_item,parent,false );
        }
        Earthquake currentEarthquake= getItem( position );
        // Displays the current magnitude
        // Pass the returned magnitude from Earthquake class to formatMag method below.
        String magnitudeFormat=formatMag(currentEarthquake.getMagnitude());
        //Setting up magnitude
        TextView magnitude=(TextView)listItemView.findViewById( R.id.magnitude );
        magnitude.setText( magnitudeFormat );
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        // Displays the current location
        // We are getting the string and splitting up into two text view
        String originalLocation=currentEarthquake.getLocation();
        // This is for primary location
        String primaryLocation;
        // This is for current location
        String locationOffset;
        // If it contains location offset split based on "of" we have declared off has a final string value at the start
        if(originalLocation.contains( LOCATION_SEPARATOR ))
        {
            String[] parts=originalLocation.split( LOCATION_SEPARATOR );
            locationOffset=parts[0]+LOCATION_SEPARATOR;
            primaryLocation=parts[1];
        }
        else
        {
            locationOffset=getContext().getString( R.string.near_the );
            primaryLocation=originalLocation;
        }
        // Textview for offset
        TextView offsetlocation=(TextView)listItemView.findViewById( R.id.location_offset );
        offsetlocation.setText( locationOffset );
        //Textview for primary
        TextView locationPrimary=(TextView)listItemView.findViewById( R.id.primary_location );
        locationPrimary.setText( primaryLocation );

        // TO get the data in readable format use Data class
        Date dateObject=new Date( currentEarthquake.getDate() );
        TextView date=(TextView)listItemView.findViewById( R.id.date );
        // Passing date object to a method formatDate
        String formattedDate=formatDate(dateObject);
        //setting up that returned String
        date.setText( formattedDate );
        //Displays time
        TextView time=(TextView)listItemView.findViewById( R.id.time );
        // Passing date object to a method formatTime
        String formattedTime=formatTime(dateObject);
        //setting up that returned String
        time.setText( formattedTime );
        return listItemView;
    }
    //Function that converts long time to readable date fomat
    public String formatDate(Date dateObject)
    {
        SimpleDateFormat dateformatter=new SimpleDateFormat( "LLL dd,yyyy" );
        return dateformatter.format( dateObject );
    }
    //function that converts long time to readable format
    public String formatTime(Date dateObject)
    {
        SimpleDateFormat timeformatter=new SimpleDateFormat( "h:mm a" );
        return timeformatter.format( dateObject );
    }
    //Method to format the magnitude
    public String formatMag(double magnitude)
    {
        DecimalFormat formatter=new DecimalFormat( "0.0" );
        return formatter.format( magnitude );
    }
    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor=(int)Math.floor(magnitude);
        switch (magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
        }
        // We have assigned the color location but not the actual color value
        //So we are using getColor() to refer to the particular color
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
