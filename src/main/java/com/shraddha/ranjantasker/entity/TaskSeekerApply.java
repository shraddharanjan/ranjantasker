package com.shraddha.ranjantasker.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "job"}))
public class TaskSeekerApply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "user_account_id")
    private TaskSeekerAccount userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task", referencedColumnName = "taskPostId")
    private TaskPost task;


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date applyDate;

    private String coverLetter;

    public TaskSeekerApply(){

    }

    public TaskSeekerApply(Integer id, TaskSeekerAccount userId, TaskPost task, Date applyDate, String coverLetter) {
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
                ", taskSeekerAccount=" + userId +
                ", task=" + task +
                ", applyDate=" + applyDate +
                ", coverLetter='" + coverLetter + '\'' +
                '}';
    }
}
