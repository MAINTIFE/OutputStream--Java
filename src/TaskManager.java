import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    // Method to add a new task.
    public void addTask(){
        System.out.println("Enter task name: ");
        String name = scanner.nextLine();
        System.out.println("Enter due date (e.g., 2024-11-01): ");
        String dueDate = scanner.nextLine();
        System.out.println("Enter priority (1-5): ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        Task newTask = new Task(name, dueDate, priority);
        tasks.add(newTask);
        System.out.println("New task added successfully!");
    }

    // Method to view all tasks.
    public  void viewTasks(){
        if (tasks.isEmpty()){
            System.out.println("No tasks available.");
        }else{
            for (Task task : tasks){
                System.out.println(task);
            }
        }
    }

    // Method to mark a task as complete
    public void completeTask() {
        System.out.println("Enter task number to mark as complete: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber <= tasks.size() && taskNumber > 0) {
            Task task = tasks.get(taskNumber - 1);
            task.completeTask();
            System.out.println("Task marked as complete!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Method to remove a task
    public void removeTask() {
        System.out.println("Enter task number to remove: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber <= tasks.size() && taskNumber > 0) {
            tasks.remove(taskNumber - 1);
            System.out.println("Task removed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
/*
    OutputStream  -Writes data(in bytes form) to a target
    InputStream     -Reads data(in bytes form) from a target
    ObjectOutputStream -Writes a java type(Used for writing Java objects to a stream, allowing for object serialization. Works with entire objects,
                        converting them into a byte stream if they implement Serializable.)
    ObjectInputStream    -Reads a java type
    FileOutputStream     -Writes to a file(Used for writing raw binary data directly to a file. Works with bytes and primitive data types.)
    FileInputStream      -Reads from a file

 */

    public void saveTasksToFile() {
        try (FileOutputStream fileOut = new FileOutputStream("tasks.dat");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks);
            System.out.println("Tasks have been saved to tasks.dat.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        try (FileInputStream fileIn = new FileInputStream("tasks.dat");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            tasks = (ArrayList<Task>) in.readObject();
            System.out.println("Tasks have been loaded from tasks.dat.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TaskManager TM = new TaskManager();
        TM.loadTasksFromFile(); //Load tasks when the program starts.
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n1. Add Task\n2. View Tasks\n3. Complete Task\n4. Remove Task\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    TM.addTask();
                    break;
                case 2:
                    TM.viewTasks();
                    break;
                case 3:
                    TM.completeTask();
                     break;
                case 4:
                    TM.removeTask();
                    break;
                case 5:
                    TM.saveTasksToFile();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
