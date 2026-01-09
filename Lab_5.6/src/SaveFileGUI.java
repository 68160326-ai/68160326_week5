import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SaveFileGUI {

    static File currentFile = null;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Mini Notepad");
        frame.setSize(600, 400);
        frame.setTitle("68160326 Yodsawadee Tumsit n47");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ===== MENU BAR =====R
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

        // ===== TEXT AREA =====
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // ===== NEW =====
        itemNew.addActionListener(e -> {
            textArea.setText("");
            currentFile = null;
            frame.setTitle("Mini Notepad - New File");
        });

        // ===== OPEN =====
        itemOpen.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                currentFile = chooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                    textArea.setText("");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    frame.setTitle("Mini Notepad - " + currentFile.getName());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Cannot open file");
                }
            }
        });

        // ===== SAVE =====
        itemSave.addActionListener(e -> {
            if (currentFile == null) {
                itemSaveAs.doClick();
            } else {
                saveFile(currentFile, textArea, frame);
            }
        });

        // ===== SAVE AS =====
        itemSaveAs.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                currentFile = chooser.getSelectedFile();
                saveFile(currentFile, textArea, frame);
                frame.setTitle("Mini Notepad - " + currentFile.getName());
            }
        });

        // ===== EXIT =====
        itemExit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    static void saveFile(File file, JTextArea textArea, JFrame frame) {
        try (FileWriter writer = new FileWriter(file + ".txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(frame, "Save successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot save file");
        }
    }
}
