
package pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TimeStats {

    @SerializedName("human_time_estimate")
    private Object humanTimeEstimate;
    @SerializedName("human_total_time_spent")
    private Object humanTotalTimeSpent;
    @SerializedName("time_estimate")
    private Long timeEstimate;
    @SerializedName("total_time_spent")
    private Long totalTimeSpent;

    public Object getHumanTimeEstimate() {
        return humanTimeEstimate;
    }

    public void setHumanTimeEstimate(Object humanTimeEstimate) {
        this.humanTimeEstimate = humanTimeEstimate;
    }

    public Object getHumanTotalTimeSpent() {
        return humanTotalTimeSpent;
    }

    public void setHumanTotalTimeSpent(Object humanTotalTimeSpent) {
        this.humanTotalTimeSpent = humanTotalTimeSpent;
    }

    public Long getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Long timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public Long getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Long totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

}
