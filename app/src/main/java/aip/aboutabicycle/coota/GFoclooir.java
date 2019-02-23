package aip.aboutabicycle.coota;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class GFoclooir extends AppCompatActivity {

    private RecyclerView amharcAthchúrsála;
    private EditText boscaCuardach;
    private TextWatcher fairtheoir;

    private ORF orf; // Obiacht Rochtain ar Fhaisnéis

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

        // amharc liosta á thúsú
        amharcAthchúrsála = findViewById(R.id.foclooir);
        amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(this));
        amharcAthchúrsála.setHasFixedSize(true);

        final FeilireNanIontrálacha feilire = new FeilireNanIontrálacha();
        amharcAthchúrsála.setAdapter(feilire);
        amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(this));

        boscaCuardach = (EditText) findViewById(R.id.cuardaigh);

        fairtheoir = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String ionchur = boscaCuardach.getText().toString();
                try {
                    ArrayList<Iontráil> aischur = orf.faighIontrálacha(ionchur);
                    feilire.cuirIontrálacha(aischur);

                } catch (Exception e) {
                    String teachtaireacht = R.string.fadhb_snáth + " " + e.getMessage();
                    Toast.makeText(getApplicationContext(), teachtaireacht, Toast.LENGTH_LONG).show();
                }
            }
        };

        boscaCuardach.addTextChangedListener(fairtheoir);

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}