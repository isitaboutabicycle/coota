package aip.aboutabicycle.coota;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GFoclooir extends AppCompatActivity {

    private RecyclerView amharcAthchúrsála;
    private EditText boscaCuardach;
    private TextWatcher fairtheoir;

    private SamhailAmharcNanIontrálacha samhailAmharcNanIontrálacha;
    public static final String AINM_AN_FHOCLÓRA = "Foclooir.db";
    private String LAATHAIR_CHOMHAID_AN_FHOCLOORA = "Foclooir.json";
/*
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_foclooir);

        // amharc liosta á thúsú
        amharcAthchúrsála = findViewById(R.id.foclooir);
        amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(this));
        amharcAthchúrsála.setHasFixedSize(true);

        final FeilireNanIontrálacha feilire = new FeilireNanIontrálacha();
        amharcAthchúrsála.setAdapter(feilire);
        amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(this));

        samhailAmharcNanIontrálacha =
                ViewModelProviders.of(this).get(SamhailAmharcNanIontrálacha.class);samhailAmharcNanIontrálacha.faighIontrálacha()
                .observe(this,
                        new Observer<List<Iontráil>>() {
                    @Override
                    public void onChanged(@Nullable final List<Iontráil> iontrálacha) {
                        feilire.cuirIontrálacha(iontrálacha);
                    }
                });

        boscaCuardach = (EditText) findViewById(R.id.cuardaigh);

        /*// JSON á léamh ó chomhad
        foclooir = leeighJSONooChomhad(getApplicationContext(), LAATHAIR_CHOMHAID_AN_FHOCLOORA);

        // iontraalacha á líonú
        try{
            // sraith JSON á cruthú ó na sonraí
            JSONArray foclooirJSON = new JSONArray(foclooir);
            int uimhir = foclooirJSON.length();

            for(int i = 0; i < uimhir; i++){

                String teidil, rannCainte, sainmhiiniuu;

                JSONObject jo = (JSONObject) foclooirJSON.get(i);

                if(jo.has("teidil")) {
                    teidil = (String) jo.get("teidil");

                    if (jo.has("sainmhiiniuu")) {
                        sainmhiiniuu = (String) jo.get("sainmhiiniuu");

                        if (jo.has("rannCainte")) {
                            rannCainte = (String) jo.get("rannCainte");
                            // tá chuile rud ann
                            Iontráil io = new Iontráil(teidil, rannCainte, sainmhiiniuu);
                            iontraalacha.add(io);

                        } else {
                            // tá teidil agus sainmhíniú ann
                            Iontráil io = new Iontráil(teidil, sainmhiiniuu);
                            iontraalacha.add(io);
                        }

                    } else {
                        // níl ann ach teidil
                        Iontráil io = new Iontráil(teidil);
                        iontraalacha.add(io);
                    }
                }
            }

        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Fadhb leis an JSON: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }*/

//        final FeilireNanIontrálacha adapter = new FeilireNanIontrálacha(this, iontraalacha);
//        mListView.setAdapter(adapter);

        fairtheoir = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String ionchur = boscaCuardach.getText().toString();
                try{
//                    feilire.cuirIontrálacha(samhailAmharcNanIontrálacha.uasdátaighIontrálacha(ionchur));
                    samhailAmharcNanIontrálacha.uasdátaighIontrálacha(ionchur);

                } catch (Exception e){
                    String teachtaireacht = R.string.fadhb_snáth+" "+e.getMessage();
                    Toast.makeText(getApplicationContext(), teachtaireacht, Toast.LENGTH_LONG).show();
                }
            }
        };

        boscaCuardach.addTextChangedListener(fairtheoir);

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    /*public String leeighJSONooChomhad(Context comhtheeacs, String comhad){
        String json = null;

        try{

            InputStream sruth = comhtheeacs.getAssets().open(comhad);
            int meeid = sruth.available();
            byte[] maolaan = new byte[meeid-3];
            //sruth.skip(3); // chun neamhaird a thabhairt do na beartanna truaillithe ag an tús
            //sruth.read(maolaan);
            //sruth.close();

            /*byte[] sraithTeist1 = new byte[16];
            sruth.skip(5);
            sruth.read(sraithTeist1, 0, 16);
            String teist1 = new String(sraithTeist1, "UTF-8");*/ /*

            //json = new String(maolaan, "UTF-8");

            StringBuilder toogaalaii = new StringBuilder();
            long deeanta = 0;
            byte[] maolaan2 = new byte[16];
            //sruth.skip(2537370);
            while (deeanta >= 0){
                //sruth.skip(deeanta);
                deeanta += sruth.read(maolaan2, 0, 16);
                String liine = new String(maolaan2);
                toogaalaii.append(liine);
            }
            sruth.close();
            json = toogaalaii.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), R.string.fadhb_comhad, Toast.LENGTH_LONG).show();
            return null;
        }

        return json;
    }*/

}