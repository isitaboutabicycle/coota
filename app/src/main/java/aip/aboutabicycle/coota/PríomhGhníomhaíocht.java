package aip.aboutabicycle.coota;

import aip.aboutabicycle.coota.ui.foclóir.BlúireAnFhoclóra;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class PríomhGhníomhaíocht extends AppCompatActivity {

    final FragmentManager bainisteoirNamBlúirí = getSupportFragmentManager();
    final Fragment blúireAnFhoclóra = new BlúireAnFhoclóra();
//    final Fragment blúireNagCeanán = new BlúireNagCeanán();

    private Fragment gníomhaíoch;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(gníomhaíoch != blúireAnFhoclóra){
                        bainisteoirNamBlúirí.beginTransaction().show(blúireAnFhoclóra).commit();
                        bainisteoirNamBlúirí.beginTransaction().hide(gníomhaíoch).commit();
                        gníomhaíoch = blúireAnFhoclóra;
                        return true;
                    }
                    return false;
                case R.id.navigation_favourites:
//                    if(gníomhaíoch != blúireNagCeanáin){
//                        bainisteoirNamBlúirí.beginTransaction().show(blúireNagCeanán).commit();
//                        bainisteoirNamBlúirí.beginTransaction().hide(gníomhaíoch).commit();
//                        gníomhaíoch = blúireNagCeanán;
//                    }
                    return true;
                //case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                //    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.priiomh_ghniiomhaiiocht);

        // tasc tosaithe á rith
        new TascTosaithe().execute();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    protected void onDestroy() {
        ORF.faighSampla(this).dún();
        super.onDestroy();
    }


    private final class TascTosaithe extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            bainisteoirNamBlúirí.beginTransaction()
                    .add(R.id.priiomh_choimeaadaan, blúireAnFhoclóra, "foclóir")
                    //.hide(blúireAnFhoclóra)
                    .commit();

            gníomhaíoch = blúireAnFhoclóra;
            return true;
        }
    }

}