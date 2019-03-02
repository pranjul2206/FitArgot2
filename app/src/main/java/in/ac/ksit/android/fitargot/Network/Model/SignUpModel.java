package in.ac.ksit.android.fitargot.Network.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("registration_status")
    @Expose
    private String registrationStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

}
