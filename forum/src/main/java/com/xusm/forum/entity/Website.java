package com.xusm.forum.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "website")
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long webId;

    private Integer webPv;

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    public Integer getWebPv() {
        return webPv;
    }

    public void setWebPv(Integer webPv) {
        this.webPv = webPv;
    }

    public Integer getWebUv() {
        return webUv;
    }

    public void setWebUv(Integer webUv) {
        this.webUv = webUv;
    }

    public Integer getWebIp() {
        return webIp;
    }

    public void setWebIp(Integer webIp) {
        this.webIp = webIp;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public Integer getWebSm() {
        return webSm;
    }

    public void setWebSm(Integer webSm) {
        this.webSm = webSm;
    }

    public Integer getWebJs() {
        return webJs;
    }

    public void setWebJs(Integer webJs) {
        this.webJs = webJs;
    }

    public Integer getWebInfo() {
        return webInfo;
    }

    public void setWebInfo(Integer webInfo) {
        this.webInfo = webInfo;
    }

    public Integer getWebLogin() {
        return webLogin;
    }

    public void setWebLogin(Integer webLogin) {
        this.webLogin = webLogin;
    }

    public Integer getWebRegister() {
        return webRegister;
    }

    public void setWebRegister(Integer webRegister) {
        this.webRegister = webRegister;
    }

    private Integer webUv;

    private Integer webIp;

    private Date schedule;

    private Integer webSm;

    private Integer webJs;

    private Integer webInfo;

    private Integer webLogin;

    private Integer webRegister;

    public Integer getWebSource() {
        return webSource;
    }

    public void setWebSource(Integer webSource) {
        this.webSource = webSource;
    }

    private Integer webSource;
}
