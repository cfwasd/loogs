package com.example.runningmanager.dao.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "running_records")
public class RunningRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId; // 记录ID，主键，自增

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id; // 用户ID，外键，关联到用户表

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal distance; // 距离（千米），不能为空

    @Column(nullable = false)
    private Integer duration; // 持续时间（秒），不能为空

    @Column(name = "start_location_lat", nullable = false, precision = 10, scale = 8)
    private BigDecimal startLocationLat; // 开始位置纬度，不能为空

    @Column(name = "start_location_lon", nullable = false, precision = 11, scale = 8)
    private BigDecimal startLocationLon; // 开始位置经度，不能为空

    @Column(name = "end_location_lat", nullable = false, precision = 10, scale = 8)
    private BigDecimal endLocationLat; // 结束位置纬度，不能为空

    @Column(name = "end_location_lon", nullable = false, precision = 11, scale = 8)
    private BigDecimal endLocationLon; // 结束位置经度，不能为空

    @Column(precision = 10, scale = 2)
    private BigDecimal averageSpeed; // 平均速度（千米/小时）

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date; // 跑步日期时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt; // 创建时间，不可更新

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt; // 更新时间

    // Getters and Setters
    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public User getUser() {
        return user_id;
    }

    public void setUser(User user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getStartLocationLat() {
        return startLocationLat;
    }

    public void setStartLocationLat(BigDecimal startLocationLat) {
        this.startLocationLat = startLocationLat;
    }

    public BigDecimal getStartLocationLon() {
        return startLocationLon;
    }

    public void setStartLocationLon(BigDecimal startLocationLon) {
        this.startLocationLon = startLocationLon;
    }

    public BigDecimal getEndLocationLat() {
        return endLocationLat;
    }

    public void setEndLocationLat(BigDecimal endLocationLat) {
        this.endLocationLat = endLocationLat;
    }

    public BigDecimal getEndLocationLon() {
        return endLocationLon;
    }

    public void setEndLocationLon(BigDecimal endLocationLon) {
        this.endLocationLon = endLocationLon;
    }

    public BigDecimal getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(BigDecimal averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}