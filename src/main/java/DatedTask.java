public class DatedTask extends Task {
    private String date;

    public DatedTask(String task, String date) {
        super(task);
        this.date = date;
    }

    public DatedTask() {
        super();
        this.date = null;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
