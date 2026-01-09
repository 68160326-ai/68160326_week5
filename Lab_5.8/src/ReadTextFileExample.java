import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ReadTextFileExample {

    public static void main(String[] args) {

        // สร้าง JFrame

        JFrame frame = new JFrame("Open Text File");
        frame.setSize(600, 400);
        frame.setTitle("68160326 Yodsawadee Tumsit n47");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ===== MENU BAR =====
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");

        JMenuItem itemNew = new JMenuItem("New");
        JMenuItem itemOpen = new JMenuItem("Open");
        JMenuItem itemSave = new JMenuItem("Save");
        JMenuItem itemSaveAs = new JMenuItem("Save As");
        JMenuItem itemExit = new JMenuItem("Exit");

        menuFile.add(itemNew);
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemSaveAs);
        menuFile.addSeparator();
        menuFile.add(itemExit);
        menuBar.add(menuFile);
        frame.setJMenuBar(menuBar);


        // ปุ่มเลือกไฟล์
        JButton btnOpen = new JButton("Open File .txt");


        // TextArea สำหรับแสดงข้อความ
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // ใส่ ScrollBar
        JScrollPane scrollPane = new JScrollPane(textArea);

        //frame.add(btnOpen, BorderLayout.NORTH);
        //frame.add(scrollPane, BorderLayout.CENTER);

        // เหตุการณ์เมื่อกดปุ่ม
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File file = fileChooser.getSelectedFile();

                    try {
                        BufferedReader reader = new BufferedReader(
                                new FileReader(file)
                        );

                        textArea.setText(""); // ล้างข้อความเก่า
                        String line;

                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }

                        reader.close();

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Cannot read file.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        // แสดงหน้าต่าง
        frame.setVisible(true);
    }
}

