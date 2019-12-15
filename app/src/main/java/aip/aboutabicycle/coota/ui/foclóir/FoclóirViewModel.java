package aip.aboutabicycle.coota.ui.foclóir;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class FoclóirViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FoclóirViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is feed fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}