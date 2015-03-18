package com.yinli.yinli_ylscrollview;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements Sample1Fragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Sample1Fragment fragment = new Sample1Fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //noinspection SimplifiableIfStatement
        if (id == R.id.sample1) {
            Sample1Fragment fragment = new Sample1Fragment();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
            return true;
        }
        if (id == R.id.sample2) {
            Sample2Fragment fragment = new Sample2Fragment();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
            return true;
        }
        if (id == R.id.sample3) {
            Sample3Fragment fragment = new Sample3Fragment();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
