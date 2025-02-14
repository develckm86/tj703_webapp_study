package com.tj703.web_app_server_study.model2_service.dto;

public class PasswordChangeHistory {
    private int changeId;
    private int userId;
    private String oldPassword;
    private String changedAt;

    @Override
    public String toString() {
        return "{" +
                "changeId=" + changeId +
                ", userId=" + userId +
                ", oldPassword='" + oldPassword + '\'' +
                ", changedAt='" + changedAt + '\'' +
                "}\n";
    }

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(String changedAt) {
        this.changedAt = changedAt;
    }
}
