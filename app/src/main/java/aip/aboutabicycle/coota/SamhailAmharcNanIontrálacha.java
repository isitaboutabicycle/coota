package aip.aboutabicycle.coota;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

// ná choinnigh riamh tagairt do Gníomhaíocht nó Amharc le tagairt dó anseo mar maróidh AndroidViewModel níos faide

public class SamhailAmharcNanIontrálacha extends AndroidViewModel {

    private Stór stór;

    public SamhailAmharcNanIontrálacha(@NonNull Application aip){
        super(aip);

        stór = new Stór(aip);
    }


    /********** Comhéadan feidhmchlárúcháin **********/

    public LiveData<List<Iontráil>> faighIontrálacha() {
        return stór.faighIontrálacha();
    }

    public void uasdátaighIontrálacha(String ionchur) throws InterruptedException {
        stór.uasdátaighIontrálacha(ionchur);
    }
}
