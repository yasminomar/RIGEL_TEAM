package com.example.eslam.bigtest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Forests extends AppCompatActivity {
    private RecyclerView mBlogListFor;
    FirebaseDatabase databaseFor;
    DatabaseReference myRefFor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        mBlogListFor = (RecyclerView) findViewById(R.id.viewpager);
        mBlogListFor.setHasFixedSize(true);
        mBlogListFor.setLayoutManager(new LinearLayoutManager(this));
        databaseFor = FirebaseDatabase.getInstance();
        myRefFor = databaseFor.getReference("NewsForests");
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<NewsFor, FBlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<NewsFor, FBlogViewHolder>(
                        NewsFor.class, R.layout.news_row_for,
                        FBlogViewHolder.class,
                        myRefFor
                ) {
                    @Override
                    protected void populateViewHolder(FBlogViewHolder fviewHolder, NewsFor model, int position) {
                        fviewHolder.setTitlefor(model.getTitlefor());
                        fviewHolder.setDescfor(model.getDescfor());
                        fviewHolder.setImagefor(getApplicationContext(), model.getImagefor());
                    }
                };
        mBlogListFor.setAdapter(firebaseRecyclerAdapter);
    }
    public static class FBlogViewHolder extends RecyclerView.ViewHolder
    {
        View mView ;
        public FBlogViewHolder (View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.unenvironment.org/explore-topics/forests/about-forests/"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent, "Choose browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);
                }

            });
        }


        public void setTitlefor(String titlefor)
        {
            TextView post_title_for = (TextView)mView.findViewById(R.id.post_title_for);
            post_title_for.setText(titlefor);
        }
        public void setDescfor(String descfor){
            TextView post_desc_for = (TextView)mView.findViewById(R.id.post_desc_for);
            post_desc_for.setText(descfor);
        }
        public void setImagefor(Context ctx , String imagefor)
        {
            ImageView post_image_for = (ImageView)mView.findViewById(R.id.post_image_for);
            Picasso.with(ctx).load(imagefor).into(post_image_for);
        }
    }
}
