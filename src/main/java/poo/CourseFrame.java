package poo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
public class CourseFrame extends JFrame {
    //este archivo es la interfaz para agregar cursos
    private JTextField idField;
    private JTextField courseNameField;
    private JTextField courseDescriptionField;
    private JButton addButton;
    private JButton queryButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea resultArea;
    private CourseDAO courseDAO;

    public CourseFrame() {
        courseDAO = new CourseDAO();

        setTitle("Administrar cursos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("Curso ID:");
        idLabel.setBounds(10, 10, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 10, 160, 25);
        add(idField);

        JLabel courseNameLabel = new JLabel("Nombre del curso:");
        courseNameLabel.setBounds(10, 40, 80, 25);
        add(courseNameLabel);

        courseNameField = new JTextField();
        courseNameField.setBounds(100, 40, 160, 25);
        add(courseNameField);

        JLabel courseDescriptionLabel = new JLabel("Descripcion del curso:");
        courseDescriptionLabel.setBounds(10, 70, 80, 25);
        add(courseDescriptionLabel);

        courseDescriptionField = new JTextField();
        courseDescriptionField.setBounds(100, 70, 160, 25);
        add(courseDescriptionField);

        addButton = new JButton("Agregar Curso");
        addButton.setBounds(10, 100, 150, 25);
        add(addButton);

        queryButton = new JButton("Consultar curso");
        queryButton.setBounds(170, 100, 150, 25);
        add(queryButton);

        updateButton = new JButton("Actualizar Curso");
        updateButton.setBounds(10, 130, 150, 25);
        add(updateButton);

        deleteButton = new JButton("Borrar curso");
        deleteButton.setBounds(170, 130, 150, 25);
        add(deleteButton);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 160, 460, 250);
        add(resultArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryCourses();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourse();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCourse();
            }
        });
    }

    private void addCourse() {
        String courseName = courseNameField.getText();
        String courseDescription = courseDescriptionField.getText();

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);

        try {
            courseDAO.addCourse(course);
            JOptionPane.showMessageDialog(this, "Curso agregado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el curso.");
        }
    }

    private void queryCourses() {
        try {
            List<Course> courses = courseDAO.getAllCourses();
            StringBuilder result = new StringBuilder();
            for (Course course : courses) {
                result.append("ID: ").append(course.getId()).append("\n");
                result.append("Course Name: ").append(course.getCourseName()).append("\n");
                result.append("Course Description: ").append(course.getCourseDescription()).append("\n\n");
            }
            resultArea.setText(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al consultar cursos.");
        }
    }

    private void updateCourse() {
        int id = Integer.parseInt(idField.getText());
        String courseName = courseNameField.getText();
        String courseDescription = courseDescriptionField.getText();

        Course course = new Course();
        course.setId(id);
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);

        try {
            courseDAO.updateCourse(course);
            JOptionPane.showMessageDialog(this, "Curso actualizado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el curso.");
        }
    }

    private void deleteCourse() {
        int id = Integer.parseInt(idField.getText());

        try {
            courseDAO.deleteCourse(id);
            JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el curso.");
        }
    }
}


