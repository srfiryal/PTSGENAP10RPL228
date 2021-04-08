package com.example.ptsgenap10rpl228.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.ptsgenap10rpl228.R;
import com.example.ptsgenap10rpl228.adapter.RecommendationsAdapter;
import com.example.ptsgenap10rpl228.model.RecommendationsModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class RecommendationsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecommendationsAdapter adapter;
    ArrayList<RecommendationsModel> arrayList;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        recyclerView = findViewById(R.id.rv_recommendations);
        toolbar = findViewById(R.id.toolbar_recommendations);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        
        addData();
    }

    private void addData() {
        arrayList = new ArrayList<>();
        arrayList.add(new RecommendationsModel(R.drawable.logo_burgerking, "Burger King", "8 Upland Dr, Del Sol Valley, CA", "Burger King (BK) is an American multinational chain of hamburger fast food restaurants. Burger King's menu has expanded from a basic offering of burgers, French fries, sodas, and milkshakes to a larger and more diverse set of products. In 1957, the \"Whopper\" became the first major addition to the menu, and it has become Burger King's signature product since."));
        arrayList.add(new RecommendationsModel(R.drawable.logo_tacobell, "Taco Bell", "7 Steppes Dr, Britechester, CA", "Taco Bell is an American-based chain of fast food restaurants originating in Irvine, California in 1962, by founder Glen Bell. Taco Bell is a subsidiary of Yum! Brands, Inc. The restaurants serve a variety of Mexican-inspired foods, that include: tacos, burritos, quesadillas, nachos, novelty and specialty items, along with a variety of \"value menu\" items. As of 2018, Taco Bell serves over two billion customers each year, at 7,072 restaurants, more than 93 percent of which are owned and operated by independent franchisees and licensees."));
        arrayList.add(new RecommendationsModel(R.drawable.logo_starbucks, "Starbucks", "12 Pier Dr, Windenburg, CA", "Starbucks Corporation is an American multinational chain of coffeehouses and roastery reserves headquartered in Seattle, Washington. As the world's largest coffeehouse chain, Starbucks is seen to be the main representation of the United States' second wave of coffee culture. Starbucks locations serve hot and cold drinks, whole-bean coffee, microground instant coffee known as VIA, espresso, caffe latte, full- and loose-leaf teas including Teavana tea products, Evolution Fresh juices, Frappuccino beverages, La Boulange pastries, and snacks including items such as chips and crackers."));
        arrayList.add(new RecommendationsModel(R.drawable.logo_genkisushi, "Genki Sushi", "5 Ophelia Drive, Willow Creek, CA", "Genki Sushi is a chain of conveyor belt sushi restaurants established in 1990 in Japan. The chain expanded to include locations in Japan, Hong Kong, Indonesia, Singapore, Kuwait, the Philippines, China, Australia, Cambodia, Myanmar and the United States, including, California, Hawaii and Washington. Genki Sushi restaurants in Hong Kong are operated by Maxim's Caterers."));
        arrayList.add(new RecommendationsModel(R.drawable.logo_texaschicken, "Texas Chicken", "9 Chateau Dr, Del Sol Valley, CA", "Texas Chicken is an American chain of fast food restaurants specializing in fried chicken, also trading outside North America as Texas Chicken or Church’s Texas Chicken. The chain was founded as Church's Fried Chicken To Go by George W. Church Sr., on April 17, 1952, in San Antonio, Texas, across the street from The Alamo."));

        adapter = new RecommendationsAdapter(getApplicationContext(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecommendationsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("image", arrayList.get(position).getImageID());
                intent.putExtra("restaurant", arrayList.get(position).getRestName());
                intent.putExtra("location", arrayList.get(position).getRestLocation());
                intent.putExtra("description", arrayList.get(position).getRestDescription());

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}