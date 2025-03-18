import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ToDoListAppGUI {
    private static TaskManager taskManager;

    public static void main(String[] args) {
        taskManager = new TaskManager();
        JFrame frame = new JFrame("To-Do List App");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel de entrada de tarefas
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        JLabel taskNameLabel = new JLabel("Nome da Tarefa:");
        JTextField taskNameField = new JTextField();
        JLabel dueDateLabel = new JLabel("Data de Vencimento:");
        JTextField dueDateField = new JTextField();

        inputPanel.add(taskNameLabel);
        inputPanel.add(taskNameField);
        inputPanel.add(dueDateLabel);
        inputPanel.add(dueDateField);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Adicionar Tarefa");
        JButton editButton = new JButton("Editar Tarefa");
        JButton deleteButton = new JButton("Excluir Tarefa");
        JButton markCompletedButton = new JButton("Marcar Concluída");
        JButton showButton = new JButton("Exibir Tarefas");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(markCompletedButton);
        buttonPanel.add(showButton);

        // JList para exibir as tarefas
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane taskScrollPane = new JScrollPane(taskList);

        // Adicionar Painéis ao Frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(taskScrollPane, BorderLayout.SOUTH);

        // Exibir a janela
        frame.setVisible(true);

        // Ação para adicionar tarefa
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = taskNameField.getText();
                String dueDate = dueDateField.getText();
                taskManager.addTask(name, dueDate);
                taskNameField.setText("");
                dueDateField.setText("");
                JOptionPane.showMessageDialog(frame, "Tarefa adicionada com sucesso!");
            }
        });

        // Ação para editar tarefa
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = taskNameField.getText();
                String dueDate = dueDateField.getText();
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Task selectedTask = taskManager.getTasks().get(selectedIndex);
                    taskManager.editTask(selectedTask.getId(), name, dueDate);
                    taskListModel.set(selectedIndex, selectedTask.toString());
                    JOptionPane.showMessageDialog(frame, "Tarefa editada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para editar.");
                }
            }
        });

        // Ação para excluir tarefa
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskManager.removeTask(selectedIndex);
                    taskListModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(frame, "Tarefa excluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para excluir.");
                }
            }
        });

        // Ação para marcar tarefa como concluída
        markCompletedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskManager.markTaskCompleted(selectedIndex);
                    JOptionPane.showMessageDialog(frame, "Tarefa marcada como concluída!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma tarefa para marcar como concluída.");
                }
            }
        });

        // Ação para exibir tarefas
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Task> tasks = taskManager.getTasks();
                taskListModel.clear();
                for (Task task : tasks) {
                    taskListModel.addElement(task.toString());
                }
            }
        });
    }
}
