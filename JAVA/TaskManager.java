import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;

class Task {

    static Scanner scanner = new Scanner(System.in);
    String title;
    String description;
    int priority;
    LocalDate dueDate;
    String category;
    TaskStatus status;

    enum TaskStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
    }

    public Task(String title,String description,int priority,LocalDate dueDate,String category,TaskStatus status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.category = category;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getCategory() {
        return category;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return ("Title: " +
                title +
                "\nDescription: " +
                description +
                "\nPriority: " +
                priority +
                "\nDue Date: " +
                dueDate +
                "\nCategory: " +
                category +
                "\nStatus: " +
                status +
                "\n");
    }
}

class Node {

    Task data;
    Node next;
    Node prev;

    public Node(Task data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class TaskManager {

    Scanner scanner = new Scanner(System.in);
    Node head;
    Node tail;

    public TaskManager() {
        head = null;
        tail = null;
    }

    public void addTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        int priority;
        while (true) {
            System.out.print("Enter task priority (1-5): ");
            try {
                priority = scanner.nextInt();
                if (priority >= 1 && priority <= 5) {
                    break;
                } else {
                    System.out.println("Priority must be between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); 

        LocalDate dueDate;
        while (true) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            try {
                String dueDateStr = scanner.nextLine();
                dueDate = LocalDate.parse(dueDateStr);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        System.out.print("Enter task category: ");
        String category = scanner.nextLine();

        Task.TaskStatus status;
        while (true) {
            System.out.print("Enter task status (PENDING, IN_PROGRESS, COMPLETED): ");
            try {
                status = Task.TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(
                        "Invalid status. Please enter PENDING, IN_PROGRESS, or COMPLETED.");
            }
        }

        Task newTask = new Task(
                title,
                description,
                priority,
                dueDate,
                category,
                status);
        Node newNode = new Node(newTask);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        System.out.println("Task Added Successfully");
    }

    public void deleteTask(String titleToDelete) {
        Node current = head;

        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(titleToDelete)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Task '" + titleToDelete + "' has been deleted.");
                return;
            }
            current = current.next;
        }
        System.out.println("Task '" + titleToDelete + "' not found.");
    }

    public void listTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            System.out.println("-----------------------------------------------");
            current = current.next;
        }
    }

    public void editTask(String title) {
        boolean taskEdited = false;

        System.out.print("Enter new task title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new task description: ");
        String newDescription = scanner.nextLine();
        System.out.print("Enter new task priority (1-5): ");
        int newPriority = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter new due date (YYYY-MM-DD): ");
        LocalDate newDueDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter new task category: ");
        String newCategory = scanner.nextLine();
        System.out.print(
                "Enter new task status (PENDING, IN_PROGRESS, COMPLETED): ");
        Task.TaskStatus newStatus = Task.TaskStatus.valueOf(
                scanner.nextLine().toUpperCase());

        Node current = head;

        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                current.data = new Task(
                        newTitle,
                        newDescription,
                        newPriority,
                        newDueDate,
                        newCategory,
                        newStatus);
                System.out.println("Task Edited successfully.");
                taskEdited = true;
                break;
            }
            current = current.next;
        }
        if (!taskEdited) {
            System.out.println(
                    "Task with title '" + title + "' not found. Edit failed.");
        }
    }

    public void searchTasksByTitle(String title) {
        Node current = head;
        boolean found = false;

        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                System.out.println(current.data);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No tasks with the given title found.");
        }
    }

    public void showcaseMode() {
        String[] titles = {
                "Complete Java Project",
                "Play Chess",
                "Watch Anime",
                "Prepare for FEE Practicals",
                "Complete Journal",
                "Prepare for upcoming Star-Batch Phase 2",
        };

        String[] descriptions = {
                "Finish the Java programming project for the course.",
                "Enjoy chess with friends or online opponents.",
                "Relax and watch your favorite anime series or movies.",
                "Study and prepare for the upcoming Fundamentals of Electrical Engineering practicals.",
                "Complete the practical journal of FEE Subject.",
                "Get ready for the second phase of the Star-Batch program.",
        };
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            String title = titles[i];
            String description = descriptions[i];
            int priority = random.nextInt(5) + 1; 
            LocalDate dueDate = LocalDate.now().plusDays(i + 1);
            String category = "College Life";
            Task.TaskStatus status;

            if (i % 2 == 0) {
                status = Task.TaskStatus.COMPLETED;
            } else {
                status = Task.TaskStatus.PENDING;
            }

            Task newTask = new Task(
                    title,
                    description,
                    priority,
                    dueDate,
                    category,
                    status);
            Node newNode = new Node(newTask);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }
        }
        System.out.println("Showcase mode: Demo Tasks added.");
    }

    public void sortByPriority() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        boolean swapped;
        Node current;
        Node next = null;

        do {
            swapped = false;
            current = head;

            while (current.next != next) {
                if (current.data.getPriority() > current.next.data.getPriority()) {
                    Task temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
            next = current;
        } while (swapped);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        System.out.println();
        System.out.println("Welcome to the Task Manager!");

        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Search Tasks by Title");
            System.out.println("6. Sort By Priority");
            System.out.println("7. ShowCase Mode!");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.println("\nAdd a New Task:");
                        taskManager.addTask();
                        break;
                    case 2:
                        System.out.println("\nDelete a Task:");
                        System.out.print("Enter the Title of the task to delete: ");
                        String title = scanner.nextLine();
                        taskManager.deleteTask(title);
                        break;
                    case 3:
                        System.out.println("\nList of Tasks:");
                        taskManager.listTasks();
                        break;
                    case 4:
                        System.out.println("\nEdit a Task:");
                        System.out.print("Enter the Title of the task to edit: ");
                        String editTitle = scanner.nextLine();
                        taskManager.editTask(editTitle);
                        break;
                    case 5:
                        System.out.println("\nSearch for Tasks by Title:");
                        System.out.print("Enter the title to search for: ");
                        String searchTitle = scanner.nextLine();
                        taskManager.searchTasksByTitle(searchTitle);
                        break;
                    case 6:
                        System.out.println("\nSorting Tasks by Priority...");
                        taskManager.sortByPriority();
                        break;
                    case 7:
                        System.out.println("\nEntering Showcase Mode:");
                        taskManager.showcaseMode();
                        break;
                    case 8:
                        System.out.println("Exiting Task Manager. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); //
            }
        }
    }
}
