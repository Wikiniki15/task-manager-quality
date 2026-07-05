package udla.taskmanager.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import udla.taskmanager.model.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void testSaveAndFindAll() {
        Task task = new Task(1, "Test Task");
        repository.save(task);

        List<Task> tasks = repository.findAll();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getName());
        assertEquals(1, tasks.get(0).getId());
    }

    @Test
    void testDeleteExistingTask() {
        Task task = new Task(1, "Test Task");
        repository.save(task);
        
        repository.delete(1);
        
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void testDeleteNonExistingTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> repository.delete(999));
    }

    @Test
    void testExistsByNameTrue() {
        Task task = new Task(1, "Test Task");
        repository.save(task);

        assertTrue(repository.existsByName("Test Task"));
        assertTrue(repository.existsByName("test task"));
    }

    @Test
    void testExistsByNameFalse() {
        Task task = new Task(1, "Test Task");
        repository.save(task);

        assertFalse(repository.existsByName("Other Task"));
    }
}
