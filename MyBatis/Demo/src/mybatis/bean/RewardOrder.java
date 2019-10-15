package mybatis.bean;

import java.util.Date;

/**
 * @Author: wangcf
 * @Date: 2019/4/8 22:17
 */
public class RewardOrder {
    private String rewardId;
    private String rewardName;
    private String rewardContent;
    private int rewardStage;
    private int createUser;
    private Date createTime;
    private int updateUser;
    private Date updateTime;

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardContent() {
        return rewardContent;
    }

    public void setRewardContent(String rewardContent) {
        this.rewardContent = rewardContent;
    }

    public int getRewardStage() {
        return rewardStage;
    }

    public void setRewardStage(int rewardStage) {
        this.rewardStage = rewardStage;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RewardOrder{" +
                "rewardId='" + rewardId + '\'' +
                ", rewardName='" + rewardName + '\'' +
                ", rewardContent='" + rewardContent + '\'' +
                ", rewardStage=" + rewardStage +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                '}';
    }
}
