package empty.stormy.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import empty.stormy.R;
import empty.stormy.adapters.DayAdapter;
import empty.stormy.weather.Day;

// Can do extends ListActivity if you want the whole ACtivity to be a ListView
public class DailyForecastActivity extends Activity {

    private Day[] mDays;


    //When in a regular activity, and want to use a ListView, set member viariables and bind them to the views in teh layout xml
    @BindView(android.R.id.list)
    ListView mListView;
    @BindView(android.R.id.empty)
    TextView mEmptyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);


        //Explanation in HourlyForecastActivity
        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, mDays);
        // setListAdapter(adapter);  - used when extending ListView.. a ListView activity

        mListView.setAdapter(adapter); // when using a ListView in a regular activity
        mListView.setEmptyView(mEmptyTextView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //can do what ever we want here - like start a new activity, show a message, toast, anything when you tap the item

                String dayOfTheWeek = mDays[position].getDayOfTheWeek(); //gives the string of the day
                String conditions = mDays[position].getSummary();
                String highTemp = mDays[position].getTemperatureMax() + "";

                //Format the string you want to display, set placeholders with %s, define with , variables to be set (in order)
                String message = String.format("On %s the high will be %s and it will be %s", dayOfTheWeek, highTemp, conditions);
                //We are just toasting a messsage when tapping on item
                Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG).show();

            }
        });
    }
}

/*

    // this method for when the top class extends ListView
    //onListItemClick, is like an onClickListner for when items are tapped on a List
    //4 parameters (listview where tap occured,,,,, specific item clicked,,,,, numerical index of item in list,,,,, optional item id we can set)
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //can do what ever we want here - like start a new activity, show a message, toast, anything when you tap the item

        String dayOfTheWeek = mDays[position].getDayOfTheWeek(); //gives the string of the day
        String conditions = mDays[position].getSummary();
        String highTemp = mDays[position].getTemperatureMax() + "";

        //Format the string you want to display, set placeholders with %s, define with , variables to be set (in order)
        String message = String.format("On %s the high will be %s and it will be %s", dayOfTheWeek, highTemp, conditions);
        //We are just toasting a messsage when tapping on item
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        ;
    }
}
*/