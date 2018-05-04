package br.senai.sp.keep.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.senai.sp.keep.model.Task;

public class TaskDAO {

	static {
		tasks = new ArrayList<Task>();
	}

	public static List<Task> tasks;
	public static Long currentId = 1L;

	public void addTask(Task task) {
		task.setId(currentId++);
		tasks.add(task);
	}

	public List<Task> getAll() {
		return tasks;
	}

	public Boolean deleteTask(Long id) {
		Boolean result = false;
		int i = 0;
		for (Task task : tasks) {
			if (task.getId() == id) {
				tasks.remove(i);
				result = true;
				break;
			}
			i++;
		}
		return result;
	}
	
	public Task getTask(Long id) {
		for(Task task : tasks) {
			if(task.getId() == id)
				return task;
		}
		return null;
	}
}
