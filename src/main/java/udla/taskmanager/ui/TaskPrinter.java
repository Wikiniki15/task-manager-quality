package udla.taskmanager.ui;

import udla.taskmanager.model.Task;
import udla.taskmanager.service.TaskService;
import java.util.List;

public class TaskPrinter {
    private final TaskService taskService;

    public TaskPrinter(final TaskService taskService) {
        this.taskService = taskService;
    }

    public void printAllTasks() {
        final List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        tasks.forEach(task -> System.out.println(task.toString()));
    }
}
