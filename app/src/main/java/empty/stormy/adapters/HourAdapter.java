package empty.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import empty.stormy.R;
import empty.stormy.weather.Hour;


// HOUR ADAPTER USING RECYCLER VIEW


public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Hour[] mHours;
    private Context mContext; // Since are in a custom Adapter, we have to pass in the Context where the adapter is being used - through the adapter Constructor

    //context object used to grab resources tha are global to your application - like string resources
    //if changing a class constructor, make sure to make changes to all affected coded

    public HourAdapter(Context context, Hour[] hours) {
        mContext = context;
        mHours = hours;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //this creates new view holders when needed
        View view = LayoutInflater.from(parent.getContext())         //inflate a layout like we do in a generic listview, can get context with parent.getContext()
                .inflate(R.layout.hourly_list_item, parent, false);  //pass in ID of the layout file we created, pass parent parameter, root boolean to false
        HourViewHolder viewHolder = new HourViewHolder(view);       // Create new viewHolder object with the view we just inflated, with the class we made below
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        //this method is the bridge between the adapter and bind method we created in our below viewHolder class
        holder.bindHour(mHours[position]); //call bindHour metho to correct position in mHours array[].
    }

    @Override
    public int getItemCount() {
        return mHours.length;
    } // the length of the array





    //HourViewHolder class nested within Adapter class for the recycler view items Below
    //since the class is nested - we can access all of the PROPERTIES within the class it is nested in

    //extends - for using of other class methods -extending another class' exact methods
    //implements - for using interface - you have to define what happens in the interfaces's methods // alt+enter to complete the implement View.OnclickListner// we're implmenting viewOnlick listner to handle tap on items in different ways
    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
            mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
            mTemperatureLabel = (TextView) itemView.findViewById(R.id.temperatureLabel);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);

            //for each item view ( HourViewHolder), we have to attach an onClickListener so that an action can happen when tapped
            itemView.setOnClickListener(this); // the listener is (this) class. (we implement the Onclick Listener)

        }

        public void bindHour(Hour hour) {
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());
        }

        //crated from generated methods from View.OnclickListner interface
        @Override
        public void onClick(View v) {
            //can do whatever here- start new activity, display a message.. etc..

            String time = mTimeLabel.getText().toString(); //using values from hour binded Hour properts from bindHour();
            String temperature = mTemperatureLabel.getText().toString(); //.toString because .get text() return a char
            String summary = mSummaryLabel.getText().toString();
            String message = String.format("At %s it will be %s and %s", time, temperature, summary);

            //for the Context parameter, add a member variable to the orinal class - HourAdapter- and pass and define in its constructor
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

        }
    }
}


