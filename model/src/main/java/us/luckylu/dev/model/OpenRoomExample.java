package us.luckylu.dev.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OpenRoomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OpenRoomExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNull() {
            addCriterion("create_at is null");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNotNull() {
            addCriterion("create_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAtEqualTo(LocalDateTime value) {
            addCriterion("create_at =", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotEqualTo(LocalDateTime value) {
            addCriterion("create_at <>", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThan(LocalDateTime value) {
            addCriterion("create_at >", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_at >=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThan(LocalDateTime value) {
            addCriterion("create_at <", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_at <=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtIn(List<LocalDateTime> values) {
            addCriterion("create_at in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotIn(List<LocalDateTime> values) {
            addCriterion("create_at not in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_at between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_at not between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(Integer value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(Integer value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(Integer value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(Integer value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(Integer value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<Integer> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<Integer> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(Integer value1, Integer value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(Integer value1, Integer value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeIsNull() {
            addCriterion("expe_arrivetime is null");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeIsNotNull() {
            addCriterion("expe_arrivetime is not null");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeEqualTo(LocalDateTime value) {
            addCriterion("expe_arrivetime =", value, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeNotEqualTo(LocalDateTime value) {
            addCriterion("expe_arrivetime <>", value, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeGreaterThan(LocalDateTime value) {
            addCriterion("expe_arrivetime >", value, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("expe_arrivetime >=", value, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeLessThan(LocalDateTime value) {
            addCriterion("expe_arrivetime <", value, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("expe_arrivetime <=", value, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeIn(List<LocalDateTime> values) {
            addCriterion("expe_arrivetime in", values, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeNotIn(List<LocalDateTime> values) {
            addCriterion("expe_arrivetime not in", values, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("expe_arrivetime between", value1, value2, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeArrivetimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("expe_arrivetime not between", value1, value2, "expeArrivetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeIsNull() {
            addCriterion("expe_leavetime is null");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeIsNotNull() {
            addCriterion("expe_leavetime is not null");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeEqualTo(LocalDateTime value) {
            addCriterion("expe_leavetime =", value, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeNotEqualTo(LocalDateTime value) {
            addCriterion("expe_leavetime <>", value, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeGreaterThan(LocalDateTime value) {
            addCriterion("expe_leavetime >", value, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("expe_leavetime >=", value, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeLessThan(LocalDateTime value) {
            addCriterion("expe_leavetime <", value, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("expe_leavetime <=", value, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeIn(List<LocalDateTime> values) {
            addCriterion("expe_leavetime in", values, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeNotIn(List<LocalDateTime> values) {
            addCriterion("expe_leavetime not in", values, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("expe_leavetime between", value1, value2, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andExpeLeavetimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("expe_leavetime not between", value1, value2, "expeLeavetime");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(Double value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(Double value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(Double value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(Double value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(Double value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(Double value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<Double> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<Double> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(Double value1, Double value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(Double value1, Double value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andExtendDaysIsNull() {
            addCriterion("extend_days is null");
            return (Criteria) this;
        }

        public Criteria andExtendDaysIsNotNull() {
            addCriterion("extend_days is not null");
            return (Criteria) this;
        }

        public Criteria andExtendDaysEqualTo(Integer value) {
            addCriterion("extend_days =", value, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysNotEqualTo(Integer value) {
            addCriterion("extend_days <>", value, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysGreaterThan(Integer value) {
            addCriterion("extend_days >", value, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("extend_days >=", value, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysLessThan(Integer value) {
            addCriterion("extend_days <", value, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysLessThanOrEqualTo(Integer value) {
            addCriterion("extend_days <=", value, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysIn(List<Integer> values) {
            addCriterion("extend_days in", values, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysNotIn(List<Integer> values) {
            addCriterion("extend_days not in", values, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysBetween(Integer value1, Integer value2) {
            addCriterion("extend_days between", value1, value2, "extendDays");
            return (Criteria) this;
        }

        public Criteria andExtendDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("extend_days not between", value1, value2, "extendDays");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusIsNull() {
            addCriterion("overdue_status is null");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusIsNotNull() {
            addCriterion("overdue_status is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusEqualTo(Integer value) {
            addCriterion("overdue_status =", value, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusNotEqualTo(Integer value) {
            addCriterion("overdue_status <>", value, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusGreaterThan(Integer value) {
            addCriterion("overdue_status >", value, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("overdue_status >=", value, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusLessThan(Integer value) {
            addCriterion("overdue_status <", value, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusLessThanOrEqualTo(Integer value) {
            addCriterion("overdue_status <=", value, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusIn(List<Integer> values) {
            addCriterion("overdue_status in", values, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusNotIn(List<Integer> values) {
            addCriterion("overdue_status not in", values, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusBetween(Integer value1, Integer value2) {
            addCriterion("overdue_status between", value1, value2, "overdueStatus");
            return (Criteria) this;
        }

        public Criteria andOverdueStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("overdue_status not between", value1, value2, "overdueStatus");
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