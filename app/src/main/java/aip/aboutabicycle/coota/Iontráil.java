package aip.aboutabicycle.coota;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
//import androidx.room.Fts4;

//@Fts4 //full text search https://www.sqlite.org/fts3.html
@Entity(tableName = "iontraail")
public class Iontráil {

    @PrimaryKey//(autoGenerate = true)
    private int id;
    @ColumnInfo(name="ceann")
    private String ceannfhocal;
    @ColumnInfo(name="sainmhiiniuu")
    private String sainmhíniú;


    public int getId() {
        return id;
    }

    public String getCeannfhocal() {
        return ceannfhocal;
    }

    public String getSainmhíniú() {
        return sainmhíniú;
    }

    // úsáidfidh Room é seo
    public void setId(int id) {
        this.id = id;
    }

    public void setCeannfhocal(String ceannfhocal) {
        this.ceannfhocal = ceannfhocal;
    }

    public void setSainmhíniú(String sainmhíniú) {
        this.sainmhíniú = sainmhíniú;
    }


    // cruthaítear an ID go huathoibríoch
    public Iontráil(String ceannfhocal, String sainmhíniú) {
        this.ceannfhocal = ceannfhocal;
        this.sainmhíniú = sainmhíniú;
    }
}