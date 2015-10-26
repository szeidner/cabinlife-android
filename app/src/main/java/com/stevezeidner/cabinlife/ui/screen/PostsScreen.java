package com.stevezeidner.cabinlife.ui.screen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.stevezeidner.cabinlife.R;
import com.stevezeidner.cabinlife.adapter.PostAdapter;
import com.stevezeidner.cabinlife.di.AppDependencies;
import com.stevezeidner.cabinlife.di.DaggerScope;
import com.stevezeidner.cabinlife.mortar.ScreenComponentFactory;
import com.stevezeidner.cabinlife.network.RestClient;
import com.stevezeidner.cabinlife.network.model.Post;
import com.stevezeidner.cabinlife.ui.activity.MainActivity;
import com.stevezeidner.cabinlife.ui.view.PostsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import flow.Flow;
import flownavigation.common.flow.Layout;
import flownavigation.path.Path;
import mortar.ViewPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@Layout(R.layout.screen_posts)
public class PostsScreen extends Path implements ScreenComponentFactory<MainActivity.Component> {

    @Override
    public Object createComponent(MainActivity.Component parent) {
        return DaggerPostsScreen_Component.builder()
                .component(parent)
                .build();
    }

    @dagger.Component(dependencies = MainActivity.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(PostsView view);
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<PostsView> implements PostAdapter.Listener {

        private final RestClient restClient;

        private PostAdapter adapter;
        private List<Post> posts = new ArrayList<>();

        @Inject
        public Presenter(RestClient restClient) {
            this.restClient = restClient;
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            getView().recyclerView.setLayoutManager(layoutManager);

            adapter = new PostAdapter(getView().getContext(), posts, this);
            getView().recyclerView.setAdapter(adapter);

            if (posts.isEmpty()) {
                load();
            }
        }

        private void load() {
            restClient.getPostsService().getPosts()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Post>>() {
                        @Override
                        public void onCompleted() {
                            Timber.e("Completed");
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (!hasView()) return;
                            Timber.d("Failure %s", e.getMessage());
                        }

                        @Override
                        public void onNext(List<Post> returnedPosts) {
                            if (!hasView()) return;
                            Timber.d("Success loaded %s", returnedPosts.size());

                            // add cache busting
                            for (Post p : returnedPosts) {
                                p.setImage(p.getImage() + "?cache=" + System.nanoTime());
                            }

                            posts.clear();
                            posts.addAll(returnedPosts);
                            adapter.notifyDataSetChanged();

                            getView().show();
                        }
                    });
        }

        @Override
        public void onItemClick(int position) {
            if (!hasView()) return;

            Post post = posts.get(position);
            Flow.get(getView()).set(new PostScreen(post));
        }
    }
}
