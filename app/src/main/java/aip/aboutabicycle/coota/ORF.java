package aip.aboutabicycle.coota;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
        cúrsóir = bs.rawQuery(
                "SELECT * FROM iontraail WHERE ceann LIKE '"+ionchur+"%' ORDER BY ceann DESC LIMIT 100",
                     new String[]{}
        );

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
