package udla.taskmanager.repository;

import udla.taskmanager.model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public void delete(int taskId) {
        boolean removed = tasks.removeIf(
                task -> task.getId() == taskId
        );
        if (!removed) {
            throw new IllegalArgumentException(
                    "No existe tarea con ID: " + taskId
            );
        }
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public boolean existsByName(String name) {
        return tasks.stream()
                .anyMatch(task -> task.getName().equalsIgnoreCase(name));
    }
}