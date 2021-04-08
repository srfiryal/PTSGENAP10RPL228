package com.example.ptsgenap10rpl228.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ptsgenap10rpl228.R;
import com.example.ptsgenap10rpl228.adapter.OurPicksAdapter;
import com.example.ptsgenap10rpl228.model.OurPicksModel;

import java.util.ArrayList;

public class OurPicksActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OurPicksAdapter adapter;
    ArrayList<OurPicksModel> arrayList;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_picks);

        recyclerView = findViewById(R.id.rv_ourPicks);
        btn_back = findViewById(R.id.img_back_ourPicks);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        
        addData();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void addData() {
        arrayList = new ArrayList<>();
        arrayList.add(new OurPicksModel("https://amindfullmom.com/wp-content/uploads/2020/11/Mashed-Potatoes-Recipe.jpg", "Resto 1", "Location"));
        arrayList.add(new OurPicksModel("https://amindfullmom.com/wp-content/uploads/2020/11/Mashed-Potatoes-Recipe.jpg", "Resto 2", "Location"));
        arrayList.add(new OurPicksModel("https://amindfullmom.com/wp-content/uploads/2020/11/Mashed-Potatoes-Recipe.jpg", "Resto 3", "Location"));
        arrayList.add(new OurPicksModel("https://amindfullmom.com/wp-content/uploads/2020/11/Mashed-Potatoes-Recipe.jpg", "Resto 4", "Location"));
        arrayList.add(new OurPicksModel("https://amindfullmom.com/wp-content/uploads/2020/11/Mashed-Potatoes-Recipe.jpg", "Resto 5", "Location"));

        adapter = new OurPicksAdapter(getApplicationContext(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OurPicksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(), arrayList.get(position).getRestaurantName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}