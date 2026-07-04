package udla.taskmanager.repository;

import udla.taskmanager.model.Task;
import java.util.List;

public interface TaskRepository {
  void save(Task task);
  void delete(int taskId);
  List<Task> findAll();
  boolean existsByName(String name);
}