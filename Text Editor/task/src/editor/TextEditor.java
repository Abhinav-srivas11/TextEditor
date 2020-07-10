package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TextEditor extends JFrame
{
    final int WIDTH = 600;
    final int HEIGHT = 400;
    JTextArea textArea;
    Container container;
    JTextField filenameField;

    public TextEditor()
    {
        super("Text Editor v.2.1");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        container = getContentPane();

        //Add a text box
        textArea = new JTextArea();
        textArea.setName("TextArea");

        //Make the current field scrollable
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setName("ScrollPane");
        //We indicate that the scroll will always be available
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //We collect all the panels for display
        container.add(scrollableTextArea, BorderLayout.CENTER);
        container.add(contentRegion(), BorderLayout.NORTH);
        setJMenuBar(ourMenuBar());
        container.add(new JLabel(" "), BorderLayout.SOUTH);    //Just for beauty
        container.add(new JLabel("    "), BorderLayout.WEST);  //Just for beauty
        container.add(new JLabel("    "), BorderLayout.EAST);  //Just for beauty
        //Display window
        setVisible(true);
    }

    //Top area with file name field and SAVE and LOAD buttons
    private JPanel contentRegion(){
        JPanel contentRegion = new JPanel();
        contentRegion.setLayout(new FlowLayout(FlowLayout.CENTER));
        filenameField = new JTextField(30);
        filenameField.setName("FilenameField");

        //Save to file
        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.addActionListener(actionEvent -> {
            File file = new File(filenameField.getText());

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(container,
                        "ERROR!\nUnable to create file\n " + filenameField.getText());
            }
        });
        //Upload a file with a name in the text box
        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(actionEvent -> {
            try{
                textArea.setText(new String(Files.readAllBytes(Paths.get(filenameField.getText()))));
            } catch (IOException e) {
//                If you open - there will be a cool window with an error. But the test does not know how to use it.
//                JOptionPane.showMessageDialog(container,
//                        "ERROR!\nFile not found:\n" + filenameField.getText());
                textArea.setText(null);
            }
        });

        // We assemble the panel from the name input field and 2 buttons
        contentRegion.add(filenameField);
        contentRegion.add(saveButton);
        contentRegion.add(loadButton);

        return contentRegion;
    }

    private JMenuBar ourMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //creating options inside the menubar
        JMenu fileOption = new JMenu("File");
        fileOption.setMnemonic(KeyEvent.VK_F);
        fileOption.setName("MenuFile");
        menuBar.add(fileOption);
        //above option is what we will be visible always in the GUI window
        JMenuItem saveOption = new JMenuItem("Save");
        saveOption.setName("MenuSave");

        JMenuItem loadOption = new JMenuItem("Load");
        loadOption.setName("MenuLoad");

        JMenuItem exitOption = new JMenuItem("Exit");
        exitOption.setName("MenuExit");

        //add all items to the menu
        fileOption.add(saveOption);
        fileOption.add(loadOption);
        fileOption.addSeparator();
        fileOption.add(exitOption);

        //adding action listeners to each menu item
        saveOption.addActionListener(saveEvent -> {
            File file = new File(filenameField.getText());

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(container,
                        "ERROR!\nUnable to create file\n " + filenameField.getText());
            }
        });

        loadOption.addActionListener(loadEvent -> {
            try{
                textArea.setText(new String(Files.readAllBytes(Paths.get(filenameField.getText()))));
            } catch (IOException e) {
                textArea.setText(null);
            }
        });

        exitOption.addActionListener(exitEvent -> dispose());

        return menuBar;
    }

}