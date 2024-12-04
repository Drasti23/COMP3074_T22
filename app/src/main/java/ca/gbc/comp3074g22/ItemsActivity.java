package ca.gbc.comp3074g22;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

import ca.gbc.comp3074g22.Dialogs.AddItemDialog;

public class ItemsActivity extends AppCompatActivity implements AddItemDialog.ItemAddedListener {
    ListView itemListView;
    ArrayList<String> itemNames;
    ArrayList<String> itemPrices;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fab = findViewById(R.id.fab2);
        itemListView = findViewById(R.id.itemList);
        itemNames = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemNames);

        adapter.notifyDataSetChanged();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDummyData();
                AddItemDialog dialog = new AddItemDialog();
                dialog.showDialog(ItemsActivity.this);
            }
        });

    }
    public void setDummyData(){
        itemNames.add("item1");
        itemNames.add("item2");
        itemNames.add("item3");
        itemNames.add("item4");
        itemNames.add("item5");
        itemNames.add("item6");
    }

    @Override
    public void onItemAdded(String itemName, String itemPrice) {
        itemNames.add(itemName);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Item added: " + itemName, Toast.LENGTH_SHORT).show();
    }
}