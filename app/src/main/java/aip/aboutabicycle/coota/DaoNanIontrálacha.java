package aip.aboutabicycle.coota;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import java.util.List;

// caithfidh an DAO bheith mar Chomhéadan seachas aicme mar cruthóidh Room coirp na bhfeidhmeanna dúinn
// is fearr DAO nua a chruthú do gach Entity
// if féidir do chuid feidhmeanna féin a dhéanamh leis an marcáil @Query("")

@Dao
public interface DaoNanIontrálacha {

    /*@Query("SELECT * FROM iontraail ORDER BY ceann DESC")
    List<Iontráil> faighIontrálacha();*/

    // % = rud ar bith nó tada
    // || = concatenation
    @Query("SELECT * FROM iontraail WHERE ceann = 'a'")
//    @Query("SELECT * FROM iontraail WHERE ceann LIKE '%' || :ionchur || '%' ORDER BY ceann DESC LIMIT 100")
//    @Query("SELECT * FROM iontraail WHERE ceann LIKE :ionchur ORDER BY ceann DESC LIMIT 100")
    LiveData<List<Iontráil>> faighIontrálacha();
}
