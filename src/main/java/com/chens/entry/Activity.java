package com.chens.entry;

import java.io.Serializable;

/**
 * 活动
 */
public class Activity  implements Serializable {
    public Activity() {
    }
    private String cn_activity_id;
    private String cn_activity_title;
    private String cn_activity_body;
    private String cn_activity_end_time;

    public String getCn_activity_id() {
        return cn_activity_id;
    }

    public void setCn_activity_id(String cn_activity_id) {
        this.cn_activity_id = cn_activity_id;
    }

    public String getCn_activity_title() {
        return cn_activity_title;
    }

    public void setCn_activity_title(String cn_activity_title) {
        this.cn_activity_title = cn_activity_title;
    }

    public String getCn_activity_body() {
        return cn_activity_body;
    }

    public void setCn_activity_body(String cn_activity_body) {
        this.cn_activity_body = cn_activity_body;
    }

    public String getCn_activity_end_time() {
        return cn_activity_end_time;
    }
    public void setCn_activity_end_time(String cn_activity_end_time) {
        this.cn_activity_end_time = cn_activity_end_time;
    }

}