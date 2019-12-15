package aip.aboutabicycle.coota.ui.foclóir;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import aip.aboutabicycle.coota.BainisteoirNaLeaganachaAmach;
import aip.aboutabicycle.coota.FeilireNanIontrálacha;
import aip.aboutabicycle.coota.Iontráil;
import aip.aboutabicycle.coota.ORF;
import aip.aboutabicycle.coota.R;

import static java.lang.String.valueOf;

public class BlúireAnFhoclóra extends Fragment {

    private ORF orf; // Obiacht Rochtain ar Fhaisnéis
    private RecyclerView amharcAthchúrsála;
    private EditText boscaCuardach;
    private TextWatcher fairtheoir;
    private static String teaghránCuardaithe;

    final FeilireNanIontrálacha feilire = new FeilireNanIontrálacha();

    public BlúireAnFhoclóra(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.bluuire_an_fhocloora, container, false);
        // initialising database and views
        orf = ORF.faighSampla(getActivity());
        orf.oscail();

        // amhairc á dtúsú
        amharcAthchúrsála = root.findViewById(R.id.foclooir);
        amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(getActivity()));
        amharcAthchúrsála.setHasFixedSize(true);
        boscaCuardach = root.findViewById(R.id.cuardaigh);

        amharcAthchúrsála.setAdapter(feilire);
        //amharcAthchúrsála.setLayoutManager(new LinearLayoutManager(this));
        amharcAthchúrsála.setLayoutManager(new BainisteoirNaLeaganachaAmach(getActivity()));

        fairtheoir = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                teaghránCuardaithe = boscaCuardach.getText().toString().trim();
                try {
                    BlúireAnFhoclóra.TascCuardaithe cuardaigh = new BlúireAnFhoclóra.TascCuardaithe();
                    cuardaigh.execute(orf);
                }
                catch (Exception e) {
                    String teachtaireacht = R.string.fadhb_snáth + " " + e.getMessage();
                    Toast.makeText(getActivity(), teachtaireacht, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        boscaCuardach.addTextChangedListener(fairtheoir);

        // cnaipe gnímh ag ainliú
        FloatingActionButton cga = root.findViewById(R.id.cga);
        cga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlúireAnFhoclóra.TascRandamach randamach = new BlúireAnFhoclóra.TascRandamach();
                randamach.execute(orf);
                //boscaCuardach.setInputType(InputType.TYPE_NULL); TODO: deisigh
            }
        });

        return root;
    }


    public static String faighTeaghránCuardaithe(){
        return valueOf(teaghránCuardaithe);
    }



    private final class TascCuardaithe extends AsyncTask<ORF, Void, ArrayList<Iontráil>> {
        private ProgressDialog taispeántas = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.taispeántas.setMessage("Á chuardach...");
            this.taispeántas.show();
        }

        @Override
        protected ArrayList<Iontráil> doInBackground(ORF... orfs) {
            return orfs[orfs.length-1].faighIontrálacha(BlúireAnFhoclóra.this.teaghránCuardaithe);
        }

        @Override
        protected void onPostExecute(ArrayList<Iontráil> toradh){
            BlúireAnFhoclóra.this.feilire.cuirIontrálacha(toradh);

            if (taispeántas.isShowing()) {
                taispeántas.dismiss();
            }
        }
    }

    private final class TascRandamach extends AsyncTask<ORF, Void, ArrayList<Iontráil>> {
        private ProgressDialog taispeántas = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.taispeántas.setMessage("Á chuardach...");
            this.taispeántas.show();
        }

        @Override
        protected ArrayList<Iontráil> doInBackground(ORF... orfs) {
            return orfs[orfs.length-1].iontráilRandamach(getActivity());
        }

        @Override
        protected void onPostExecute(ArrayList<Iontráil> toradh){
            BlúireAnFhoclóra.this.feilire.cuirIontrálacha(toradh);

            if (taispeántas.isShowing()) {
                taispeántas.dismiss();
            }
        }
    }

}
