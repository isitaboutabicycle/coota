package aip.aboutabicycle.coota;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GFoclooir extends AppCompatActivity {

    private RecyclerView amharcAthchúrsála;
    private EditText boscaCuardach;
    private TextWatcher fairtheoir;

    private ORF orf; // Obiacht Rochtain ar Fhaisnéis

    private String teaghránCuardaithe;
    private Boolean gnóthach = false;

    final FeilireNanIontrálacha feilire = new FeilireNanIontrálacha();

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

        orf = ORF.faighSampla(getApplicationContext());
        orf.oscail();

        // amhairc á dtúsú
        amharcAthchúrsála = findViewById(R.id.foclooir);
        amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(this));
        amharcAthchúrsála.setHasFixedSize(true);
        boscaCuardach = findViewById(R.id.cuardaigh);

        amharcAthchúrsála.setAdapter(feilire);
        amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(this));

        fairtheoir = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaghránCuardaithe = boscaCuardach.getText().toString().trim();
                try {
                    TascCuardaithe cuardaigh = new TascCuardaithe();
                    cuardaigh.execute();
                }
                catch (Exception e) {
                    String teachtaireacht = R.string.fadhb_snáth + " " + e.getMessage();
                    Toast.makeText(getApplicationContext(), teachtaireacht, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        boscaCuardach.addTextChangedListener(fairtheoir);

        // cnaipe gnímh ag ainliú
        FloatingActionButton cga = findViewById(R.id.cga);
        cga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TascRandamach randamach = new TascRandamach();
                randamach.execute();
                //boscaCuardach.setInputType(InputType.TYPE_NULL); TODO: deisigh
            }
        });

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onDestroy(){
        orf.dún();
        super.onDestroy();
    }


    private final class TascCuardaithe extends AsyncTask<ORF, Void, ArrayList<Iontráil>> {
        private ProgressDialog taispeántas = new ProgressDialog(GFoclooir.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.taispeántas.setMessage("Á chuardach...");
            this.taispeántas.show();
        }

        @Override
        protected ArrayList<Iontráil> doInBackground(ORF... orfs) {
            return orf.faighIontrálacha(GFoclooir.this.teaghránCuardaithe);
        }

        @Override
        protected void onPostExecute(ArrayList<Iontráil> toradh){
            GFoclooir.this.feilire.cuirIontrálacha(toradh);
            GFoclooir.this.gnóthach = false;

            if (taispeántas.isShowing()) {
                taispeántas.dismiss();
            }
        }
    }

    private final class TascRandamach extends AsyncTask<ORF, Void, ArrayList<Iontráil>> {
        private ProgressDialog taispeántas = new ProgressDialog(GFoclooir.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.taispeántas.setMessage("Á chuardach...");
            this.taispeántas.show();
        }

        @Override
        protected ArrayList<Iontráil> doInBackground(ORF... orfs) {
            return orf.iontráilRandamach(getApplicationContext());
        }

        @Override
        protected void onPostExecute(ArrayList<Iontráil> toradh){
            GFoclooir.this.feilire.cuirIontrálacha(toradh);
            GFoclooir.this.gnóthach = false;

            if (taispeántas.isShowing()) {
                taispeántas.dismiss();
            }
        }
    }
}