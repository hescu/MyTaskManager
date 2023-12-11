package task_manager_app.controllers;

import task_manager_app.services.TaskService;

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
            selection = getUserInput(input, MAX_OPTION);
        } while (checkMenuSelection(selection, MAX_OPTION));

        switch (selection) {
            case 1:
                viewTasksSubMenu(input);
                break;
            case 2:
                addUpdateTaskSubMenu(input);
                break;
            case 3:
                showReportsSubMenu();
                break;
        }
        return selection;
    }

    private static void viewTasksSubMenu(Scanner input) {
        System.out.println("Your current tasks: ");
        TaskService.showCurrentTasks();
        System.out.println();
        System.out.println("Press Enter to return to the main menu...");
        input.nextLine();
    }

    private static void addUpdateTaskSubMenu(Scanner input) {
        int sel;
        final int MAX_OPTION = 4;

        System.out.println("Edit Menu: What would you like to do? (Enter a number displayed below)");
        System.out.println();
        System.out.println("1) Add a task");
        System.out.println("2) Update a task");
        System.out.println("3) Delete a task");
        System.out.println("4) Exit");

        do {
            sel = getUserInput(input, MAX_OPTION);
        } while (checkMenuSelection(sel, MAX_OPTION));

        switch (sel) {
            case 1:
                TaskService.addTask(input);
                break;
            case 2:
                TaskService.updateTask(input);
                break;
            case 3:
                TaskService.deleteTask(input);
                break;
        }
    }

    private static void showReportsSubMenu() {
        // Implement logic for the "Show reports" submenu
        System.out.println("Showing reports...");
    }

    private static int getUserInput(Scanner input, int maxOption) {
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
