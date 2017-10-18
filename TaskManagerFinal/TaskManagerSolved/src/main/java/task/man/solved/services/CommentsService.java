package task.man.solved.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import task.man.solved.entities.Comment;

@Service
@Transactional
public class CommentsService extends BaseService<Comment> {

}
