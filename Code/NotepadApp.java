package notepadapp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NotepadApp extends JFrame implements ActionListener {

    JMenuBar menubar;
    JMenu File, Edit, Help;
    JMenuItem newfile, openfile, savefile, printfile, exit, cut, copy, paste, selectall, about;
    JTextArea textarea;

    NotepadApp() {
        setTitle("Notepad Application");
        setBounds(100, 100, 800, 600);

        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
        setIconImage(icon.getImage());

        menubar = new JMenuBar();
        setJMenuBar(menubar);

        File = new JMenu("File");
        menubar.add(File);

        Edit = new JMenu("Edit");
        menubar.add(Edit);

        Help = new JMenu("Help");
        menubar.add(Help);

        newfile = new JMenuItem("New");
        File.add(newfile);

        openfile = new JMenuItem("Open");
        File.add(openfile);

        savefile = new JMenuItem("Save");
        File.add(savefile);

        printfile = new JMenuItem("Print");
        File.add(printfile);

        exit = new JMenuItem("Exit");
        File.add(exit);

        cut = new JMenuItem("Cut");
        Edit.add(cut);

        copy = new JMenuItem("Copy");
        Edit.add(copy);

        paste = new JMenuItem("Paste");
        Edit.add(paste);

        selectall = new JMenuItem("Select All");
        Edit.add(selectall);

        about = new JMenuItem("About");
        Help.add(about);

        textarea = new JTextArea();
        textarea.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        JScrollPane scrollpane = new JScrollPane(textarea);
        add(scrollpane);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        printfile.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        about.addActionListener(this);

        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        printfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            textarea.setText(null);
        } else if (e.getActionCommand().equalsIgnoreCase("Open")) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textfilter);

            int action = filechooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                    textarea.read(reader, this);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textfilter);

            int action = filechooser.showSaveDialog(this);
            if (action == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = filechooser.getSelectedFile();
                    if (!file.getName().endsWith(".txt")) {
                        file = new File(file.getParentFile(), file.getName() + ".txt");
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textarea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Print")) {
            try {
                textarea.print();
            } catch (PrinterException ex) {
                Logger.getLogger(NotepadApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
            textarea.cut();
        } else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
            textarea.copy();
        } else if (e.getActionCommand().equalsIgnoreCase("Paste")) {
            textarea.paste();
        } else if (e.getActionCommand().equalsIgnoreCase("Select All")) {
            textarea.selectAll();
        } else if (e.getActionCommand().equalsIgnoreCase("About")) {
            new About();
            setVisible(true);
        }
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new NotepadApp();
    }

}
