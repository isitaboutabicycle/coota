package aip.aboutabicycle.coota;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static aip.aboutabicycle.coota.ui.foclóir.BlúireAnFhoclóra.faighTeaghránCuardaithe;

public class FeilireNanIontrálacha extends RecyclerView.Adapter<FeilireNanIontrálacha.CoimeádaíNanIontrálacha>{
    private List<Iontráil> iontrálacha = new ArrayList<Iontráil>();

    @NonNull
    @Override
    public CoimeádaíNanIontrálacha onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View amharcRuda = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.iontraail_feein, parent, false);

        return new CoimeádaíNanIontrálacha(amharcRuda);
    }

    @Override
    public void onBindViewHolder(@NonNull CoimeádaíNanIontrálacha holder, int position) {
        Iontráil iontráilSeo = iontrálacha.get(position);
        String ceann = iontráilSeo.getCeannfhocal();
        String sainmhíniú = iontráilSeo.getSainmhíniú();

        holder.amharcTéacsAnCheannfhocail.setText(ceann);
        holder.amharcTéacsAntSainmhínithe.setText(sainmhíniú);
//        TascAibhsithe aibhsigh = new TascAibhsithe();
//        Object[] ionchur = new Object[3];
//        ionchur[0] = holder;
//        ionchur[1] = ceann;
//        ionchur[2] = sainmhíniú;
//        aibhsigh.execute(ionchur);
        //holder.amharcTéacsAnCheannfhocail.setText(iontráilSeo.getCeannfhocal());
        //holder.amharcTéacsAntSainmhínithe.setText(iontráilSeo.getSainmhíniú());
    }

    @Override
    public int getItemCount() {
        return iontrálacha.size();
    }

    public void cuirIontrálacha(List<Iontráil> liosta) {
        this.iontrálacha = liosta;
        // TODO: déan an aibhsiú anseo?
        notifyDataSetChanged(); //TODO: tá bealaí níos fearr ann
    }


    private Spannable aibhsigh(String cuardaithe, String leCuardach){
        Spannable inréisithe = Spannable.Factory.getInstance().newSpannable(leCuardach);

        if(cuardaithe.length() > 2){
            //aimsigh an téarma san iontráil
            ArrayList<Integer> samplaí = new ArrayList<>();
            int innéacs = 0;
            while(true){
                innéacs = leCuardach.indexOf(cuardaithe, innéacs);
                if(innéacs < 0){
                    break; // má tá, ná cuir sa liosta é
                }
                samplaí.add(innéacs);
            }
            //cuir na samplaí aimsithe sna réimsí inréisithe
            for (int i=0; i<samplaí.size(); i++){
                int tús = samplaí.get(i);
                int deireadh = tús+cuardaithe.length();
                inréisithe.setSpan(new BackgroundColorSpan(Color.YELLOW), tús, deireadh, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return inréisithe;
    }




    class CoimeádaíNanIontrálacha extends RecyclerView.ViewHolder {
        private TextView amharcTéacsAnCheannfhocail;
        private TextView amharcTéacsAntSainmhínithe;

        public CoimeádaíNanIontrálacha(View amharcRuda) {
            super(amharcRuda);

            amharcTéacsAnCheannfhocail = amharcRuda.findViewById(R.id.amharc_téacs_an_cheannfhocail);
            amharcTéacsAntSainmhínithe = amharcRuda.findViewById(R.id.amharc_téics_an_tsainmhínithe);
        }
    }




    private final class TascAibhsithe extends AsyncTask<Object[], Void, Object[]> {
        @Override
        protected void onPreExecute(){ }

        @Override
        protected Object[] doInBackground(Object[]... objects){
            Object[] ionchur = objects[objects.length-1];
            String ceann = (String) ionchur[1];
            String sainmhíniú = (String) ionchur[2];
            String cuardaithe = faighTeaghránCuardaithe();

            ionchur[1] = aibhsigh(cuardaithe, ceann);
            ionchur[2] = aibhsigh(cuardaithe, sainmhíniú);

            return ionchur;
        }

        @Override
        protected void onPostExecute(Object[] toradh){
            CoimeádaíNanIontrálacha coimeádaí = (CoimeádaíNanIontrálacha) toradh[0];
            Spannable ceann = (Spannable) toradh[1];
            Spannable sainmhíniú = (Spannable) toradh[2];

            coimeádaí.amharcTéacsAnCheannfhocail.setText(ceann);
            coimeádaí.amharcTéacsAntSainmhínithe.setText(sainmhíniú);
        }
    }
}