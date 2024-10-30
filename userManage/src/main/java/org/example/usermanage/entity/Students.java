package org.example.usermanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * (Students)实体类
 *
 * @author makejava
 * @since 2024-10-30 10:10:43
 */
@TableName("students")
public class Students extends Model<Students> {
    @TableId(value = "student_id",type = IdType.AUTO)
    private String studentId;

    @TableField(value = "student_number")
    private String studentNumber;

    @TableField(value = "name")
    private String name;

    @TableField(value = "class")
    private String clzss;

    @TableField(value = "major")
    private String major;

    @TableField(value = "created_at")
    private Date createdAt;

    @TableField(value = "updated_at")
    private Date updatedAt;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClzss() {
        return clzss;
    }

    public void setClzss(String clzss) {
        this.clzss = clzss;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public Students() {
    }

    public Students(String studentId, String studentNumber, String name, String clzss, String major, Date createdAt, Date updatedAt) {
        this.studentId = studentId;
        this.studentNumber = studentNumber;
        this.name = name;
        this.clzss = clzss;
        this.major = major;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Students{" +
                "studentId='" + studentId + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                ", clzss='" + clzss + '\'' +
                ", major='" + major + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

