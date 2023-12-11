package task_manager_app.services;

import task_manager_app.data.TaskCRUD;
import task_manager_app.model.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TaskService {
    public static void showCurrentTasks() {
        List<Task> currentTasks = TaskCRUD.queryAllTasks();
        for (Task task : currentTasks) {
            System.out.println(task.toString());
        }
    }
    
    public static void addTask(Scanner input) {
        Task newTask = new Task();
        System.out.println("Create new Task: ");
        System.out.print("Title: ");
        newTask.setTitle(input.nextLine());
        System.out.print("Description: ");
        newTask.setDescription(input.nextLine());
        LocalDate dueDate = getValidDueDate(input);
        newTask.setDueDate(dueDate);
        System.out.println("Priority: ");
        newTask.setPriority(input.nextInt());

        if (checkIfUpdateWasSuccessful(TaskCRUD.createTask(newTask))) {
            System.out.println("New task was added.");
        } else {
            System.out.println("Task could not be added. Something went wrong.");
        }
    }

    private static LocalDate getValidDueDate(Scanner input) {
        while (true) {
            try {
                System.out.println("Due Date (yyyy-MM-dd): ");
                String dateString = input.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(dateString, formatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a valid date.");
            }
        }
    }

    public static boolean checkIfUpdateWasSuccessful(int x) {
        return x > 0;
    }

    public static void updateTask(Scanner input) {
    }

    public static void deleteTask(Scanner input) {
        showCurrentTasks();
        System.out.println("Which task do you want to delete?");
        if (checkIfUpdateWasSuccessful(TaskCRUD.deleteTaskFromDB(input.nextInt()))) {
            System.out.println("Deletion successful.");
        } else {
            System.out.println("Task could not be deleted. Something went wrong.");
        }
    }
}
