package com.example.eslam.bigtest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int SPLASH_TIME_OUT = 4000;
    private RecyclerView mBlogList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBlogList = (RecyclerView) findViewById(R.id.myRecycleView);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        //send a query to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("News");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<NewsMain, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<NewsMain, BlogViewHolder>(
                        NewsMain.class, R.layout.news_row,
                        BlogViewHolder.class,
                        myRef
                ) {
                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder, NewsMain model, int position) {
                        viewHolder.setTitle(model.getTitle());
                        viewHolder.setDesc(model.getDesc());
                        viewHolder.setImage(getApplicationContext(), model.getImage());
                    }
                };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView ;
        public BlogViewHolder (View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nasa.gov/"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent, "Choose browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);
                }

            });
        }


        public void setTitle (String title)
        {
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx , String image)
        {
            ImageView post_image = (ImageView)mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void displayselectedscreen(int id) {

        Fragment fragment = null;
        switch (id) {
            case R.id.nav_history:
                fragment = new dataFragmentHis();
                break;
            case R.id.nav_contin:
                fragment = new dataFragmentcon();
                break;
            case R.id.nav_forests:
                fragment = new dataFragment();
                break;
            case R.id.nav_mountains:
                fragment = new dataFragmentMou();
                break;
            case R.id.nav_oceans:
                fragment = new dataFragmentOce();
                break;
            case R.id.home:{
                Intent h = new Intent(MainActivity.this,MainActivity.class);
                startActivity(h);
                break;}
            case R.id.launch:{
                Intent h = new Intent(MainActivity.this,Launch.class);
                startActivity(h);}
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        displayselectedscreen(id);
        return true;
    }
}


