package task_manager_app;

import task_manager_app.controllers.Menu;

import java.util.Scanner;

import static task_manager_app.controllers.Menu.mainMenu;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (mainMenu(input) != 4) {
            System.out.println();
        }
        input.close();
    }
}

// Idea: Write / Read JSON instead of Database
