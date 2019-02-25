package aip.aboutabicycle.coota;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

// Obiacht Rochtain ar Fhaisnéis
public class ORF {
    private SQLiteOpenHelper osclóir;
    private SQLiteDatabase bs;
    private static ORF sampla;
    private Cursor cúrsóir = null;

    // aonarán
    private ORF(Context comhthéacs){
        this.osclóir = new OsclóirBhunacharSonraí(comhthéacs);
    }

    public static ORF faighSampla(Context comhthéacs){
        if(sampla == null ){
            sampla = new ORF(comhthéacs);
        }
        return sampla;
    }

    public void oscail(){
        this.bs = osclóir.getWritableDatabase();
    }

    public boolean oscailte(){
        return this.bs.isOpen();
    }

    public void dún() {
        if(bs != null){
            this.bs.close();
        }
    }

    public ArrayList<Iontráil> faighIontrálacha(String ionchur){
        ArrayList<Iontráil> aschur = new ArrayList<Iontráil>();
        //TODO: seachan ionsaithe XSS!!! déan feabhsú ollmhór air seo
        if(ionchur.contains("<")
                || ionchur.contains(">")
                || ionchur.contains("DROP TABLE")){
            return aschur;
        }

        aschur = déanCuardach(
                "SELECT * FROM iontraail WHERE ceann LIKE '"+ionchur+"' ORDER BY ceann DESC LIMIT 100"
        );
        if(!aschur.isEmpty()){
            aschur.add(new Iontráil("\n\n","\n\n"));
        }

        ArrayList<Iontráil> cuardachEile = déanCuardach("SELECT * FROM iontraail WHERE ceann LIKE '"+ionchur+"%' ORDER BY ceann DESC LIMIT 100");
        for (Iontráil iontráil : cuardachEile) {
            if (!aschur.contains(iontráil)){ //TODO: níl sé seo ag obair
                aschur.add(iontráil);
            }
        }

        if(ionchur.length() > 1){
            cuardachEile = déanCuardach(
                    "SELECT * FROM iontraail WHERE sainmhiiniuu LIKE '%"+ionchur+"%' ORDER BY ceann DESC LIMIT 50"
            );
            if (!cuardachEile.isEmpty()){
                aschur.add(new Iontráil("\n\n","\n\n")); //TODO: déan i mbealach níos fearr

                for (Iontráil iontráil : cuardachEile) {
                    if (!aschur.contains(iontráil)){
                        aschur.add(iontráil);
                    }
                }
            }
        }

        return aschur;
    }

    public synchronized ArrayList<Iontráil> iontráilRandamach(Context comhthéacs){
        ArrayList<Iontráil> aschur = new ArrayList<Iontráil>();

        long líon = DatabaseUtils.queryNumEntries(bs, "iontraail");
        int uimhirRandamach = 0;
        Random randamach = new Random();

        //TODO: úsáid bealach níos fearr
        if(líon > Integer.MAX_VALUE){
            líon = Integer.MAX_VALUE;
            Toast.makeText(comhthéacs, R.string.fadhb_randamach, Toast.LENGTH_LONG);
        }
        uimhirRandamach = randamach.nextInt((int)líon);

        String teaghránCuardach = "SELECT * FROM iontraail WHERE id = '" + uimhirRandamach + "'";

        aschur = déanCuardach(teaghránCuardach);

        return aschur;
    }

    private synchronized ArrayList<Iontráil> déanCuardach(String teaghránChuardaigh){
        ArrayList<Iontráil> aschur = new ArrayList<Iontráil>();
        cúrsóir = bs.rawQuery(teaghránChuardaigh, new String[]{});

        int innéacAnAinm = cúrsóir.getColumnIndex("ceann");
        int innéacsAnSainmhínithe = cúrsóir.getColumnIndex("sainmhiiniuu");
        while(cúrsóir.moveToNext()){
            Iontráil iontráil = new Iontráil(
                    cúrsóir.getString(innéacAnAinm), cúrsóir.getString(innéacsAnSainmhínithe)
            );
            aschur.add(iontráil);
        }

        return aschur;
    }
}
