package com.stevezeidner.cabinlife.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stevezeidner.cabinlife.R;
import com.stevezeidner.cabinlife.di.DaggerService;
import com.stevezeidner.cabinlife.ui.screen.PostScreen;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostView extends FrameLayout {

    @Bind(R.id.post_photo)
    public SimpleDraweeView photo;

    @Bind(R.id.post_content)
    public TextView content;

    @Bind(R.id.post_scrollview)
    public ScrollView scrollView;

    @Inject
    protected PostScreen.Presenter presenter;

    public PostView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<PostScreen.Component>getDaggerComponent(context).inject(this);
    }

    public void show() {
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
