package org.example.usermanage.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

//    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "academy", nullable = false, length = 100)
    private String academy;

    @Column(name = "clasz", nullable = false, length = 100)
    private String clasz;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "nickname", nullable = false, length = 100)
    private String nickname;

    @Column(name = "open_id", nullable = false, length = 1000)
    private String openId;

    private String code;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
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

    @Override
    public String toString() {
        return "Users{" +
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
}