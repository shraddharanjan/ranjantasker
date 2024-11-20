package com.shraddha.ranjantasker.repository;

import com.shraddha.ranjantasker.entity.TaskPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskPostRepository extends JpaRepository<TaskPost, Integer> {
}
