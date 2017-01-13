package empty.stormy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import empty.stormy.R;
import empty.stormy.weather.Day;


// to LIST VIEW

public class DayAdapter extends BaseAdapter {   //****baseadapter

    private Context mContext;
    private Day[] mDays;

    public DayAdapter(Context context, Day[] days) {
        mContext = context;
        mDays = days;
    }

    @Override
    public int getCount() {
        return mDays.length;
    } // length of array

    @Override
    public Object getItem(int position) {
        return mDays[position];
    } //get at position[0#]

    @Override
    public long getItemId(int position) {
        return 0; // we aren't going to use this. Tag items for easy reference
    }

    private static class ViewHolder {
        ImageView iconImageView; // public by default
        TextView temperatureLabel;
        TextView dayLabel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // THIS IS WHERE MAPPING OCCURS
        //convertView parameter -- the view object that we want to re use - first time getView() is called this will be null. so the first time, we will have to create it and set it up

        ViewHolder holder;

        if (convertView == null) {
            //create the convertView by inflating it from the context using a layout inflater
            // a layout inflater is an Android object that takes XML layouts and turns them into views in code that we can use - get it from the context

            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder(); // initialize theViewHolder holder variable up top
            //then set the ViewHolder variables
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);        //// can call findViewById(r.id.) from the convertView we just inflated.
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.dayNameLabel);

            //set tag for the (holder) view we will reuse below in the else statement
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();  // cast the parameter convertView as a (ViewHolder) class
        }

        //Create day object based on the position in array --- given in parameter passed
        Day day = mDays[position];
        //and set the data
        holder.iconImageView.setImageResource(day.getIconId());
        holder.temperatureLabel.setText(day.getTemperatureMax() + "");

        if (position == 0) {
            holder.dayLabel.setText("Today");
        }
        else {
            holder.dayLabel.setText(day.getDayOfTheWeek());
        }

        return convertView; //will refresh data on subsequent day/view
    }


}

