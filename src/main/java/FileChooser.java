/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//package components;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
/**
 * interface to retrieve filepath of directory.
 * @author Erik Cole(modifications and additions to base code)
 * @version 2.0
 */
interface FileGet {
    /**
     * method to return the file.
     * @return File object selected
     * @param f the file that is selected
     *
     */
    public File retFile(File f);
}
/**
 * FileChooserDemo.java uses these files.
 *   images/Open16.gif
 *   images/Save16.gif
 *   @author Oracle
 *   @version 1.0
 */
public class FileChooser extends JPanel
        implements ActionListener {
    static private final String NL = "\n";
    JButton openButton; 
    JButton saveButton;
    JTextArea log;
    JFileChooser fc;
    File file;
    FileGet fileget;
    /**
     * Gui class.
     * @param fg interface to get the file directory
     */
    public FileChooser(FileGet fg) {
        super(new BorderLayout());
        fileget = fg;
        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Open a File...",
                createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save a File...",
                createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();
         //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    /**
     * Responds to Gui button press.
     * @param e actionevent listener
     */
    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                //This is where a real application would open the file.
                
                //System.out.println(file.getPath());
                fileget.retFile(file);
                log.append("Opening: " + file.getName() + "." + NL);
            } 
            else {
                log.append("Open command cancelled by user." + NL);
            }
            log.setCaretPosition(log.getDocument().getLength());

            //Handle save button action.
        } 
        else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
               // log.append("Saving: " + file.getName() + "." + Newline);
            } 
            else {
                log.append("save");
               // log.append("Save command cancelled by user." + Newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. 
     * @param path file path for image location
     * @return returns the image icon object
     * */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } 
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     * @param fg is the interface 
     */
    public static void createAndShowGUI(FileGet fg) {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new FileChooser(fg));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Method to get the file path.
     * @return string version of the file path
     */
    public String getFilePath() {
        return file.getPath();
    }
    /** 
     * Method to set the file.
     * @param f is a file to set as if it was picked by the gui
     */
    public void setFile(File f) {
        file = f;
    }

//    public static void main(String[] args) {
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                //Turn off metal's use of bold fonts
//                UIManager.put("swing.boldMetal", Boolean.FALSE);
//                createAndShowGUI();
//            }
//        });
//    }
}

