package org.toughradius.model;

import java.io.Serializable;

public class RadUserMeta extends RadUserMetaKey implements Serializable {
    private String value;

    private String metaDesc;

    private static final long serialVersionUID = 1L;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMetaDesc() {
        return metaDesc;
    }

    public void setMetaDesc(String metaDesc) {
        this.metaDesc = metaDesc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", value=").append(value);
        sb.append(", metaDesc=").append(metaDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}