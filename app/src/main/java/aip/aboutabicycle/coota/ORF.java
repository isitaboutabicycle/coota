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
    Cursor c = null;

    // aonarán
    private ORF(Context comhthéacs){
        this.osclóir = new OsclóirBhunacharSonraí(comhthéacs);
    }

    public static ORF faighSampla(Context comhthéacs){
        if(sampla == null){
            sampla = new ORF(comhthéacs);
        }
        return sampla;
    }

    // TODO: cuir ar ais Boole
    public void oscail(){
        this.bs = osclóir.getWritableDatabase();
    }

    //TODO: mar an gcéanna
    public void dún() {
        if(bs != null){
            this.bs.close();
        }
    }

    public ArrayList<Iontráil> faighIontrálacha(String ionchur){
        ArrayList<Iontráil> aschur = new ArrayList<Iontráil>();
        c = bs.rawQuery(
                "SELECT * FROM iontraail WHERE ceann LIKE '"+ionchur+"' ORDER BY ceann DESC LIMIT 100",
                     new String[]{}
        );

        int innéacAnAinm = c.getColumnIndex("ceann");
        int innéacsAnSainmhínithe = c.getColumnIndex("sainmhiiniuu");
        while(c.moveToNext()){
            Iontráil iontráil = new Iontráil(
                    c.getString(innéacAnAinm), c.getString(innéacsAnSainmhínithe)
            );
            aschur.add(iontráil);
        }

        return aschur;
    }
}