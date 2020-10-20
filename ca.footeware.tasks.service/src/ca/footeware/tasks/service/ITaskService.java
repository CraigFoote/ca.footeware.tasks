/**
 * 
 */
package ca.footeware.tasks.service;

import java.util.Date;
import java.util.List;

import ca.footeware.tasks.service.models.Task;

/**
 * @author Footeware.ca
 *
 */
public interface ITaskService {

	public List<Task> getTasks();

	public Task getTask(String id);

	public Task createTask(String title, String description, Date due, boolean completed);

	public boolean deleteTask(String id);

	public Task updateTask(String id, String title, String description, Date due, boolean completed);

}
