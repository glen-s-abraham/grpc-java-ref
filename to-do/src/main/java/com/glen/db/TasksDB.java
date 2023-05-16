package com.glen.db;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.glen.models.Task;
import com.glen.models.TaskList;
import com.glen.models.TaskRequest;

public class TasksDB {
	private static final Map<String, Task> db = new HashMap<String, Task>();
	
	private static boolean checkIfRecordExists(String id) throws RuntimeException{
		if(!db.containsKey(id)) {
			throw new RuntimeException("Record with id: "+id+" not found");
		}
		return true;
	}
	
	private static Task buildTask(TaskRequest req) {
		return  Task.newBuilder()
				.setId(UUID.randomUUID().toString())
				.setTask(req.getTask())
				.setDate(req.getDate())
				.build();
		
	}
	
	public static Task create(TaskRequest req) {
		Task task = buildTask(req);
		db.put(task.getId(), task);
		return task;
	}
	
	public static void delete(String id) {
		checkIfRecordExists(id);
		db.remove(id);
	}
	
	public static Task findById(String id) {
		return db.get(id);
	}
	
	public static Task updateById(String id,TaskRequest updtdTaskReq) {
		checkIfRecordExists(id);
		Task task = buildTask(updtdTaskReq);
		db.put(id,task);
		return task;
	}
	
	public static TaskList findAll() {
		return TaskList.newBuilder().addAllTasks( db.values()).build();
	}
	
}
