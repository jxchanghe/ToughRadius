package org.toughradius.model;

import java.io.Serializable;

public class RadAdmin implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_ADMIN.LOGIN_NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RAD_ADMIN.PASSWORD
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RAD_ADMIN
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_ADMIN.LOGIN_NAME
     *
     * @return the value of RAD_ADMIN.LOGIN_NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_ADMIN.LOGIN_NAME
     *
     * @param loginName the value for RAD_ADMIN.LOGIN_NAME
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RAD_ADMIN.PASSWORD
     *
     * @return the value of RAD_ADMIN.PASSWORD
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RAD_ADMIN.PASSWORD
     *
     * @param password the value for RAD_ADMIN.PASSWORD
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RAD_ADMIN
     *
     * @mbggenerated Sun Sep 01 00:11:50 CST 2013
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}