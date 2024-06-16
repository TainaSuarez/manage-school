package poo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton studentButton;
    private JButton courseButton;
//hace la primera interfaz de escoger si quiero curso o alumno
    public MainFrame() {
        setTitle("Estudiante");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Inicializa los componentes
        studentButton = new JButton("Manejo de estudiante");
        studentButton.setBounds(50, 30, 200, 30);
        add(studentButton);

        courseButton = new JButton("Manejo de curso");
        courseButton.setBounds(50, 80, 200, 30);
        add(courseButton);

        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentFrame().setVisible(true);
            }
        });

        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourseFrame().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
