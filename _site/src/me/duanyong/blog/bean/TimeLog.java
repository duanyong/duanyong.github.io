package me.duanyong.blog.bean;

import java.io.Serializable;
import java.util.Date;

public class TimeLog extends AbstractBean implements Serializable {
    private Integer id;

    private Integer timeDeviceId;

    private Integer timeCardId;

    private Integer userId;

    private Date date;

    private Date time;

    private String desc;

    private Byte status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeDeviceId() {
        return timeDeviceId;
    }

    public void setTimeDeviceId(Integer timeDeviceId) {
        this.timeDeviceId = timeDeviceId;
    }

    public Integer getTimeCardId() {
        return timeCardId;
    }

    public void setTimeCardId(Integer timeCardId) {
        this.timeCardId = timeCardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TimeLog other = (TimeLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTimeDeviceId() == null ? other.getTimeDeviceId() == null : this.getTimeDeviceId().equals(other.getTimeDeviceId()))
            && (this.getTimeCardId() == null ? other.getTimeCardId() == null : this.getTimeCardId().equals(other.getTimeCardId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTimeDeviceId() == null) ? 0 : getTimeDeviceId().hashCode());
        result = prime * result + ((getTimeCardId() == null) ? 0 : getTimeCardId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", timeDeviceId=").append(timeDeviceId);
        sb.append(", timeCardId=").append(timeCardId);
        sb.append(", userId=").append(userId);
        sb.append(", date=").append(date);
        sb.append(", time=").append(time);
        sb.append(", desc=").append(desc);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}