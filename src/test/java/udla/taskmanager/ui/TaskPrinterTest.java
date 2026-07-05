package udla.taskmanager.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import udla.taskmanager.service.TaskService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskPrinterTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void setUp() {
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testPrintAllTasksEmpty() {
        TaskService taskService = new TaskService();
        TaskPrinter printer = new TaskPrinter(taskService);

        printer.printAllTasks();

        assertTrue(capturedOut.toString().contains("No hay tareas registradas."));
    }

    @Test
    void testPrintAllTasksWithTasks() {
        TaskService taskService = new TaskService();
        taskService.addTask("Complete project");
        taskService.addTask("Write tests");
        TaskPrinter printer = new TaskPrinter(taskService);

        printer.printAllTasks();

        String output = capturedOut.toString();
        assertTrue(output.contains("Task 1: Complete project"));
        assertTrue(output.contains("Task 2: Write tests"));
    }
}
