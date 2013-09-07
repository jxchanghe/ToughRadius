package org.toughradius.model;

import java.util.ArrayList;
import java.util.List;

public class RadOptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RadOptionExample() {
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

        public Criteria andOptionNameIsNull() {
            addCriterion("OPTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOptionNameIsNotNull() {
            addCriterion("OPTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOptionNameEqualTo(String value) {
            addCriterion("OPTION_NAME =", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotEqualTo(String value) {
            addCriterion("OPTION_NAME <>", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameGreaterThan(String value) {
            addCriterion("OPTION_NAME >", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPTION_NAME >=", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLessThan(String value) {
            addCriterion("OPTION_NAME <", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLessThanOrEqualTo(String value) {
            addCriterion("OPTION_NAME <=", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLike(String value) {
            addCriterion("OPTION_NAME like", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotLike(String value) {
            addCriterion("OPTION_NAME not like", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameIn(List<String> values) {
            addCriterion("OPTION_NAME in", values, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotIn(List<String> values) {
            addCriterion("OPTION_NAME not in", values, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameBetween(String value1, String value2) {
            addCriterion("OPTION_NAME between", value1, value2, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotBetween(String value1, String value2) {
            addCriterion("OPTION_NAME not between", value1, value2, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionValueIsNull() {
            addCriterion("OPTION_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andOptionValueIsNotNull() {
            addCriterion("OPTION_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andOptionValueEqualTo(String value) {
            addCriterion("OPTION_VALUE =", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueNotEqualTo(String value) {
            addCriterion("OPTION_VALUE <>", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueGreaterThan(String value) {
            addCriterion("OPTION_VALUE >", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueGreaterThanOrEqualTo(String value) {
            addCriterion("OPTION_VALUE >=", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueLessThan(String value) {
            addCriterion("OPTION_VALUE <", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueLessThanOrEqualTo(String value) {
            addCriterion("OPTION_VALUE <=", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueLike(String value) {
            addCriterion("OPTION_VALUE like", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueNotLike(String value) {
            addCriterion("OPTION_VALUE not like", value, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueIn(List<String> values) {
            addCriterion("OPTION_VALUE in", values, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueNotIn(List<String> values) {
            addCriterion("OPTION_VALUE not in", values, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueBetween(String value1, String value2) {
            addCriterion("OPTION_VALUE between", value1, value2, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionValueNotBetween(String value1, String value2) {
            addCriterion("OPTION_VALUE not between", value1, value2, "optionValue");
            return (Criteria) this;
        }

        public Criteria andOptionDescIsNull() {
            addCriterion("OPTION_DESC is null");
            return (Criteria) this;
        }

        public Criteria andOptionDescIsNotNull() {
            addCriterion("OPTION_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andOptionDescEqualTo(String value) {
            addCriterion("OPTION_DESC =", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescNotEqualTo(String value) {
            addCriterion("OPTION_DESC <>", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescGreaterThan(String value) {
            addCriterion("OPTION_DESC >", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescGreaterThanOrEqualTo(String value) {
            addCriterion("OPTION_DESC >=", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescLessThan(String value) {
            addCriterion("OPTION_DESC <", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescLessThanOrEqualTo(String value) {
            addCriterion("OPTION_DESC <=", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescLike(String value) {
            addCriterion("OPTION_DESC like", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescNotLike(String value) {
            addCriterion("OPTION_DESC not like", value, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescIn(List<String> values) {
            addCriterion("OPTION_DESC in", values, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescNotIn(List<String> values) {
            addCriterion("OPTION_DESC not in", values, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescBetween(String value1, String value2) {
            addCriterion("OPTION_DESC between", value1, value2, "optionDesc");
            return (Criteria) this;
        }

        public Criteria andOptionDescNotBetween(String value1, String value2) {
            addCriterion("OPTION_DESC not between", value1, value2, "optionDesc");
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