package org.network.opensky.main.ui.saved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class SavedViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    // Text date.
    private final MutableLiveData<Date> date = new MutableLiveData<>();

    public SavedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is saved fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<Date> getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date.setValue(date);
    }
}