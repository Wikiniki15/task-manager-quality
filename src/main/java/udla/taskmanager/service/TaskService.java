package udla.taskmanager.service;

import udla.taskmanager.model.Task;
import udla.taskmanager.repository.TaskRepository;
import udla.taskmanager.repository.InMemoryTaskRepository;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;
    private int nextId = 1;

    public TaskService() {
        this.taskRepository = new InMemoryTaskRepository();
    }

    public void addTask(String taskName) {
        validateTaskName(taskName);
        if (taskRepository.existsByName(taskName)) {
            throw new IllegalArgumentException(
                    "La tarea ya existe: " + taskName
            );
        }
        Task newTask = new Task(nextId++, taskName);
        taskRepository.save(newTask);
    }

    public void removeTask(int taskId) {
        if (taskId <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser mayor a 0. ID recibido: " + taskId
            );
        }
        taskRepository.delete(taskId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    private void validateTaskName(String taskName) {
        if (taskName == null || taskName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre de la tarea no puede ser nulo o vacío."
            );
        }
    }
}