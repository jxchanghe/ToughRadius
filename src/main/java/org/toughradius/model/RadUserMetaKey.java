package org.toughradius.model;

import java.io.Serializable;

public class RadUserMetaKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_USER_META.USER_NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_USER_META.NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RAD_USER_META
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_USER_META.USER_NAME
     *
     * @return the value of RAD_USER_META.USER_NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_USER_META.USER_NAME
     *
     * @param userName the value for RAD_USER_META.USER_NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_USER_META.NAME
     *
     * @return the value of RAD_USER_META.NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_USER_META.NAME
     *
     * @param name the value for RAD_USER_META.NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RAD_USER_META
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userName=").append(userName);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}