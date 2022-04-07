package com.incs.spendtracking.response;

public class DeviceTypeDetails {
    private String id;
    private String name;
    private String description;
    private String reportInterval;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportInterval() {
        return reportInterval;
    }

    public void setReportInterval(String reportInterval) {
        this.reportInterval = reportInterval;
    }
}
