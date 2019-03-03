package in.ac.ksit.android.fitargot.Network.Model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodModel {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("imgid")
    @Expose
    private String imgid;
    @SerializedName("qid")
    @Expose
    private Integer qid;
    @SerializedName("status_msg")
    @Expose
    private String statusMsg;
    @SerializedName("food_results")
    @Expose
    private List<List<String>> foodResults = null;
    @SerializedName("time_cost")
    @Expose
    private Double timeCost;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public List<List<String>> getFoodResults() {
        return foodResults;
    }

    public void setFoodResults(List<List<String>> foodResults) {
        this.foodResults = foodResults;
    }

    public Double getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Double timeCost) {
        this.timeCost = timeCost;
    }

}