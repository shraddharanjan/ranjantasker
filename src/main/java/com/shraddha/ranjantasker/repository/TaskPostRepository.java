package com.shraddha.ranjantasker.repository;

import com.shraddha.ranjantasker.entity.IRequesterTasks;
import com.shraddha.ranjantasker.entity.TaskPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskPostRepository extends JpaRepository<TaskPost, Integer> {
    @Query(value = " SELECT COUNT(s.user_id) as totalCandidates,t.task_post_id,t.task_title,l.id as locationId,l.city,l.country FROM task_post t " +
            " inner join task_location l " +
            " on t.task_location_id = l.id " +
            " left join task_seeker_apply s " +
            " on s.task = t.task_post_id " +
            " where t.posted_by_id = :requester " +
            " GROUP By t.task_post_id" ,nativeQuery = true)

    List<IRequesterTasks> getRequesterTasks(@Param("requester") int requester);
}
