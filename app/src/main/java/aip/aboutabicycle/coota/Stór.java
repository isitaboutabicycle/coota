package aip.aboutabicycle.coota;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

// Níl an phíosa seo riachtanach ach tá sé cabhrach le hord a chur ar rudaí

public class Stór {
    private DaoNanIontrálacha dao;
    static private LiveData<List<Iontráil>> iontrálacha;

    public Stór(Application aip) {
        BunacharSonraí bs = BunacharSonraí.faighSampla(aip);
        dao = bs.daoNanIontrálacha();
    }


    /********** Comhéadan feidhmchlárúcháin **********/

    /*public List<Iontráil> faighIontrálacha(){
        if(gachIontráil==null || gachIontráil.isEmpty()){
            gachIontráil = dao.faighIontrálacha();
        }
        return gachIontráil;
    }*/
    public LiveData<List<Iontráil>> faighIontrálacha(){
        return iontrálacha;
    }

    public void uasdátaighIontrálacha(String ionchur) throws InterruptedException {
        TascAisioncronachFaighIontrálacha tasc = new TascAisioncronachFaighIontrálacha(this ,dao);
        tasc.execute(ionchur);

        /*while(tasc.getStatus() != AsyncTask.Status.FINISHED){
            Thread.sleep(50); // TODO: aimsigh slí níos fearr ná seo
        }

        return this.iontrálacha;*/
    }

    private void taiscighIontrálacha(LiveData<List<Iontráil>> liosta){
        this.iontrálacha = liosta;
    }


    /********** Tascanna **********/
                                                                        // <ionchur, nuashonraithe, aschur>
    private static class TascAisioncronachFaighIontrálacha extends AsyncTask<String, Void, Void> {
        private DaoNanIontrálacha dao;
        private Stór stór;

        private TascAisioncronachFaighIontrálacha(Stór moStór, DaoNanIontrálacha moDhao){
            this.stór = moStór;
            this.dao = moDhao;
        }

        @Override
        protected Void doInBackground(String... teaghráin) {
            LiveData<List<Iontráil>> aimsithe = dao.faighIontrálacha(/*teaghráin[0]*/);
            stór.taiscighIontrálacha(aimsithe);

            return null;
        }

        /*@Override // TODO: an gá leis seo ar chur ar bith?
        protected void onPostExecute(List<Iontráil> liosta){

            super.onPostExecute(liosta);
            //príomhliosta = liosta;
        }*/
    }
}
