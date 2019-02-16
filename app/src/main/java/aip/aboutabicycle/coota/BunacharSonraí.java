package aip.aboutabicycle.coota;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.widget.Toast;

import com.huma.room_for_asset.RoomAsset; // https://github.com/humazed/RoomAsset

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {Iontráil.class}, version = 2) // version = 1 sa ghnáth-room
public abstract class BunacharSonraí extends RoomDatabase {

    private static volatile BunacharSonraí SAMPLA = null;

    public abstract DaoNanIontrálacha daoNanIontrálacha(); // déanann Room leagan inúsáide de seo

    public static BunacharSonraí faighSampla(final Context comhthéacs) {

        // comhaid an fhoclóra féin á aimsiú ar mhaithe leis an oibiacht a chruthú
        //ContextWrapper mFhilteog = new ContextWrapper(comhthéacs);
        String láthair = bogBS(comhthéacs);

        if (SAMPLA == null){
            synchronized (BunacharSonraí.class) {
                if (SAMPLA == null){
                    SAMPLA = RoomAsset.databaseBuilder(comhthéacs.getApplicationContext(),          //seachas Room.datab...
                            BunacharSonraí.class, GFoclooir.AINM_AN_FHOCLÓRA)//láthair)
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
//                            .fallbackToDestructiveMigration() // TODO: faigh amach an gá é seo ná rud cosúil leis
                            .build();
                }
            }
        }
        return SAMPLA;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(SupportSQLiteDatabase bs){
            // tada le déanamh anseo mar ní athrófar an bunachar sonraí
        }
    };

    public static String bogBS (Context comhthéacs){
        final String ainm = GFoclooir.AINM_AN_FHOCLÓRA;
        final File conairCheart = comhthéacs.getDatabasePath(ainm);

        boolean exists = conairCheart.exists(); //TODO: bain iad seo
        boolean readable = conairCheart.canRead();
        String[] conairMícheart;
        try {
            conairMícheart = comhthéacs.getAssets().list(ainm);
            Thread.sleep(1);
        } catch (Exception e){}

        //if(!conairCheart.exists()) {
            //copyDatabaseFile(conairCheart.getAbsolutePath());
            conairCheart.getParentFile().mkdirs();

            try{
                // an comhad á bogadh
                final InputStream sruthIsteach = comhthéacs.getAssets().open(ainm);
                final OutputStream sruthAmach = new FileOutputStream("databases/"+conairCheart);

                byte[] maolán = new byte[8192];
                int fad;

                while((fad = sruthIsteach.read(maolán, 0, 8192)) > 0) {
                    sruthAmach.write(maolán, 0, fad);
                }

                sruthAmach.flush();
                sruthAmach.close();
                sruthIsteach.close();

            } catch (IOException e){
                String teachtaireacht = e.getMessage()+" "+R.string.fadhb_snáth;
                Toast.makeText(comhthéacs, teachtaireacht, Toast.LENGTH_LONG).show();
            }
        //}

        return conairCheart.getAbsolutePath();
    }
}
