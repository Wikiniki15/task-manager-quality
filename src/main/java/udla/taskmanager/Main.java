package udla.taskmanager;

import udla.taskmanager.service.TaskService;
import udla.taskmanager.ui.TaskPrinter;

public class Main {
  public static void main(String[] args) {
    TaskService taskService = new TaskService();
    TaskPrinter printer = new TaskPrinter(taskService);

    taskService.addTask("Complete project");
    taskService.addTask("Write tests");
    printer.printAllTasks();
    taskService.removeTask(1);
    printer.printAllTasks();
  }
}
