/**
 * 
 */
package ca.footeware.tasks.service.models;

import java.util.Date;
import java.util.UUID;

/**
 * @author Footeware.ca
 *
 */
public class Task {

	private String id = UUID.randomUUID().toString();
	private String title;
	private String description;
	private Date due;
	private boolean completed;

	/**
<<<<<<< HEAD
	 * Constructor.
	 * 
	 * @param title       {@link String}
	 * @param description {@link String}
	 * @param due         {@link Date}
	 * @param completed   boolean
	 */
	public Task(String title, String description, Date due, boolean completed) {
		this.title = title;
		this.description = description;
		this.due = due;
		this.completed = completed;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the due
	 */
	public Date getDue() {
		return due;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param due the due to set
	 */
	public void setDue(Date due) {
		this.due = due;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
=======
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the due
	 */
	public Date getDue() {
		return due;
	}

	/**
	 * @param due the due to set
	 */
	public void setDue(Date due) {
		this.due = due;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Constructor.
	 * 
	 * @param title       {@link String}
	 * @param description {@link String}
	 * @param due         {@link Date}
	 * @param completed   boolean
	 */
	public Task(String title, String description, Date due, boolean completed) {
		this.title = title;
		this.description = description;
		this.due = due;
		this.completed = completed;
>>>>>>> branch 'master' of https://github.com/CraigFoote/ca.footeware.tasks.git
	}

}
