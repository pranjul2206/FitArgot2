package in.ac.ksit.android.fitargot.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventResult {

    @SerializedName("gname")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("etime")
    @Expose
    private String etime;
    @SerializedName("gid")
    @Expose
    private Integer gid;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("location_lat")
    @Expose
    private String locationLat;
    @SerializedName("location_long")
    @Expose
    private String locationLong;
    @SerializedName("participants")
    @Expose
    private Integer participants;
    @SerializedName("pdate")
    @Expose
    private String pdate;
    @SerializedName("stime")
    @Expose
    private String stime;
    @SerializedName("uid")
    @Expose
    private String uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(String locationLong) {
        this.locationLong = locationLong;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}