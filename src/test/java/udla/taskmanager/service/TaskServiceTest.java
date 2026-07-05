package udla.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TaskServiceTest {

    @Test
    void addTaskStoresNewTask() {
        TaskService taskService = new TaskService();

        taskService.addTask("Complete project");

        assertEquals(1, taskService.getAllTasks().size());
        assertEquals("Complete project", taskService.getAllTasks().get(0).getName());
    }

    @Test
    void addTaskRejectsDuplicateName() {
        TaskService taskService = new TaskService();
        taskService.addTask("Write tests");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.addTask("write tests")
        );

        assertEquals("La tarea ya existe: write tests", exception.getMessage());
    }

    @Test
    void removeTaskDeletesExistingTask() {
        TaskService taskService = new TaskService();
        taskService.addTask("Review report");

        taskService.removeTask(1);

        assertEquals(0, taskService.getAllTasks().size());
    }
}
