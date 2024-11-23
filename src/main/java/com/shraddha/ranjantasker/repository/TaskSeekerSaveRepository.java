package com.shraddha.ranjantasker.repository;

import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.TaskSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskSeekerSaveRepository extends JpaRepository<TaskSeekerSave, Integer> {
    List<TaskSeekerSave> findByUserId(TaskSeekerAccount userAccountId);

    List<TaskSeekerSave> findByTask(TaskPost task);

}
