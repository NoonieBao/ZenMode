package com.cppzeal.nightguard.component;// YourActivity.java

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.cppzeal.nightguard.R;

@Deprecated
public class YourActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Sample data for the list
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

        // Creating an ArrayAdapter to populate the ListView with the data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_list_item, R.id.textView, items);
        listView.setAdapter(adapter);

        // Setting a click listener for the button in each list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button button = view.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click here
                        // For example, you can show a toast message
                        // indicating which button was clicked
                        String itemText = items[position];
                        showToast("Button clicked for: " + itemText);
                    }
                });
            }
        });
    }

    // Method to show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
