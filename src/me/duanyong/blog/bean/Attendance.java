package me.duanyong.blog.bean;

import java.io.Serializable;
import java.util.Date;

public class Attendance extends AbstractBean implements Serializable {
    private Integer id;

    private Integer typeId;

    private Integer userId;

    private Date date;

    private Date firstClock;

    private Date firstReclock;

    private Date lastClock;

    private Date lastReclock;

    private Date lateTime;

    private Date overTime;

    private Integer workMins;

    private Integer laterinHours;

    private Integer earlyoutHours;

    private Boolean locked;

    private Date time;

    private String desc;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Date getFirstClock() {
        return firstClock;
    }

    public void setFirstClock(Date firstClock) {
        this.firstClock = firstClock;
    }

    public Date getFirstReclock() {
        return firstReclock;
    }

    public void setFirstReclock(Date firstReclock) {
        this.firstReclock = firstReclock;
    }

    public Date getLastClock() {
        return lastClock;
    }

    public void setLastClock(Date lastClock) {
        this.lastClock = lastClock;
    }

    public Date getLastReclock() {
        return lastReclock;
    }

    public void setLastReclock(Date lastReclock) {
        this.lastReclock = lastReclock;
    }

    public Date getLateTime() {
        return lateTime;
    }

    public void setLateTime(Date lateTime) {
        this.lateTime = lateTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Integer getWorkMins() {
        return workMins;
    }

    public void setWorkMins(Integer workMins) {
        this.workMins = workMins;
    }

    public Integer getLaterinHours() {
        return laterinHours;
    }

    public void setLaterinHours(Integer laterinHours) {
        this.laterinHours = laterinHours;
    }

    public Integer getEarlyoutHours() {
        return earlyoutHours;
    }

    public void setEarlyoutHours(Integer earlyoutHours) {
        this.earlyoutHours = earlyoutHours;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
        Attendance other = (Attendance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getFirstClock() == null ? other.getFirstClock() == null : this.getFirstClock().equals(other.getFirstClock()))
            && (this.getFirstReclock() == null ? other.getFirstReclock() == null : this.getFirstReclock().equals(other.getFirstReclock()))
            && (this.getLastClock() == null ? other.getLastClock() == null : this.getLastClock().equals(other.getLastClock()))
            && (this.getLastReclock() == null ? other.getLastReclock() == null : this.getLastReclock().equals(other.getLastReclock()))
            && (this.getLateTime() == null ? other.getLateTime() == null : this.getLateTime().equals(other.getLateTime()))
            && (this.getOverTime() == null ? other.getOverTime() == null : this.getOverTime().equals(other.getOverTime()))
            && (this.getWorkMins() == null ? other.getWorkMins() == null : this.getWorkMins().equals(other.getWorkMins()))
            && (this.getLaterinHours() == null ? other.getLaterinHours() == null : this.getLaterinHours().equals(other.getLaterinHours()))
            && (this.getEarlyoutHours() == null ? other.getEarlyoutHours() == null : this.getEarlyoutHours().equals(other.getEarlyoutHours()))
            && (this.getLocked() == null ? other.getLocked() == null : this.getLocked().equals(other.getLocked()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getFirstClock() == null) ? 0 : getFirstClock().hashCode());
        result = prime * result + ((getFirstReclock() == null) ? 0 : getFirstReclock().hashCode());
        result = prime * result + ((getLastClock() == null) ? 0 : getLastClock().hashCode());
        result = prime * result + ((getLastReclock() == null) ? 0 : getLastReclock().hashCode());
        result = prime * result + ((getLateTime() == null) ? 0 : getLateTime().hashCode());
        result = prime * result + ((getOverTime() == null) ? 0 : getOverTime().hashCode());
        result = prime * result + ((getWorkMins() == null) ? 0 : getWorkMins().hashCode());
        result = prime * result + ((getLaterinHours() == null) ? 0 : getLaterinHours().hashCode());
        result = prime * result + ((getEarlyoutHours() == null) ? 0 : getEarlyoutHours().hashCode());
        result = prime * result + ((getLocked() == null) ? 0 : getLocked().hashCode());
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
        sb.append(", typeId=").append(typeId);
        sb.append(", userId=").append(userId);
        sb.append(", date=").append(date);
        sb.append(", firstClock=").append(firstClock);
        sb.append(", firstReclock=").append(firstReclock);
        sb.append(", lastClock=").append(lastClock);
        sb.append(", lastReclock=").append(lastReclock);
        sb.append(", lateTime=").append(lateTime);
        sb.append(", overTime=").append(overTime);
        sb.append(", workMins=").append(workMins);
        sb.append(", laterinHours=").append(laterinHours);
        sb.append(", earlyoutHours=").append(earlyoutHours);
        sb.append(", locked=").append(locked);
        sb.append(", time=").append(time);
        sb.append(", desc=").append(desc);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}