package ca.gbc.comp3074g22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import ca.gbc.comp3074g22.Dialogs.AddSectionDialog;

import java.util.ArrayList;

public class ManagePOSActivity extends AppCompatActivity implements AddSectionDialog.SectionAddedListener {

    RecyclerView recyclerView;
    private ArrayList<String> sectionList;
    private CardAdapter adapter;
    private FloatingActionButton fab;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_posactivity);

        // Set edge-to-edge insets for the activity
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        back = findViewById(R.id.imageViewBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagePOSActivity.this, AdminPortalActivity.class);
                startActivity(i);
                finish();
            }
        });

        // Initialize RecyclerView and FloatingActionButton
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the section list and adapter
        sectionList = new ArrayList<>();
        adapter = new CardAdapter(sectionList);
        recyclerView.setAdapter(adapter);

        // Handle FloatingActionButton click to show the dialog
        fab.setOnClickListener(v -> {
            AddSectionDialog dialog = new AddSectionDialog();
            dialog.setSectionAddedListener(this); // Set the callback
            dialog.showDialog(ManagePOSActivity.this);
        });
    }

    @Override
    public void onSectionAdded(String sectionName) {
        // Update the RecyclerView when a new section is added
        sectionList.add(sectionName);
        adapter.notifyItemInserted(sectionList.size() - 1);
        Toast.makeText(this, "Section added: " + sectionName, Toast.LENGTH_SHORT).show();
    }
}