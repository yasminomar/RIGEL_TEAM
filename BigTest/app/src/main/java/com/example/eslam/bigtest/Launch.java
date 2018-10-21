package com.example.eslam.bigtest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Launch extends AppCompatActivity {
    private RecyclerView LBlogList;
    FirebaseDatabase Ldatabase;
    DatabaseReference LmyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch2);
        LBlogList = (RecyclerView) findViewById(R.id.myRecycleViewL);
        LBlogList.setHasFixedSize(true);
        LBlogList.setLayoutManager(new LinearLayoutManager(this));

        //send a query to the database
        Ldatabase = FirebaseDatabase.getInstance();
        LmyRef = Ldatabase.getReference("NewsL");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<NewsMainL, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<NewsMainL, BlogViewHolder>(
                        NewsMainL.class, R.layout.news_rowl,
                        BlogViewHolder.class,
                        LmyRef
                ) {
                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder, NewsMainL model, int position) {
                        viewHolder.setTitleL(model.getTitleL());
                        viewHolder.setDescL(model.getDescL());
                        viewHolder.setImageL(getApplicationContext(), model.getImageL());
                    }
                };
        LBlogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nasa.gov/launchschedule/"));
                    Intent browserChooserIntent = Intent.createChooser(browserIntent, "Choose browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);
                }

            });
        }


        public void setTitleL(String titleL) {
            TextView post_titleL = (TextView) mView.findViewById(R.id.post_titlel);
            post_titleL.setText(titleL);
        }

        public void setDescL(String descL) {
            TextView post_descL = (TextView) mView.findViewById(R.id.post_descl);
            post_descL.setText(descL);
        }

        public void setImageL(Context ctx, String imageL) {
            ImageView post_imageL = (ImageView) mView.findViewById(R.id.post_imagel);
            Picasso.with(ctx).load(imageL).into(post_imageL);
        }
    }
}
