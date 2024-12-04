package ca.gbc.comp3074g22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private ListView orderListView;
    private List<Order> orders;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderListView = findViewById(R.id.orderListView);
        orders = new ArrayList<>();
        createDummyData(); // Add some dummy data

        OrderAdapter adapter = new OrderAdapter(this, orders);
        orderListView.setAdapter(adapter);
        back = findViewById(R.id.backButton2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search submit (optional)
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterOrders(newText);
                return true;
            }
        });
    }

    private void createDummyData() {
        orders.add(new Order("ORD001", "Open"));
        orders.add(new Order("ORD002", "Closed"));
        orders.add(new Order("ORD003", "Open"));
        orders.add(new Order("ORD004", "Closed"));
        orders.add(new Order("ORD005", "Open"));
    }

    private void filterOrders(String query) {
        List<Order> filteredList = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOrderNumber().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(order);
            }
        }
        OrderAdapter filteredAdapter = new OrderAdapter(this, filteredList);
        orderListView.setAdapter(filteredAdapter);
    }
}