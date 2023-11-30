package task_manager_app.controllers;

import java.util.Scanner;

public class Menu {
    public static int mainMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("MyTaskManager Main Menu: What would you like to do? (Enter a number displayed below)");
        System.out.println();
        System.out.println("1) View your tasks");
        System.out.println("2) Add/Update a task");
        System.out.println("3) Show reports");
        System.out.println("4) Exit");

        do {
            selection = input.nextInt();
        } while (selection < 1 || selection > 4);

        return selection;
    }

}
