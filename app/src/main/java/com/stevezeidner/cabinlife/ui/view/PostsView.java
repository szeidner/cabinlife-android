package com.stevezeidner.cabinlife.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.stevezeidner.cabinlife.R;
import com.stevezeidner.cabinlife.di.DaggerService;
import com.stevezeidner.cabinlife.ui.screen.PostsScreen;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsView extends LinearLayout {

    @Inject
    protected PostsScreen.Presenter presenter;

    @Bind(R.id.rv)
    public RecyclerView recyclerView;

    @Bind(R.id.progress)
    public ProgressBar progressBar;

    public PostsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<PostsScreen.Component>getDaggerComponent(context).inject(this);
    }

    public void show() {
        progressBar.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }
}
