package me.duanyong.blog.bean;

import java.io.Serializable;
import java.util.Date;

public class Category extends AbstractBean implements Serializable {
    private Integer id;

    private Integer topId;

    private Integer parentId;

    private String name;

    private String alias;

    private String css;

    private String css1;

    private String css2;

    private Short zone;

    private Short pos;

    private Date ctime;

    private Byte status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css == null ? null : css.trim();
    }

    public String getCss1() {
        return css1;
    }

    public void setCss1(String css1) {
        this.css1 = css1 == null ? null : css1.trim();
    }

    public String getCss2() {
        return css2;
    }

    public void setCss2(String css2) {
        this.css2 = css2 == null ? null : css2.trim();
    }

    public Short getZone() {
        return zone;
    }

    public void setZone(Short zone) {
        this.zone = zone;
    }

    public Short getPos() {
        return pos;
    }

    public void setPos(Short pos) {
        this.pos = pos;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
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
        Category other = (Category) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopId() == null ? other.getTopId() == null : this.getTopId().equals(other.getTopId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAlias() == null ? other.getAlias() == null : this.getAlias().equals(other.getAlias()))
            && (this.getCss() == null ? other.getCss() == null : this.getCss().equals(other.getCss()))
            && (this.getCss1() == null ? other.getCss1() == null : this.getCss1().equals(other.getCss1()))
            && (this.getCss2() == null ? other.getCss2() == null : this.getCss2().equals(other.getCss2()))
            && (this.getZone() == null ? other.getZone() == null : this.getZone().equals(other.getZone()))
            && (this.getPos() == null ? other.getPos() == null : this.getPos().equals(other.getPos()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopId() == null) ? 0 : getTopId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
        result = prime * result + ((getCss() == null) ? 0 : getCss().hashCode());
        result = prime * result + ((getCss1() == null) ? 0 : getCss1().hashCode());
        result = prime * result + ((getCss2() == null) ? 0 : getCss2().hashCode());
        result = prime * result + ((getZone() == null) ? 0 : getZone().hashCode());
        result = prime * result + ((getPos() == null) ? 0 : getPos().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
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
        sb.append(", topId=").append(topId);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", alias=").append(alias);
        sb.append(", css=").append(css);
        sb.append(", css1=").append(css1);
        sb.append(", css2=").append(css2);
        sb.append(", zone=").append(zone);
        sb.append(", pos=").append(pos);
        sb.append(", ctime=").append(ctime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}