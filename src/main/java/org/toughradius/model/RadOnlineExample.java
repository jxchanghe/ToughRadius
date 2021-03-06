package org.toughradius.model;

import java.util.ArrayList;
import java.util.List;

public class RadOnlineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RadOnlineExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andClientAddressIsNull() {
            addCriterion("CLIENT_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andClientAddressIsNotNull() {
            addCriterion("CLIENT_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andClientAddressEqualTo(String value) {
            addCriterion("CLIENT_ADDRESS =", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressNotEqualTo(String value) {
            addCriterion("CLIENT_ADDRESS <>", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressGreaterThan(String value) {
            addCriterion("CLIENT_ADDRESS >", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_ADDRESS >=", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressLessThan(String value) {
            addCriterion("CLIENT_ADDRESS <", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_ADDRESS <=", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressLike(String value) {
            addCriterion("CLIENT_ADDRESS like", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressNotLike(String value) {
            addCriterion("CLIENT_ADDRESS not like", value, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressIn(List<String> values) {
            addCriterion("CLIENT_ADDRESS in", values, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressNotIn(List<String> values) {
            addCriterion("CLIENT_ADDRESS not in", values, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressBetween(String value1, String value2) {
            addCriterion("CLIENT_ADDRESS between", value1, value2, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andClientAddressNotBetween(String value1, String value2) {
            addCriterion("CLIENT_ADDRESS not between", value1, value2, "clientAddress");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNull() {
            addCriterion("SESSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNotNull() {
            addCriterion("SESSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSessionIdEqualTo(String value) {
            addCriterion("SESSION_ID =", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotEqualTo(String value) {
            addCriterion("SESSION_ID <>", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThan(String value) {
            addCriterion("SESSION_ID >", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_ID >=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThan(String value) {
            addCriterion("SESSION_ID <", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThanOrEqualTo(String value) {
            addCriterion("SESSION_ID <=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLike(String value) {
            addCriterion("SESSION_ID like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotLike(String value) {
            addCriterion("SESSION_ID not like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdIn(List<String> values) {
            addCriterion("SESSION_ID in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotIn(List<String> values) {
            addCriterion("SESSION_ID not in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdBetween(String value1, String value2) {
            addCriterion("SESSION_ID between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotBetween(String value1, String value2) {
            addCriterion("SESSION_ID not between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNull() {
            addCriterion("IP_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNotNull() {
            addCriterion("IP_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andIpAddressEqualTo(String value) {
            addCriterion("IP_ADDRESS =", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotEqualTo(String value) {
            addCriterion("IP_ADDRESS <>", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThan(String value) {
            addCriterion("IP_ADDRESS >", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("IP_ADDRESS >=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThan(String value) {
            addCriterion("IP_ADDRESS <", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThanOrEqualTo(String value) {
            addCriterion("IP_ADDRESS <=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLike(String value) {
            addCriterion("IP_ADDRESS like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotLike(String value) {
            addCriterion("IP_ADDRESS not like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressIn(List<String> values) {
            addCriterion("IP_ADDRESS in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotIn(List<String> values) {
            addCriterion("IP_ADDRESS not in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressBetween(String value1, String value2) {
            addCriterion("IP_ADDRESS between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotBetween(String value1, String value2) {
            addCriterion("IP_ADDRESS not between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressIsNull() {
            addCriterion("MAC_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andMacAddressIsNotNull() {
            addCriterion("MAC_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andMacAddressEqualTo(String value) {
            addCriterion("MAC_ADDRESS =", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotEqualTo(String value) {
            addCriterion("MAC_ADDRESS <>", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThan(String value) {
            addCriterion("MAC_ADDRESS >", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThanOrEqualTo(String value) {
            addCriterion("MAC_ADDRESS >=", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThan(String value) {
            addCriterion("MAC_ADDRESS <", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThanOrEqualTo(String value) {
            addCriterion("MAC_ADDRESS <=", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLike(String value) {
            addCriterion("MAC_ADDRESS like", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotLike(String value) {
            addCriterion("MAC_ADDRESS not like", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressIn(List<String> values) {
            addCriterion("MAC_ADDRESS in", values, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotIn(List<String> values) {
            addCriterion("MAC_ADDRESS not in", values, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressBetween(String value1, String value2) {
            addCriterion("MAC_ADDRESS between", value1, value2, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotBetween(String value1, String value2) {
            addCriterion("MAC_ADDRESS not between", value1, value2, "macAddress");
            return (Criteria) this;
        }

        public Criteria andAcctStartIsNull() {
            addCriterion("ACCT_START is null");
            return (Criteria) this;
        }

        public Criteria andAcctStartIsNotNull() {
            addCriterion("ACCT_START is not null");
            return (Criteria) this;
        }

        public Criteria andAcctStartEqualTo(String value) {
            addCriterion("ACCT_START =", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartNotEqualTo(String value) {
            addCriterion("ACCT_START <>", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartGreaterThan(String value) {
            addCriterion("ACCT_START >", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_START >=", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartLessThan(String value) {
            addCriterion("ACCT_START <", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartLessThanOrEqualTo(String value) {
            addCriterion("ACCT_START <=", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartLike(String value) {
            addCriterion("ACCT_START like", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartNotLike(String value) {
            addCriterion("ACCT_START not like", value, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartIn(List<String> values) {
            addCriterion("ACCT_START in", values, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartNotIn(List<String> values) {
            addCriterion("ACCT_START not in", values, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartBetween(String value1, String value2) {
            addCriterion("ACCT_START between", value1, value2, "acctStart");
            return (Criteria) this;
        }

        public Criteria andAcctStartNotBetween(String value1, String value2) {
            addCriterion("ACCT_START not between", value1, value2, "acctStart");
            return (Criteria) this;
        }

        public Criteria andNasPortIsNull() {
            addCriterion("NAS_PORT is null");
            return (Criteria) this;
        }

        public Criteria andNasPortIsNotNull() {
            addCriterion("NAS_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andNasPortEqualTo(String value) {
            addCriterion("NAS_PORT =", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortNotEqualTo(String value) {
            addCriterion("NAS_PORT <>", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortGreaterThan(String value) {
            addCriterion("NAS_PORT >", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortGreaterThanOrEqualTo(String value) {
            addCriterion("NAS_PORT >=", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortLessThan(String value) {
            addCriterion("NAS_PORT <", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortLessThanOrEqualTo(String value) {
            addCriterion("NAS_PORT <=", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortLike(String value) {
            addCriterion("NAS_PORT like", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortNotLike(String value) {
            addCriterion("NAS_PORT not like", value, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortIn(List<String> values) {
            addCriterion("NAS_PORT in", values, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortNotIn(List<String> values) {
            addCriterion("NAS_PORT not in", values, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortBetween(String value1, String value2) {
            addCriterion("NAS_PORT between", value1, value2, "nasPort");
            return (Criteria) this;
        }

        public Criteria andNasPortNotBetween(String value1, String value2) {
            addCriterion("NAS_PORT not between", value1, value2, "nasPort");
            return (Criteria) this;
        }

        public Criteria andStartSourceIsNull() {
            addCriterion("START_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andStartSourceIsNotNull() {
            addCriterion("START_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andStartSourceEqualTo(Integer value) {
            addCriterion("START_SOURCE =", value, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceNotEqualTo(Integer value) {
            addCriterion("START_SOURCE <>", value, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceGreaterThan(Integer value) {
            addCriterion("START_SOURCE >", value, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("START_SOURCE >=", value, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceLessThan(Integer value) {
            addCriterion("START_SOURCE <", value, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceLessThanOrEqualTo(Integer value) {
            addCriterion("START_SOURCE <=", value, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceIn(List<Integer> values) {
            addCriterion("START_SOURCE in", values, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceNotIn(List<Integer> values) {
            addCriterion("START_SOURCE not in", values, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceBetween(Integer value1, Integer value2) {
            addCriterion("START_SOURCE between", value1, value2, "startSource");
            return (Criteria) this;
        }

        public Criteria andStartSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("START_SOURCE not between", value1, value2, "startSource");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}