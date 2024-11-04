package org.example.usermanage.entity;

public class User {


    private Integer userId;


    private Integer studentId;


    private String academy;


    private String clasz;


    private String userName;


    private String nickname;

    private String openId;

    private String code;

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", studentId=" + studentId +
                ", academy='" + academy + '\'' +
                ", clasz='" + clasz + '\'' +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", openId='" + openId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getClasz() {
        return clasz;
    }

    public void setClasz(String clasz) {
        this.clasz = clasz;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}