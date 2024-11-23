package com.shraddha.ranjantasker.repository;

import com.shraddha.ranjantasker.entity.IRequesterTasks;
import com.shraddha.ranjantasker.entity.TaskPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
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

    @Query(value = "SELECT * FROM task_post t " +
            "INNER JOIN task_location l ON t.task_location_id = l.id " +
            "WHERE t.task_title LIKE CONCAT('%', :task, '%') " +
            "AND ((l.city LIKE CONCAT('%', :location, '%') OR l.country LIKE CONCAT('%', :location, '%')) " +
            "     AND t.task_type IN(:type) " +
            "     AND t.remote IN(:remote))",
            nativeQuery = true)
    List<TaskPost> searchWithoutDate(@Param("task") String task, @Param("location") String location, @Param("remote") List<String> remote, @Param("type") List<String> type);

    @Query(value = "SELECT * FROM task_post t " +
            "INNER JOIN task_location l ON t.task_location_id = l.id " +
            "WHERE t.task_title LIKE CONCAT('%', :task, '%') " +
            "AND ((l.city LIKE CONCAT('%', :location, '%') " +
            "      OR l.country LIKE CONCAT('%', :location, '%')) " +
            "     AND t.task_type IN (:type) " +
            "     AND t.remote IN (:remote) " +
            "     AND t.posted_date >= :date)",
            nativeQuery = true)
    List<TaskPost> search(@Param("task") String task,
                          @Param("location") String location,
                          @Param("remote") List<String> remote,
                          @Param("type") List<String> type,
                          @Param("date") LocalDate date);
}
