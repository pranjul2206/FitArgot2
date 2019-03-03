package in.ac.ksit.android.fitargot.Network.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpModel {

    @SerializedName("registration_status")
    @Expose
    private String registrationStatus;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
    }

}
