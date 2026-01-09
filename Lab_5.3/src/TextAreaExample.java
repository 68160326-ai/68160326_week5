import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class TextAreaExample {

    public static void main(String[] args) {

        // สร้าง Frame
        JFrame frame = new JFrame("Progam with JTextArea");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // สร้าง TextArea
        JTextArea textArea = new JTextArea(8, 30);
        textArea.setLineWrap(true);       // ตัดบรรทัดอัตโนมัติ
        textArea.setWrapStyleWord(true);  // ตัดตามคำ

        // ใส่ ScrollBar ให้ TextArea
        JScrollPane scrollPane2 = new JScrollPane(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // สร้างปุ่ม
        JButton button2 = new JButton("Save");
        JButton button = new JButton("Show message");


        // เมื่อกดปุ่ม
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try{ FileWriter writer = new FileWriter("note.txt", true);
                    writer.write(textArea.getText());
                   writer.close();
                   JOptionPane.showMessageDialog(frame, " Save to note.txt Successful");
               } catch (IOException ex) {
                   JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
               }
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                JOptionPane.showMessageDialog(frame, text,
                        "Your message: ", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // จัด Layout
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane2, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(button2, BorderLayout.NORTH);
        frame.add(button, BorderLayout.SOUTH);

        // แสดงหน้าจอ
        frame.setVisible(true);
    }
}

