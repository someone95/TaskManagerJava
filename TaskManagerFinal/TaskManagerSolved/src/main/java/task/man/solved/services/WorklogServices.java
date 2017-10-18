package task.man.solved.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import task.man.solved.entities.Worklog;

@Service
@Transactional
public class WorklogServices extends BaseService<Worklog> {

}
