package com.shraddha.ranjantasker.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class TaskPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskPostId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postedById", referencedColumnName = "userId")
    private Users postedById;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taskLocationId", referencedColumnName = "Id")
    private TaskLocation taskLocationId;

    @Transient
    private Boolean isActive;
    @Transient
    private Boolean isSaved;
    @Length(max = 10000)
    private String taskDescription;

    private String taskType;
    private String price;
    private String remote;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date postedDate;
    private String taskTitle;

    public TaskPost() {
    }

    public TaskPost(Integer taskPostId, Users postedById, TaskLocation taskLocationId, Boolean isActive, Boolean isSaved, String taskDescription, String taskType, String price, String remote, Date postedDate, String taskTitle) {
        this.taskPostId = taskPostId;
        this.postedById = postedById;
        this.taskLocationId = taskLocationId;
        this.isActive = isActive;
        this.isSaved = isSaved;
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.price = price;
        this.remote = remote;
        this.postedDate = postedDate;
        this.taskTitle = taskTitle;
    }

    public Integer getTaskPostId() {
        return taskPostId;
    }

    public void setTaskPostId(Integer taskPostId) {
        this.taskPostId = taskPostId;
    }

    public Users getPostedById() {
        return postedById;
    }

    public void setPostedById(Users postedById) {
        this.postedById = postedById;
    }

    public TaskLocation getTaskLocationId() {
        return taskLocationId;
    }

    public void setTaskLocationId(TaskLocation taskLocationId) {
        this.taskLocationId = taskLocationId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Override
    public String toString() {
        return "TaskPost{" +
                "taskPostId=" + taskPostId +
                ", postedById=" + postedById +
                ", taskLocationId=" + taskLocationId +
                ", isActive=" + isActive +
                ", isSaved=" + isSaved +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskType='" + taskType + '\'' +
                ", price='" + price + '\'' +
                ", remote='" + remote + '\'' +
                ", postedDate=" + postedDate +
                ", taskTitle='" + taskTitle + '\'' +
                '}';
    }
}
