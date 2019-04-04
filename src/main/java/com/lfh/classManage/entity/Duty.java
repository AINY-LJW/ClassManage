package com.lfh.classManage.entity;

import java.util.Date;

public class Duty {
    private Integer id;

    private String name;

    private Date dutyDate;

    private String dutyClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public String getDutyClass() {
        return dutyClass;
    }

    public void setDutyClass(String dutyClass) {
        this.dutyClass = dutyClass == null ? null : dutyClass.trim();
    }
}