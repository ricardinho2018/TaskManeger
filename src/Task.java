import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;  // Versão da serialização
    private static int idCounter = 0;
    private int id;
    private String name;
    private String dueDate;
    private boolean completed;

    public Task(String name, String dueDate) {
        this.id = ++idCounter;
        this.name = name;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return name + " - " + dueDate + (completed ? " (Concluída)" : " (Pendente)");
    }
}
