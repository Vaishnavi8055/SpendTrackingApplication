package com.incs.spendtracking.common;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collection = "user_history")
public class UserHistory {

    @Id
    private String userHistoryId;
    private String userId;
    private String userName;
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy | hh:mm")
    private LocalDateTime activityDoneAt;

    public String getUserHistoryId() {
        return userHistoryId;
    }

    public void setUserHistoryId(String userHistoryId) {
        this.userHistoryId = userHistoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getActivityDoneAt() {
        return activityDoneAt;
    }

    public void setActivityDoneAt(LocalDateTime activityDoneAt) {
        this.activityDoneAt = activityDoneAt;
    }
}
