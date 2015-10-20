package com.stevezeidner.cabinlife.ui.screen;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stevezeidner.cabinlife.R;
import com.stevezeidner.cabinlife.di.AppDependencies;
import com.stevezeidner.cabinlife.di.DaggerScope;
import com.stevezeidner.cabinlife.flow.Layout;
import com.stevezeidner.cabinlife.mortar.ScreenComponentFactory;
import com.stevezeidner.cabinlife.network.model.Post;
import com.stevezeidner.cabinlife.ui.activity.MainActivity;
import com.stevezeidner.cabinlife.ui.view.PostView;

import butterknife.Bind;
import dagger.Provides;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.screen_posts)
public class PostScreen extends Path implements ScreenComponentFactory<MainActivity.Component> {

    Post post;

    public PostScreen(Post post) {
        this.post = post;
    }

    @Override
    public Object createComponent(MainActivity.Component parent) {
        return DaggerPostScreen_Component.builder()
                .component(parent)
                .build();
    }

    @dagger.Module
    public class Module {

        @Provides
        @DaggerScope(Component.class)
        public Presenter providesPresenter() {
            return new Presenter(post);
        }
    }

    @dagger.Component(dependencies = MainActivity.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(PostView view);
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<PostView> {

        private Post post;

        public Presenter(Post post) {
            this.post = post;
        }

        @Bind(R.id.post_photo)
        SimpleDraweeView photo;

        @Bind(R.id.post_content)
        TextView content;

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            if (post != null) {
                load();
            }
        }

        private void load() {
            photo.setImageURI(Uri.parse(post.getImage()));
            content.setText(post.getBody());
        }
    }
}
