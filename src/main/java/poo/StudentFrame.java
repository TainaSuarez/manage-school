package poo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
public class StudentFrame extends JFrame{
    //interfaz del estudiante
    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JButton addButton;
    private JButton queryButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea resultArea;
    private StudentDAO studentDAO;

    public StudentFrame() {
        studentDAO = new StudentDAO();

        setTitle("Manejo de estudiante");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("ID eStudiante :");
        idLabel.setBounds(10, 10, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 10, 160, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(10, 40, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 40, 160, 25);
        add(nameField);

        JLabel addressLabel = new JLabel("Direccion:");
        addressLabel.setBounds(10, 70, 80, 25);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(100, 70, 160, 25);
        add(addressField);

        JLabel phoneLabel = new JLabel("Telefono:");
        phoneLabel.setBounds(10, 100, 80, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(100, 100, 160, 25);
        add(phoneField);

        addButton = new JButton("Agregar estudiante");
        addButton.setBounds(10, 130, 150, 25);
        add(addButton);

        queryButton = new JButton("Buscar estudiante");
        queryButton.setBounds(170, 130, 150, 25);
        add(queryButton);

        updateButton = new JButton("Actualizar estudiante");
        updateButton.setBounds(10, 160, 150, 25);
        add(updateButton);

        deleteButton = new JButton("Borrar estudiante");
        deleteButton.setBounds(170, 160, 150, 25);
        add(deleteButton);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 190, 460, 250);
        add(resultArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryStudents();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
    }

    private void addStudent() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();

        Student student = new Student();
        student.setName(name);
        student.setAddress(address);
        student.setPhone(phone);

        try {
            studentDAO.addStudent(student);
            JOptionPane.showMessageDialog(this, "Estudiante agregado con exito!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error agregar estudiante.");
        }
    }

    private void queryStudents() {
        try {
            List<Student> students = studentDAO.getAllStudents();
            StringBuilder result = new StringBuilder();
            for (Student student : students) {
                result.append("ID estudiante: ").append(student.getId()).append("\n");
                result.append("Nombre: ").append(student.getName()).append("\n");
                result.append("Direccion: ").append(student.getAddress()).append("\n");
                result.append("Telefono: ").append(student.getPhone()).append("\n\n");
            }
            resultArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error buscar estudiante.");
        }
    }

    private void updateStudent() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAddress(address);
        student.setPhone(phone);

        try {
            studentDAO.updateStudent(student);
            JOptionPane.showMessageDialog(this, "Estudiante actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar estudiante.");
        }
    }

    private void deleteStudent() {
        int id = Integer.parseInt(idField.getText());

        try {
            studentDAO.deleteStudent(id);
            JOptionPane.showMessageDialog(this, "Estudiante eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar estudiante.");
        }
    }
}

