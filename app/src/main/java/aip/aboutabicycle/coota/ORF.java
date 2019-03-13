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
    private String cuardachDeireanach = "";
    private String cuardachNíosSine = "";
    private int idDeireanach;
    private int idNíosSine;

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
        int tús = 0;
        String príomhtheaghránCuardachAnChinn;
        String teaghránEileCuardachAnChinn;
        String teaghránCuardachAntSainmhínithe;

        //TODO: seachan ionsaithe XSS!!! déan feabhsú ollmhór air seo
        if(ionchur.contains("<")
                || ionchur.contains(">")
                || ionchur.contains("DROP TABLE")){
            return aschur;
        }

        if(ionchur.startsWith(cuardachNíosSine)){
            tús = idNíosSine;

            if(ionchur.startsWith(cuardachDeireanach)){
                tús = idDeireanach;
            }
        }

        príomhtheaghránCuardachAnChinn = "SELECT * FROM iontraail WHERE id >= " + tús +
                " AND ceann LIKE '"+ionchur+"' ORDER BY ceann DESC LIMIT 100"; //todo: cén fáth nach bhfeictear "amadán" ach "amad" a chuardach?
        teaghránEileCuardachAnChinn = "SELECT * FROM iontraail WHERE id >= " + tús +
                " AND ceann LIKE '%"+ionchur+"%' ORDER BY ceann DESC LIMIT 100";
        teaghránCuardachAntSainmhínithe = "SELECT * FROM iontraail WHERE id >= " + tús +
                " AND sainmhiiniuu LIKE '%"+ionchur+"%' ORDER BY ceann DESC LIMIT 50";

        // céad chuardach
        aschur = déanCuardach(príomhtheaghránCuardachAnChinn);
        if(!aschur.isEmpty()){
            aschur.add(new Iontráil("\n\n","\n\n"));
        }

        // dara cuardach
        ArrayList<Iontráil> cuardachEile = déanCuardach(teaghránEileCuardachAnChinn);
        boolean curthaLeis = false;
        for (Iontráil iontráilNua : cuardachEile) { //TODO: seo bealach fadálach
            boolean ann = false;

            for (Iontráil seanIontráil : aschur){
                if (seanIontráil.getId() == iontráilNua.getId() ){
                    ann = true;
                    break;
                }
            }

            if (!ann){
                aschur.add(iontráilNua);
                curthaLeis = true;
            }
        }
        if(curthaLeis){
            aschur.add(new Iontráil("\n\n","\n\n")); //TODO: déan i mbealach níos fearr
        }

        // treas cuardach
        if(ionchur.length() > 1){
            cuardachEile = déanCuardach(teaghránCuardachAntSainmhínithe);
            if (!cuardachEile.isEmpty()){
                for (Iontráil iontráil : cuardachEile) {
                    if (!aschur.contains(iontráil)){
                        aschur.add(iontráil);
                    }
                }
            }
        }

        //todo: má tá sé níos giorra
        if(!aschur.isEmpty() && ionchur != cuardachDeireanach){

            while( aschur.get(aschur.size()-1).getCeannfhocal() == "\n\n"
                   && aschur.get(aschur.size()-1).getSainmhíniú() == "\n\n" ){
                aschur.remove(aschur.size()-1);
            }

            cuardachNíosSine = cuardachDeireanach;
            idNíosSine = idDeireanach;
            cuardachDeireanach = ionchur;
            idDeireanach = aschur.get(0).getId();
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
