/**
 * 
 */
package ca.footeware.tasks.service.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.footeware.tasks.service.ITaskService;
import ca.footeware.tasks.service.models.Task;

/**
 * @author Footeware.ca
 *
 */
public class TaskService implements ITaskService {
	
	private List<Task> tasks = new ArrayList<>();
	
	public TaskService() {
	}
		
	@Override
	public List<Task> getTasks() {
		return tasks;
	}

	@Override
	public Task getTask(String id) {
		for (Task task : tasks) {
			if(task.getId().equals(id)) {
				return task;
			}
		}
		return null;
	}

	@Override
	public Task createTask(String title, String description, Date due, boolean completed) {
		Task newTask = new Task(title, description, due, completed);
		tasks.add(newTask);
		return newTask;
	}

	@Override
	public boolean deleteTask(String id) {
		for (Task task : tasks) {
			if(task.getId().equals(id)) {
				tasks.remove(task);
				return true;
			}
		}
		return false;
	}

	@Override
	public Task updateTask(String id, String title, String description, Date due, boolean completed) {
		for (Task task : tasks) {
			if(task.getId().equals(id)) {
				task.setTitle(title);
				task.setDescription(description);
				task.setDue(due);
				task.setCompleted(completed);
				return task;
			}
		}
		return null;
	}

}
