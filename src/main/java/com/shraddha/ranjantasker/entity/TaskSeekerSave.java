package com.shraddha.ranjantasker.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "job"})})
public class TaskSeekerSave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "user_account_id")
    private TaskSeekerAccount userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task", referencedColumnName = "taskPostId")
    private TaskPost task;

    public TaskSeekerSave() {
    }

    public TaskSeekerSave(Integer id, TaskSeekerAccount userId, TaskPost task) {
        this.id = id;
        this.userId = userId;
        this.task = task;
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

    @Override
    public String toString() {
        return "TaskSeekerSave{" +
                "id=" + id +
                ", userId=" + userId.toString() +
                ", task=" + task.toString() +
                '}';
    }
}
