package task.man.solved.entities;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class User extends BaseEntity {
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private boolean admin;
	@OneToMany(mappedBy = "creator", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
	private Set<Task> tasks;
	@OneToMany(mappedBy = "assignedUser", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
	private Set<Task> assignedTasks;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Task> getAssignedTasks() {
		return assignedTasks;
	}

	public void setAssignedTasks(Set<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

}
