package com.glen.services;

import com.glen.models.TaskRequest;
import com.glen.models.TaskUpdateRequest;
import com.glen.db.TasksDB;
import com.glen.models.GetTaskRequest;
import com.glen.models.Task;
import com.glen.models.TaskList;
import com.glen.models.TaskServiceGrpc.TaskServiceImplBase;
import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;

public class TaskService extends TaskServiceImplBase {
	@Override
	public void createTask(TaskRequest request, StreamObserver<Task> responseObserver) {
		responseObserver.onNext(TasksDB.create(request));
		responseObserver.onCompleted();
	}

	@Override
	public void getTasks(Empty request, StreamObserver<TaskList> responseObserver) {
		responseObserver.onNext(TasksDB.findAll());
		responseObserver.onCompleted();
	}

	@Override
	public void deleteTask(Task request, StreamObserver<Empty> responseObserver) {
		TasksDB.delete(request.getId());
		responseObserver.onCompleted();
	}

	@Override
	public void getTaskById(GetTaskRequest request, StreamObserver<Task> responseObserver) {
		responseObserver.onNext(TasksDB.findById(request.getId()));
		responseObserver.onCompleted();
	}

	@Override
	public void updateTask(TaskUpdateRequest request, StreamObserver<Task> responseObserver) {
		Task updatedTask = TasksDB.updateById(request.getId(),
				TaskRequest.newBuilder().setTask(request.getNewTask()).setDate(request.getNewDate()).build());
		responseObserver.onNext(updatedTask);
		responseObserver.onCompleted();
	}
}
