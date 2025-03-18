import java.io.*;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private static final String FILE_NAME = "tasks.dat"; // Nome do arquivo onde serão salvas as tarefas

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasksFromFile();  // Carregar tarefas ao iniciar
    }

    public void addTask(String name, String dueDate) {
        Task newTask = new Task(name, dueDate);
        tasks.add(newTask);
        saveTasksToFile();  // Salvar após adicionar
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasksToFile();  // Salvar após remover
        }
    }

    public void editTask(int index, String newName, String newDueDate) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setName(newName);
            task.setDueDate(newDueDate);
            saveTasksToFile();  // Salvar após editar
        }
    }

    public void markTaskCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
            saveTasksToFile();  // Salvar após marcar como concluída
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Salvar tarefas no arquivo
    private void saveTasksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.err.println("Erro ao salvar as tarefas: " + e.getMessage());
        }
    }

    // Carregar tarefas do arquivo
    @SuppressWarnings("unchecked")
    private void loadTasksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (ArrayList<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo de tarefas encontrado. Criando um novo.");
            tasks = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar as tarefas: " + e.getMessage());
            tasks = new ArrayList<>();
        }
    }
}
