package task.man.solved.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Worklog extends BaseEntity {
	@Column
	private int hours;
	@Column
	private Date dateWorklog;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "task")
	private Task task;

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Date getDateWorklog() {
		return dateWorklog;
	}

	public void setDateWorklog(Date dateWorklog) {
		this.dateWorklog = dateWorklog;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
