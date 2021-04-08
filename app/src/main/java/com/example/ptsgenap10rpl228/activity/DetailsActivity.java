package com.example.ptsgenap10rpl228.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptsgenap10rpl228.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DetailsActivity extends AppCompatActivity {

    private Bundle extras;
    private String restaurant, location, description;
    private int image;
    private ImageView img_logo;
    private TextView tv_restaurant, tv_location, tv_description;
    private LinearLayout linearLayout;
    private MaterialToolbar toolbar;
    private Button btn_seeMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        img_logo = findViewById(R.id.img_restLogo_details);
        tv_restaurant = findViewById(R.id.tv_restName_details);
        tv_location = findViewById(R.id.tv_restLoc_details);
        tv_description = findViewById(R.id.tv_restDesc_details);
        linearLayout = findViewById(R.id.background_layout);
        btn_seeMenu = findViewById(R.id.btn_seeMenu_details);

        toolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_seeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "See Menu", Toast.LENGTH_SHORT).show();
            }
        });

        extras = getIntent().getExtras();
        if (extras != null) {
            image = extras.getInt("image");
            restaurant = extras.getString("restaurant");
            location = extras.getString("location");
            description = extras.getString("description");

            getSupportActionBar().setTitle(restaurant);
        }

        img_logo.setImageResource(image);
        tv_restaurant.setText(restaurant);
        tv_location.setText(location);
        tv_description.setText(description);

        setDominantColor();
    }

    private void setDominantColor() {
        Picasso.get()
                .load(image)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Palette.from(bitmap)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(@Nullable Palette palette) {
                                        Palette.Swatch vibrant = palette.getDarkVibrantSwatch();

                                        if (vibrant != null) {
                                            int titleColor = vibrant.getRgb();
                                            linearLayout.setBackgroundColor(titleColor);
                                            btn_seeMenu.setBackgroundColor(titleColor);

                                            toolbar.setBackgroundColor(titleColor);
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                getWindow().setStatusBarColor(titleColor);
                                            }
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

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