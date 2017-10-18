package task.man.solved.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import task.man.solved.entities.Task;

@Service
@Transactional
public class TasksService extends BaseService<Task> {

}
