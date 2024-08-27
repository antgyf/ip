import javax.lang.model.type.ErrorType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PHamBot {
    private static final String line = "____________________________________________________________\n";
    private static TaskList tasks = new TaskList();
    private static final String[] UserGreetings = {"Hello", "Hi", "What's up"};

    public static void main(String[] args) {
        Greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                SayGoodbye();
                break;
            }

            if (input.equals("list")) {
                ListTasks();
            }

            if (input.contains("delete")) {
                try {
                    int index = checkIndexInput(input) - 1;
                    if (index < 0) {
                        OutlineMessage("Sorry boss, you need to give me a positive number, I not good with negatives.");
                    }
                    else {
                        printRemoveTask(index);
                        tasks.deleteTask(index);
                    }
                } catch (MissingIndexException e) {
                    OutlineMessage("Bro, which task you want me to delete sia...");
                } catch (IndexOutOfBoundsException e) {
                    OutlineMessage("Laoban, you don't even have that many task!");
                }
            }

            if (input.contains("todo")) {
                try {
                    ToDo task = checkToDoInput(input, new ToDo());
                    tasks.addTask(task);
                    OutlineMessage("Added: " + task.toString());
                } catch (MissingTaskException e) {
                    OutlineMessage("You gotta give me a task man!");
                }
            }

            if (input.contains("deadline")) {
                try {
                    DatedTask task = checkDatedTaskInput(input, new Deadline());
                    tasks.addTask(task);
                    OutlineMessage("Added: " + task.toString());
                } catch (MissingTaskException e) {
                    OutlineMessage("You gotta give me a task man!");
                } catch (MissingDateException e) {
                    OutlineMessage("You haven't added a date for the task!");
                } catch (MissingDividerException e) {
                    OutlineMessage("You missed out a slash to separate the task and date!");
                }
            }

            if (input.contains("event")) {
                try {
                    DatedTask task = checkDatedTaskInput(input, new Event());
                    tasks.addTask(task);
                    OutlineMessage("Added: " + task.toString());
                } catch (MissingTaskException e) {
                    OutlineMessage("You gotta give me a task man!");
                } catch (MissingDateException e) {
                    OutlineMessage("You haven't added a date for the task!");
                } catch (MissingDividerException e) {
                    OutlineMessage("You missed out a slash to separate the task and date!");
                }
            }

            if (input.contains("unmark")) {
                unmark(Integer.parseInt(input.substring(7)));
            } else if (input.contains("mark")) {
                mark(Integer.parseInt(input.substring(5)));
            }
            else {
                for (String userGreeting : UserGreetings) {
                    if (input.contains(userGreeting)) {
                        OutlineMessage("Hi! How can I help you?");
                        break;
                    }
                }
            }


        }
    }

    private static void OutlineMessage(String msg) {
        System.out.println(line + msg + "\n" + line);
    }

    public static void Greet() {
        String greeting = "Hi! I'm PHamBot\nHappy to be of service to you today!";
        OutlineMessage(greeting);
    }

    public static void SayGoodbye() {
        String goodbye = "Hope I was able to help\nGoodbye!";
        OutlineMessage(goodbye);
    }

    public static String addToDo(String task) {
        tasks.addTask(new ToDo(task));
        return "Added:\n" + task;
    }

    public static String addDeadline(String task, String deadline) {
        tasks.addTask(new Deadline(task, deadline));
        return "Added:\n" + task;
    }

    public static String addEvent(String task, String date) {
        tasks.addTask(new Event(task, date));
        return "Added:\n" + task;
    }

    public static void ListTasks() {
        if (tasks.taskCount() == 0) {
            OutlineMessage("You're free! There aren't any tasks currently.");
            return;
        }
        OutlineMessage(tasks.toString());
    }

    public static void mark(int index) {
        int i = index - 1;
        tasks.markTask(i);
        OutlineMessage("I've marked the following task as done!\n" + tasks.getTask(i));
    }

    public static void unmark(int index) {
        int i = index - 1;
        tasks.unmarkTask(i);
        OutlineMessage("I've marked the following task as not done.\n" + tasks.getTask(i));
    }


    public static void printRemoveTask(int index) {
        OutlineMessage("On it boss. I've removed this task:\n" + tasks.getTask(index).toString());
    }

    public static ToDo checkToDoInput(String input, ToDo toDo) throws MissingTaskException {
        String[] temp = input.split(" ", 2);
        if (temp.length < 2) {
            throw new MissingTaskException("Missing a task to add!");
        }
        else {
            toDo.setTaskName(temp[1]);
            return toDo;
        }
    }

    public static DatedTask checkDatedTaskInput(String input, DatedTask task)
            throws MissingTaskException, MissingDateException, MissingDividerException {
        //Split the input on the slash
        String[] temp = input.split("/", 2);

        if (temp.length < 2) {
            throw new MissingDividerException("You're missing a slash between your task and date!");
        }

        String[] dateTime = getDateTime(temp[1]);
        //remove the task type
        String[] temp2 = temp[0].trim().split(" ", 2);

        if (temp2.length < 2) {
            throw new MissingTaskException("Missing a task to add!");
        }

        task.setTaskName(temp2[1].trim());
        task.setDate(LocalDate.parse(dateTime[1].trim()));
        if (dateTime.length > 2) {
            task.setTime(LocalTime.parse(dateTime[2]));
        }
        return task;
    }

    private static String[] getDateTime(String input) throws MissingTaskException, MissingDividerException, MissingDateException {
        String[] dateTime = input.trim().split(" ", 3);

        if (dateTime.length < 2) {
            throw new MissingDateException("Missing a date for your task!");
        }

        return dateTime;
    }

    public static int checkIndexInput(String input) throws MissingIndexException, IndexOutOfBoundsException {
        String[] temp = input.split(" ", 2);
        if (temp.length < 2 || Objects.equals(temp[1], "")) {
            throw new MissingIndexException("There is no list index!");
        }
        else {
            int index = Integer.parseInt(temp[1]);
            if (index > tasks.taskCount()) {
                throw new IndexOutOfBoundsException("There aren't even that many task!");
            }
            return index;
        }
    }

}
