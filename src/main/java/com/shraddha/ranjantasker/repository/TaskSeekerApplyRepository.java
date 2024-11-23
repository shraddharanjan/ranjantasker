package com.shraddha.ranjantasker.repository;

import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.TaskSeekerApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskSeekerApplyRepository extends JpaRepository<TaskSeekerApply, Integer> {
    List<TaskSeekerApply> findByUserId(TaskSeekerAccount userId);

    List<TaskSeekerApply> findByTask(TaskPost task);
}
