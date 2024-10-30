package com.example.runningmanager.dao.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "user_challenges")
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_challenge_id")
    private Long userChallengeId; // 用户挑战ID，主键，自增

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId; // 用户ID，外键，关联到用户表

    @JoinColumn(name = "challenge_id", nullable = false)
    private Long challengeId; // 挑战ID，外键，关联到挑战表

    @Column(precision = 10, scale = 2)
    private BigDecimal currentDistance; // 当前完成的距离（千米），默认为0.00

    @Column(name = "completed_is",nullable = false)
    private Boolean completedIs; // 是否完成，布尔值，默认为false

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "joined_at")
    private Date joinedAt; // 参加时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completed_at")
    private Date completedAt; // 完成时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt; // 创建时间，不可更新

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt; // 更新时间

    // Getters and Setters


    public Long getUserChallengeId() {
        return userChallengeId;
    }

    public void setUserChallengeId(Long userChallengeId) {
        this.userChallengeId = userChallengeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public BigDecimal getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(BigDecimal currentDistance) {
        this.currentDistance = currentDistance;
    }

    public Boolean getCompletedIs() {
        return completedIs;
    }

    public void setCompletedIs(Boolean completedIs) {
        this.completedIs = completedIs;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserChallenge{" +
                "userChallengeId=" + userChallengeId +
                ", userId=" + userId +
                ", challengeId=" + challengeId +
                ", currentDistance=" + currentDistance +
                ", completedIs=" + completedIs +
                ", joinedAt=" + joinedAt +
                ", completedAt=" + completedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}