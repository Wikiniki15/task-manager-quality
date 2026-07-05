package udla.taskmanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import udla.taskmanager.model.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void testAddTaskSuccess() {
        taskService.addTask("Task 1");
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0).getName());
    }

    @Test
    void testAddTaskNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask(null));
    }

    @Test
    void testAddTaskEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask("   "));
    }

    @Test
    void testAddDuplicateTaskThrowsException() {
        taskService.addTask("Task 1");
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask("Task 1"));
    }

    @Test
    void testRemoveTaskSuccess() {
        taskService.addTask("Task 1");
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        int taskId = tasks.get(0).getId();

        taskService.removeTask(taskId);
        assertTrue(taskService.getAllTasks().isEmpty());
    }

    @Test
    void testRemoveTaskInvalidIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> taskService.removeTask(0));
        assertThrows(IllegalArgumentException.class, () -> taskService.removeTask(-1));
    }

    @Test
    void testGetAllTasksEmpty() {
        assertTrue(taskService.getAllTasks().isEmpty());
    }

    @Test
    void testGetAllTasksMultiple() {
        taskService.addTask("Task 1");
        taskService.addTask("Task 2");
        
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.stream().anyMatch(t -> t.getName().equals("Task 1")));
        assertTrue(tasks.stream().anyMatch(t -> t.getName().equals("Task 2")));
    }
}
