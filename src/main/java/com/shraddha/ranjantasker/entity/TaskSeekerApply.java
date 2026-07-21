package com.shraddha.ranjantasker.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(
    name = "task_seeker_apply",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_task_seeker_apply_user_task",
            columnNames = {"user_id", "task"}
        )
    }
)
public class TaskSeekerApply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "user_account_id",
        nullable = false
    )
    private TaskSeekerAccount userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "task",
        referencedColumnName = "taskPostId",
        nullable = false
    )
    private TaskPost task;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apply_date")
    private Date applyDate;

    @Column(name = "cover_letter")
    private String coverLetter;

    public TaskSeekerApply() {
    }

    public TaskSeekerApply(
            Integer id,
            TaskSeekerAccount userId,
            TaskPost task,
            Date applyDate,
            String coverLetter
    ) {
        this.id = id;
        this.userId = userId;
        this.task = task;
        this.applyDate = applyDate;
        this.coverLetter = coverLetter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaskSeekerAccount getUserId() {
        return userId;
    }

    public void setUserId(TaskSeekerAccount userId) {
        this.userId = userId;
    }

    public TaskPost getTask() {
        return task;
    }

    public void setTask(TaskPost task) {
        this.task = task;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    @Override
    public String toString() {
        return "TaskSeekerApply{" +
                "id=" + id +
                ", userId=" + userId +
                ", task=" + task +
                ", applyDate=" + applyDate +
                ", coverLetter='" + coverLetter + '\'' +
                '}';
    }
}