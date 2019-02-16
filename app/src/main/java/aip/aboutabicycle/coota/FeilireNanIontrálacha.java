package aip.aboutabicycle.coota;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cathal on 20/02/2018.
 */

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
        notifyDataSetChanged(); //TODO: tá leaganacha níos fearr ann
    }

    /*private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Iontráil> mDataSource;
    private ArrayList<Iontráil> cooipSonraii;

    public FeilireNanIontrálacha(Context comhtheeacs, ArrayList<Iontráil> baill){
        mContext = comhtheeacs;
        mDataSource = baill;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cooipSonraii = new ArrayList<Iontráil>();

        cooipSonraii.addAll(baill);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; // id uathúil do gach ball liosta - d'fhéadfadh sé a bheith níos snasta
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.iontráil, parent, false);

        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.TeidilNahIontraala);

        /* // Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.RannCainteNahIontraala);

        // Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.SainmhiiniuuNahIontraala);*//*

        // sonraí á gcur isteach san amharc
        Iontráil iontraail = (Iontráil) getItem(position);
        boolean rannAnn = false;
        if(iontraail.rannCainte != null && iontraail.rannCainte != ""){rannAnn = true;}
        titleTextView.setText(iontraail.teidil);
        /*if(rannAnn){
            subtitleTextView.setText(
                    (iontráil.rannCainte == iontráil.sainmhiiniuu) ? "" : "\t\t("+iontráil.rannCainte+")"
            );
        } else {
            subtitleTextView.setText("");
        }
        detailTextView.setText(
                (iontráil.sainmhiiniuu == "" || iontráil.sainmhiiniuu == null) ? iontráil.rannCainte : iontráil.sainmhiiniuu
        );*//*

        // cló á roghnú
        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "bunchlo.ttf");
        Typeface descriptionTypeFace = Typeface.createFromAsset(mContext.getAssets(), "urchlo.ttf");
        titleTextView.setTypeface(titleTypeFace);
        //subtitleTextView.setTypeface(descriptionTypeFace);
        //detailTextView.setTypeface(descriptionTypeFace);

        return rowView;
    }*/

    /*public void filter(String charText) {

        mDataSource.clear();
        if (charText.length() == 0) {
            mDataSource.addAll(cooipSonraii);
        }
        else
        {
            String cuardaithe = charText.toLowerCase(Locale.getDefault());
            String cuardaitheCaighdeaanach = UirlisíTeanga.bainSíntíGoCeart(cuardaithe);

            for (Iontráil iontraail : cooipSonraii )
            {
                //todo: glac caractair le buailte ⁊rl freisin
                String teidilLom = UirlisíTeanga.bainSíntíGoLom(iontraail.teidil.toLowerCase(Locale.getDefault()));
                String teidilCaighdeaanach = UirlisíTeanga.bainSíntíGoCeart(iontraail.teidil.toLowerCase(Locale.getDefault()));

                if (teidilLom.contains(cuardaithe)
                        || teidilLom.contains(cuardaitheCaighdeaanach)
                        || teidilCaighdeaanach.contains(cuardaithe)
                        || teidilCaighdeaanach.contains(cuardaitheCaighdeaanach))
                {
                    mDataSource.add(iontraail);
                }
            }

            if(mDataSource.size() < 2){
                for (Iontráil iontraail : cooipSonraii )
                {
                    String rannCainteLom = UirlisíTeanga.bainSíntíGoLom(iontraail.rannCainte.toLowerCase(Locale.getDefault()));
                    String rannCaighdeaanach = UirlisíTeanga.bainSíntíGoCeart(iontraail.rannCainte.toLowerCase(Locale.getDefault()));
                    String sainmhiiniuuLom = UirlisíTeanga.bainSíntíGoLom(iontraail.sainmhiiniuu.toLowerCase(Locale.getDefault()));
                    String sainmhiiniuuCaighdeaanach = UirlisíTeanga.bainSíntíGoCeart(iontraail.sainmhiiniuu.toLowerCase(Locale.getDefault()));

                    if (       rannCainteLom.contains(cuardaithe)
                            || rannCainteLom.contains(cuardaitheCaighdeaanach)
                            || rannCaighdeaanach.contains(cuardaithe)
                            || rannCaighdeaanach.contains(cuardaitheCaighdeaanach)
                            || sainmhiiniuuLom.contains(cuardaithe)
                            || sainmhiiniuuLom.contains(cuardaitheCaighdeaanach)
                            || sainmhiiniuuCaighdeaanach.contains(cuardaithe)
                            || sainmhiiniuuCaighdeaanach.contains(cuardaitheCaighdeaanach))
                    {
                        mDataSource.add(iontraail);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/



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
