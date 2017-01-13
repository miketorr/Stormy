package empty.stormy.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import empty.stormy.R;
import empty.stormy.adapters.HourAdapter;
import empty.stormy.weather.Hour;


public class HourlyForecastActivity extends ActionBarActivity {

    private Hour[] mHours;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        ButterKnife.bind(this);

        //add intent.get Extra - getParcelableArray(MainActivity.public TAG)
        //Receive Data through the Parcelable class Array. [Day[] can accept this data because it inherits the Parcelable interface]
        //use special array method copyOf -makes a copy of one array into another
        //Arrays.copyOf(   array we want to copy ,  length of array,  the type of array we are going to use
        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        mHours = Arrays.copyOf(parcelables, parcelables.length, Hour[].class);

        HourAdapter adapter = new HourAdapter(this, mHours);
        mRecyclerView.setAdapter(adapter);

        //layout manager for recycler list views
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //when dealing with a a fixed size, set true
        mRecyclerView.setHasFixedSize(true);
    }
}
