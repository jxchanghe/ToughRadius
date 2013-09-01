package org.toughradius.model;

import java.io.Serializable;

public class RadGroupMetaKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_GROUP_META.GROUP_NAME
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    private String groupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_GROUP_META.NAME
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RAD_GROUP_META
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_GROUP_META.GROUP_NAME
     *
     * @return the value of RAD_GROUP_META.GROUP_NAME
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_GROUP_META.GROUP_NAME
     *
     * @param groupName the value for RAD_GROUP_META.GROUP_NAME
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_GROUP_META.NAME
     *
     * @return the value of RAD_GROUP_META.NAME
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_GROUP_META.NAME
     *
     * @param name the value for RAD_GROUP_META.NAME
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RAD_GROUP_META
     *
     * @mbggenerated Sun Sep 01 20:56:17 CST 2013
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupName=").append(groupName);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}