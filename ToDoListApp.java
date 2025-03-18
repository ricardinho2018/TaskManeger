import java.util.Scanner;

public class ToDoListApp {
    private static TaskManager taskManager;

    public static void main(String[] args) {
        taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Exibir menu
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Editar tarefa");
            System.out.println("3. Excluir tarefa");
            System.out.println("4. Marcar tarefa como concluída");
            System.out.println("5. Exibir todas as tarefas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer de linha

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    editTask(scanner);
                    break;
                case 3:
                    deleteTask(scanner);  // Alterado de deleteTask para removeTask
                    break;
                case 4:
                    markTaskAsCompleted(scanner);
                    break;
                case 5:
                    showTasks();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (choice != 6);
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Digite o nome da tarefa: ");
        String name = scanner.nextLine();
        System.out.print("Digite a data de vencimento (formato: dd/MM/yyyy): ");
        String dueDate = scanner.nextLine();
        taskManager.addTask(name, dueDate);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    private static void editTask(Scanner scanner) {
        System.out.print("Digite o ID da tarefa a ser editada: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer de linha
        System.out.print("Digite o novo nome da tarefa: ");
        String newName = scanner.nextLine();
        System.out.print("Digite a nova data de vencimento (formato: dd/MM/yyyy): ");
        String newDueDate = scanner.nextLine();
        taskManager.editTask(id, newName, newDueDate);
        System.out.println("Tarefa editada com sucesso!");
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Digite o ID da tarefa a ser excluída: ");
        int id = scanner.nextInt();
        taskManager.removeTask(id);  // Alterado de deleteTask para removeTask
        System.out.println("Tarefa excluída com sucesso!");
    }

    private static void markTaskAsCompleted(Scanner scanner) {
        System.out.print("Digite o ID da tarefa a ser marcada como concluída: ");
        int id = scanner.nextInt();
        taskManager.markTaskCompleted(id);  // Certificando-se de usar o nome correto do método
        System.out.println("Tarefa marcada como concluída!");
    }

    private static void showTasks() {
        if (taskManager.getTasks().isEmpty()) {
            System.out.println("Nenhuma tarefa para exibir.");
        } else {
            System.out.println("\n--- Lista de Tarefas ---");
            for (Task task : taskManager.getTasks()) {
                System.out.println(task);
            }
        }
    }
}
