package com.stevezeidner.cabinlife.mortar;

import android.content.Context;
import android.util.AttributeSet;

import com.stevezeidner.cabinlife.R;
import com.stevezeidner.cabinlife.flow.FramePathContainerView;
import com.stevezeidner.cabinlife.flow.SimplePathContainer;

import flow.path.Path;

public class MortarPathContainerView extends FramePathContainerView {

    public MortarPathContainerView(Context context, AttributeSet attrs) {
        super(context, attrs, new SimplePathContainer(R.id.screen_switcher_tag, Path.contextFactory(new BasicMortarContextFactory(new ScreenScoper()))));
    }
}
