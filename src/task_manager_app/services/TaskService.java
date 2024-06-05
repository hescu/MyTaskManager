package task_manager_app.services;

import task_manager_app.controllers.Menu;
import task_manager_app.data.TaskCRUD;
import task_manager_app.model.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TaskService {

    public static List<Task> showCurrentTasks() {
        List<Task> currentTasks = TaskCRUD.queryCurrentTasks();
        for (Task task : currentTasks) {
            System.out.println(task.toString());
        }
        return currentTasks;
    }
    
    public static void addTask() {
        Scanner input = new Scanner(System.in);

        Task newTask = new Task();
        System.out.println("Create new Task: ");
        System.out.print("Title: ");
        newTask.setTitle(input.nextLine());
        System.out.print("Description: ");
        newTask.setDescription(input.nextLine());
        LocalDate dueDate = getValidDueDate();
        newTask.setDueDate(dueDate);
        System.out.println("Priority: ");
        newTask.setPriority(Integer.parseInt(input.nextLine()));

        if (checkIfUpdateWasSuccessful(TaskCRUD.createTask(newTask))) {
            System.out.println("New task was added.");
        } else {
            System.out.println("Task could not be added. Something went wrong.");
        }
    }

    private static LocalDate getValidDueDate() {
        Scanner input = new Scanner(System.in);

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

    public static void updateTask() {
        Scanner input = new Scanner(System.in);
        List<Task> listOfTasks = showCurrentTasks();
        System.out.println("Which task do you want to update?");
        int taskNum = input.nextInt();
        Optional<Task> result = listOfTasks.stream()
                .filter(task -> task.getTaskId() == taskNum)
                .findFirst();

        if (result.isPresent()) {
            Task taskToUpdate = result.get();
            Task updatedTask = updateTaskStatus(taskToUpdate);
            if (checkIfUpdateWasSuccessful(TaskCRUD.saveTask(updatedTask))) {
                System.out.println("Task updated!");
            }
        } else {
            System.out.println("Task not found. Update not successful");
        }
    }

    private static Task updateTaskStatus(Task taskToUpdate) {
        Scanner input = new Scanner(System.in);

        System.out.println("Change status to: ");
        System.out.println("1) PENDING");
        System.out.println("2) IN_PROGRESS");
        System.out.println("3) COMPLETED");

        int userInput = Menu.getUserInput(3);

        switch (userInput) {
            case 1:
                taskToUpdate.setStatus("PENDING");
                break;
            case 2:
                taskToUpdate.setStatus("IN_PROGRESS");
                break;
            case 3:
                taskToUpdate.setStatus("COMPLETED");
                break;
        }

        return taskToUpdate;
    }

    public static void deleteTask() {
        Scanner input = new Scanner(System.in);
        showCurrentTasks();
        System.out.println("Which task do you want to delete?");
        if (checkIfUpdateWasSuccessful(TaskCRUD.deleteTaskFromDB(input.nextInt()))) {
            System.out.println("Deletion successful.");
        } else {
            System.out.println("Task could not be deleted. Something went wrong.");
        }
    }

    public static List<Task> getCompletedTasks() {
        return TaskCRUD.queryCompletedTasks();
    }
}
