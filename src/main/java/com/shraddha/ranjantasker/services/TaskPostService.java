package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.IRequesterTasks;
import com.shraddha.ranjantasker.entity.RequesterTasksDto;
import com.shraddha.ranjantasker.entity.TaskLocation;
import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.repository.TaskPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskPostService {

    private final TaskPostRepository taskPostRepository;

    @Autowired
    public TaskPostService(TaskPostRepository taskPostRepository) {
        this.taskPostRepository = taskPostRepository;
    }

    public TaskPost addNew(TaskPost taskPost) {
        return taskPostRepository.save(taskPost);
    }

    public List<RequesterTasksDto> getRequesterTasks(int requester){
        List<IRequesterTasks> requesterTasksDtos = taskPostRepository.getRequesterTasks(requester);
        List<RequesterTasksDto> requesterTasksDtoList = new ArrayList<>();

        for (IRequesterTasks req: requesterTasksDtos) {
            TaskLocation loc = new TaskLocation(req.getLocationId(), req.getCity(), req.getCountry());
            requesterTasksDtoList.add(new RequesterTasksDto(req.getTotalCandidates(), req.getTask_post_id(), req.getTask_title(), loc));
        }
        return requesterTasksDtoList;

    }

    public TaskPost getOne(int id) {
        return taskPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }
}
