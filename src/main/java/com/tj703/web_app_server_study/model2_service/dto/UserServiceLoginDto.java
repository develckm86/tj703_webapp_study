package com.tj703.web_app_server_study.model2_service.dto;

public class UserServiceLoginDto {
    //map.set("userDto",new UserDto());
    //map.set("isPwHistory",true);
    private UserDto user;
    private boolean isPwHistory;
    @Override
    public String toString() {
        return "UserServiceLoginDto{" +
                "user=" + user +
                ", isPwHistory=" + isPwHistory +
                '}';
    }
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isPwHistory() {
        return isPwHistory;
    }

    public void setPwHistory(boolean pwHistory) {
        isPwHistory = pwHistory;
    }
}
