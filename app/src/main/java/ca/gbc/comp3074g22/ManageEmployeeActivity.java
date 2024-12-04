package ca.gbc.comp3074g22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import ca.gbc.comp3074g22.Dialogs.AddEmployeeDialog;

import java.util.ArrayList;

public class ManageEmployeeActivity extends AppCompatActivity implements AddEmployeeDialog.EmployeeAddedListener {

    RecyclerView recyclerView;
    private ArrayList<String> employeeList;
    private CardAdapter adapter;
    private FloatingActionButton fab;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employee);

        // Initialize RecyclerView and FloatingActionButton
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the employee list and adapter
        employeeList = new ArrayList<>();
        adapter = new CardAdapter(employeeList);
        recyclerView.setAdapter(adapter);

        // Handle FloatingActionButton click to show the dialog
        fab.setOnClickListener(v -> {
            AddEmployeeDialog dialog = new AddEmployeeDialog();
            dialog.setEmployeeAddedListener(this); // Set the callback for employee added
            dialog.showDialog(ManageEmployeeActivity.this);
        });

        // Set up back button to navigate to the Admin portal
        back = findViewById(R.id.imageViewBackButton);
        back.setOnClickListener(v -> {
            Intent i = new Intent(ManageEmployeeActivity.this, AdminPortalActivity.class);
            startActivity(i);
            finish();
        });
    }

    @Override
    public void onEmployeeAdded(String employeeName) {
        // Update the RecyclerView when a new employee is added
        employeeList.add(employeeName);
        adapter.notifyItemInserted(employeeList.size() - 1);
        Toast.makeText(this, "Employee added: " + employeeName, Toast.LENGTH_SHORT).show();
    }
}