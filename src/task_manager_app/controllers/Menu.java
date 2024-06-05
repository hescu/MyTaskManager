package task_manager_app.controllers;

import task_manager_app.model.Task;
import task_manager_app.services.TaskService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static int mainMenu(Scanner input) {
        int selection;
        final int MAX_OPTION = 4;

        System.out.println("MyTaskManager Main Menu: What would you like to do? (Enter a number displayed below)");
        System.out.println();
        System.out.println("1) View your tasks");
        System.out.println("2) Add/Update a task");
        System.out.println("3) Show reports");
        System.out.println("4) Exit");

        do {
            selection = getUserInput(MAX_OPTION);
        } while (checkMenuSelection(selection, MAX_OPTION));

        switch (selection) {
            case 1:
                viewTasksSubMenu();
                break;
            case 2:
                addUpdateTaskSubMenu();
                break;
            case 3:
                showReportsSubMenu();
                break;
        }
        return selection;
    }

    private static void viewTasksSubMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Your current tasks: ");
        TaskService.showCurrentTasks();
        System.out.println();
        System.out.println("Press Enter to return to the main menu...");
        input.nextLine();
    }

    private static void addUpdateTaskSubMenu() {
        int sel;
        final int MAX_OPTION = 4;

        System.out.println("Edit Menu: What would you like to do? (Enter a number displayed below)");
        System.out.println();
        System.out.println("1) Add a task");
        System.out.println("2) Update a task");
        System.out.println("3) Delete a task");
        System.out.println("4) Exit");

        do {
            sel = getUserInput(MAX_OPTION);
        } while (checkMenuSelection(sel, MAX_OPTION));

        switch (sel) {
            case 1:
                TaskService.addTask();
                break;
            case 2:
                TaskService.updateTask();
                break;
            case 3:
                TaskService.deleteTask();
                break;
        }
    }

    private static void showReportsSubMenu() {
        System.out.println("Showing reports...");
        System.out.println("Completed tasks: ");
        List<Task> completedTasks = TaskService.getCompletedTasks();

        for (Task task : completedTasks) {
            System.out.println(task.toString());
        }
    }

    public static int getUserInput(int maxOption) {
        Scanner input = new Scanner(System.in);

        String userInput = input.nextLine();

        if (userInput.isEmpty()) {
            return maxOption;
        }

        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return -1;
        }
    }


    private static boolean checkMenuSelection(int selection, int maxOption) {
        if (selection < 1 || selection > maxOption) {
            System.out.println("Please enter a number between 1 and " + maxOption + "!");
        }
        return selection < 1 || selection > maxOption;
    }
}
