package com.colson.common.bean;

import com.colson.common.emum.JudgeType;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Condition implements Serializable {

    private String condition;
    private List<Condition> ands;
    private List<Condition> ors;
    private String name;
    private JudgeType judge;
    private Object value;
    private Object[] values;

    public Condition() {
    }

    public Condition(String condition) {
        this.condition = condition;
    }

    public Condition(String condition, Object value) {
        this.condition = condition;
        if (value != null) {
            this.value = value;
        }

    }

    public Condition(String condition, Collection values) {
        this.condition = condition;
        if (values != null) {
            this.values = new String[values.size()];
            int i = 0;

            Object obj;
            for(Iterator var4 = values.iterator(); var4.hasNext(); this.values[i++] = String.valueOf(obj)) {
                obj = var4.next();
            }
        }

    }

    public Condition(String condition, Object[] values) {
        this.condition = condition;
        if (values != null) {
            this.values = new String[values.length];

            for(int i = 0; i < values.length; ++i) {
                this.values[i] = String.valueOf(values[i]);
            }
        }

    }

    public Condition analysis() {
        if (StringUtils.isEmpty(this.condition)) {
            this.judge = JudgeType.NONE;
        }

        if (this.judge == null) {
            int index = this.condition.lastIndexOf(".");
            if (index < 0) {
                throw new IllegalArgumentException(this.condition + " format error");
            }

            this.judge = JudgeType.valueOf(this.condition.substring(index + 1).toUpperCase());
            this.name = this.condition.substring(0, index);
        }

        Condition or;
        Iterator var3;
        if (this.ands != null) {
            var3 = this.ands.iterator();

            while(var3.hasNext()) {
                or = (Condition)var3.next();
                or.analysis();
            }
        }

        if (this.ors != null) {
            var3 = this.ors.iterator();

            while(var3.hasNext()) {
                or = (Condition)var3.next();
                or.analysis();
            }
        }

        this.checkCondition();
        return this;
    }

    private void checkCondition() {
        if (this.judge != JudgeType.NONE && this.judge != JudgeType.ISNULL && this.judge != JudgeType.NOTNULL) {
            if (this.judge != JudgeType.IN && this.judge != JudgeType.NOTIN) {
                if (this.judge != JudgeType.BETWEEN && this.judge != JudgeType.JSON_EQ) {
                    if (this.value == null) {
                        throw new IllegalArgumentException(this.condition + " value must not be null");
                    }
                } else if (this.values == null || this.values.length != 2) {
                    throw new IllegalArgumentException(this.condition + " values length must = 2");
                }
            } else if (this.values == null || this.values.length < 1) {
                throw new IllegalArgumentException(this.condition + " values length must > 1");
            }
        }
    }

    public Condition or(Condition condition) {
        if (this.ors == null) {
            this.ors = new ArrayList();
        }

        this.ors.add(condition);
        return this;
    }

    public Condition and(Condition condition) {
        if (this.ands == null) {
            this.ands = new ArrayList();
        }

        this.ands.add(condition);
        return this;
    }

    public String getCondition() {
        return this.condition;
    }

    public List<Condition> getAnds() {
        return this.ands;
    }

    public List<Condition> getOrs() {
        return this.ors;
    }

    public String getName() {
        return this.name;
    }

    public JudgeType getJudge() {
        return this.judge;
    }

    public Object getValue() {
        return this.value;
    }

    public Object[] getValues() {
        return this.values;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setAnds(List<Condition> ands) {
        this.ands = ands;
    }

    public void setOrs(List<Condition> ors) {
        this.ors = ors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJudge(JudgeType judge) {
        this.judge = judge;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
