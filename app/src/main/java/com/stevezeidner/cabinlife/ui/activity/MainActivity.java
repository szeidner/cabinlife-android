package com.stevezeidner.cabinlife.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.stevezeidner.cabinlife.CabinLifeApplication;
import com.stevezeidner.cabinlife.R;
import com.stevezeidner.cabinlife.di.AppDependencies;
import com.stevezeidner.cabinlife.di.DaggerScope;
import com.stevezeidner.cabinlife.di.DaggerService;
import com.stevezeidner.cabinlife.flow.GsonParceler;
import com.stevezeidner.cabinlife.flow.HandlesBack;
import com.stevezeidner.cabinlife.ui.screen.PostsScreen;

import butterknife.Bind;
import butterknife.ButterKnife;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.path.Path;
import flow.path.PathContainerView;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

import static mortar.MortarScope.findChild;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Flow.Dispatcher {

    @Bind(R.id.container) PathContainerView pathContainerView;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    MortarScope mortarScope;
    FlowDelegate flowDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Crashlytics.start(this);

        mortarScope = MortarScope.findChild(getApplicationContext(), getScopeName());
        if (mortarScope == null) {
            Component component = DaggerMainActivity_Component.builder()
                    .component(DaggerService.<CabinLifeApplication.Component>getDaggerComponent(getApplicationContext()))
                    .build();

            mortarScope = MortarScope.buildChild(getApplicationContext())
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME, component)
                    .build(getClass().getName());
        }

        DaggerService.<Component>getDaggerComponent(this).inject(this);
        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // toolbar
        setSupportActionBar(toolbar);

        // navdrawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // start flow
        GsonParceler parceler = new GsonParceler(new Gson());
        @SuppressWarnings("deprecation") FlowDelegate.NonConfigurationInstance nonConfig =
                (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();
        flowDelegate = FlowDelegate.onCreate(nonConfig, getIntent(), savedInstanceState, parceler, History.single(new PostsScreen()), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        flowDelegate.onPause();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
        flowDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
            if (activityScope != null) activityScope.destroy();
        }

        super.onDestroy();
    }

    private String getScopeName() {
        return getClass().getName();
    }

    @Override
    public Object getSystemService(String name) {
        // see: https://github.com/square/mortar/issues/155
        if (getApplication() == null) {
            return super.getSystemService(name);
        }

        Object service = null;
        if (flowDelegate != null) {
            service = flowDelegate.getSystemService(name);
        }

        if (service == null && mortarScope != null && mortarScope.hasService(name)) {
            service = mortarScope.getService(name);
        }

        return service != null ? service : super.getSystemService(name);
    }

    @Override
    public void onBackPressed() {
        if (((HandlesBack) pathContainerView).onBackPressed()) return;
        if (flowDelegate.onBackPressed()) return;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_stars) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Flow.Dispatcher
    @Override
    public void dispatch(Flow.Traversal traversal, final Flow.TraversalCallback callback) {
        Path path = traversal.destination.top();
        setTitle(path.getClass().getSimpleName());
        boolean canGoBack = traversal.destination.size() > 1;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
        getSupportActionBar().setHomeButtonEnabled(canGoBack);

        pathContainerView.dispatch(traversal, new Flow.TraversalCallback() {
            @Override
            public void onTraversalCompleted() {
                invalidateOptionsMenu();
                callback.onTraversalCompleted();
            }
        });
    }

    @dagger.Component(dependencies = CabinLifeApplication.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(MainActivity activity);
    }
}
