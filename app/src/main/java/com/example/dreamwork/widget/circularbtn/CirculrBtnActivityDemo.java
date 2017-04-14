package com.example.dreamwork.widget.circularbtn;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dreamwork.R;

public class CirculrBtnActivityDemo extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] items = getResources().getStringArray(R.array.sample_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                SampleOneActivity.startThisActivity(this);
                break;
            case 1:
                SampleTwoActivity.startThisActivity(this);
                break;
            case 2:
                SampleThreeActivity.startThisActivity(this);
                break;
            case 3:
                SampleFourActivity.startThisActivity(this);
                break;
            case 4:
                SampleFiveActivity.startThisActivity(this);
                break;
        }
    }
}
