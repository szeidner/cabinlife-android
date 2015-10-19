package com.stevezeidner.cabinlife.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stevezeidner.cabinlife.R;
import com.stevezeidner.cabinlife.network.model.Post;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final Context context;
    private final List<Post> posts;
    private final Listener listener;

    public PostAdapter(Context context, List<Post> posts, Listener listener) {
        this.context = context;
        this.posts = posts;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_post, viewGroup, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Post post = posts.get(i);

        viewHolder.title.setText(post.getTitle());
        viewHolder.description.setText(post.getSummary());
        viewHolder.location.setText(post.getLatitude() + ", " + post.getLongitude());
        viewHolder.photo.setImageURI(Uri.parse(post.getImage()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.row_posts_photo)
        public SimpleDraweeView photo;
        @Bind(R.id.row_posts_title)
        public TextView title;
        @Bind(R.id.row_posts_location)
        public TextView location;
        @Bind(R.id.row_posts_description)
        public TextView description;

        private Listener listener;

        public ViewHolder(View view, Listener listener) {
            super(view);
            this.listener = listener;

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface Listener {
        void onItemClick(int position);
    }

}
