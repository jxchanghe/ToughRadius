package org.toughradius.model;

import java.io.Serializable;

public class RadBlacklist implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_BLACKLIST.MACADDR
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private String macaddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_BLACKLIST.EXPIRE
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private Integer expire;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RAD_BLACKLIST
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_BLACKLIST.MACADDR
     *
     * @return the value of RAD_BLACKLIST.MACADDR
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public String getMacaddr() {
        return macaddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_BLACKLIST.MACADDR
     *
     * @param macaddr the value for RAD_BLACKLIST.MACADDR
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public void setMacaddr(String macaddr) {
        this.macaddr = macaddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_BLACKLIST.EXPIRE
     *
     * @return the value of RAD_BLACKLIST.EXPIRE
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public Integer getExpire() {
        return expire;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_BLACKLIST.EXPIRE
     *
     * @param expire the value for RAD_BLACKLIST.EXPIRE
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RAD_BLACKLIST
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", macaddr=").append(macaddr);
        sb.append(", expire=").append(expire);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}