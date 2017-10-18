package task.man.solved.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity {

	@Column
	private String description;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "task")
	private Task task;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
