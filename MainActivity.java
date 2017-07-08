package com.zaeem.mychat;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zaeem.mychat.Adapters.SectionPageAdapter;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    private ViewPager mviewpager;
    private SectionPageAdapter mpageAdapter;
    private TabLayout mtabllayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mToolbar = (Toolbar) findViewById(R.id.MainActivity_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MyChat");

        mviewpager = (ViewPager) findViewById(R.id.MainActivity_View_Pager);
        mpageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mviewpager.setAdapter(mpageAdapter);

        mtabllayout = (TabLayout) findViewById(R.id.MainActivity_Tab_layout);
        mtabllayout.setupWithViewPager(mviewpager);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser CurrentUser = mAuth.getCurrentUser();

        if(CurrentUser==null){
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.menu_logout_bt){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }

        return true;
    }
}
