package com.shraddha.ranjantasker.entity;

public class RequesterTasksDto {
    private Long totalCandidates;
    private Integer taskPostId;
    private String taskTitle;
    private TaskLocation taskLocationId;

    public RequesterTasksDto(Long totalCandidates, Integer taskPostId, String taskTitle, TaskLocation taskLocationId) {
        this.totalCandidates = totalCandidates;
        this.taskPostId = taskPostId;
        this.taskTitle = taskTitle;
        this.taskLocationId = taskLocationId;
    }

    public Long getTotalCandidates() {
        return totalCandidates;
    }

    public void setTotalCandidates(Long totalCandidates) {
        this.totalCandidates = totalCandidates;
    }

    public Integer getTaskPostId() {
        return taskPostId;
    }

    public void setTaskPostId(Integer taskPostId) {
        this.taskPostId = taskPostId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public TaskLocation getTaskLocationId() {
        return taskLocationId;
    }

    public void setTaskLocationId(TaskLocation taskLocationId) {
        this.taskLocationId = taskLocationId;
    }
}
