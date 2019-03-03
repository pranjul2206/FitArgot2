package in.ac.ksit.android.fitargot.Network.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.ac.ksit.android.fitargot.Network.Model.EventResult;

public class PastEventModel {

    @SerializedName("result")
    @Expose
    private List<EventResult> result = null;

    public List<EventResult> getResult() {
        return result;
    }

    public void setResult(List<EventResult> result) {
        this.result = result;
    }

}

