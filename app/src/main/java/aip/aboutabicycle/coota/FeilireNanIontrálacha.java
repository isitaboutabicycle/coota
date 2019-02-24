package aip.aboutabicycle.coota;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        Iontráil iontráilSeo = iontrálacha. get(position);
        holder.amharcTéacsAnCheannfhocail.setText(iontráilSeo.getCeannfhocal());
        holder.amharcTéacsAntSainmhínithe.setText(iontráilSeo.getSainmhíniú());
    }

    @Override
    public int getItemCount() {
        return iontrálacha.size();
    }

    public void cuirIontrálacha(List<Iontráil> liosta) {
        this.iontrálacha = liosta;
        notifyDataSetChanged(); //TODO: tá bealaí níos fearr ann
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
}