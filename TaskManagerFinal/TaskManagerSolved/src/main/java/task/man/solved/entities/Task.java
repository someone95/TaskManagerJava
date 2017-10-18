package task.man.solved.entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Task extends BaseEntity {

	@Column
	private String title;
	@Column
	private String content;
	@Column
	private double mark;
	@Column
	private Date dateOfCreation;
	@Column
	private Date dateOfLastEdit;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "creator")
	private User creator;
	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
	private Set<Comment> comments;
	@OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
	private Set<Worklog> worklogs;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "assignedUser")
	private User assignedUser;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Date getDateOfLastEdit() {
		return dateOfLastEdit;
	}

	public void setDateOfLastEdit(Date dateOfLastEdit) {
		this.dateOfLastEdit = dateOfLastEdit;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Worklog> getWorklogs() {
		return worklogs;
	}

	public void setWorklogs(Set<Worklog> worklogs) {
		this.worklogs = worklogs;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}
	

}
