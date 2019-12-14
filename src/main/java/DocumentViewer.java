import jdk.internal.org.objectweb.asm.Handle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zack
 * @create 2019-12-05-2:15
 */
public class DocumentViewer extends JFrame implements ActionListener {
    //store title
    public ArrayList arrayListTitle;
    //store all text
    public ArrayList arrayListAlltext;
    //store main text
    public ArrayList arrayListmainText;
    private JButton load;
    private JButton showmaintext;
    private JButton statistics;
    public JTextField input;
    private JTextArea showTitle;
    private Getinfo getinfo;
    private static final String PATHDAFF = System.getProperty("user.dir") + "\\src\\main\\java\\daffodils.txt";
    private static final String PATHEARN = System.getProperty("user.dir") + "\\src\\main\\java\\Earnest.txt";
    private static final String PATHPRID = System.getProperty("user.dir") + "\\src\\main\\java\\PrideAndPrejudice.txt";
    private String Path = "";

    public DocumentViewer() {
        setSize(400, 300);
        Container container = this.getContentPane();
        container.setVisible(true);
        container.setLayout(new BorderLayout());


        load = new JButton("load");
        load.addActionListener(this);
        input = new JTextField();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));
        jPanel.add(input);
        jPanel.add(load);
        container.add(jPanel, BorderLayout.NORTH);

        showTitle = new JTextArea();
        container.add(showTitle, BorderLayout.CENTER);

        JPanel jPanel3 = new JPanel();
        showmaintext = new JButton("show");
        showmaintext.addActionListener(this);
        statistics = new JButton("alldoc");
        statistics.addActionListener(this);
        jPanel3.add(showmaintext);
        jPanel3.add(statistics);
        container.add(jPanel3, BorderLayout.SOUTH);

    }


    public static void main(String[] args) {
        DocumentViewer documentViewer = new DocumentViewer();
        documentViewer.setVisible(true);
        documentViewer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println(documentViewer.getFilePath());
    }

    private URL getFilePath() {
        return this.getClass().getClassLoader().getResource("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = input.getText();
        if (filename.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please input the title", "alert", JOptionPane.ERROR_MESSAGE);
        } else {
            Object source = e.getSource();
            if (((JButton) source).getText().equals("load")) {
                if (filename.equalsIgnoreCase("poem")) {
                    Path = PATHDAFF;
                    System.out.println(Path);
                } else if (filename.equalsIgnoreCase("play")) {
                    Path = PATHEARN;
                } else if (filename.equalsIgnoreCase("Novel")) {
                    Path = PATHPRID;
                } else {
                    Path = filename;
                }
                Getinfo getinfo = new Getinfo();
                arrayListTitle = getinfo.getAlltexttitle(Path);
                String title = getinfo.getTextString(arrayListTitle);
                showTitle.setText(title);
            } else if (((JButton) source).getText().equals("show")) {
                if (Path.equalsIgnoreCase(PATHDAFF) || Path.equalsIgnoreCase(PATHEARN)) {
                    Object[] options = {"OK", "CANCEL"};
                    JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    new ShowMainText();
                    load.setEnabled(false);
                } else {
                    Object[] options = {"OK", "CANCEL"};
                    JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    new ShowMainText();
                    load.setEnabled(false);
                }


            } else {
                if (Path.equalsIgnoreCase(PATHDAFF) || Path.equalsIgnoreCase(PATHEARN)) {
                    Object[] options = {"OK", "CANCEL"};
                    JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    new showalldoc();
                    showmaintext.setEnabled(false);
                } else {
                    Object[] options = {"OK", "CANCEL"};
                    JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    new showalldoc();
                    showmaintext.setEnabled(false);
                }
            }
        }

    }

    class ShowMainText extends JFrame {
        private JTextArea showMaintext;
        private JScrollPane jScrollPane;
        private ArrayList arrayListTitle;
        private ArrayList arrayListAlltext;
        private ArrayList arrayListmainText;

        public ShowMainText() {
            showMaintext = new JTextArea();
            showMaintext.setEnabled(false);
            getinfo = new Getinfo();
            String mainText = input.getText();
            //Poem  daffodils
            //Play  Earnest
            //Novel PrideAndPrejudice
            String Path = "";
            if (mainText.equalsIgnoreCase("poem")) {
                Path = PATHDAFF;

            } else if (mainText.equalsIgnoreCase("play")) {
                Path = PATHEARN;
            } else if (mainText.equalsIgnoreCase("Novel")) {
                Path = PATHPRID;

            } else {
                Path = mainText;
            }
            arrayListTitle = getinfo.getAlltexttitle(Path);
            arrayListAlltext = getinfo.getAlldoctext(Path);
            arrayListmainText = getinfo.getmaintext(arrayListTitle, arrayListAlltext);
            setSize(400, 300);
            Container container = this.getContentPane();
            jScrollPane = new JScrollPane();
            String TextString = getinfo.getTextString(arrayListmainText);
            showMaintext.setText(TextString);
            jScrollPane.add(showMaintext);
            jScrollPane.setViewportView(showMaintext);
            container.add(jScrollPane, BorderLayout.CENTER);
            this.setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }


    }

    class showalldoc extends JFrame {
        private ArrayList arrayListTitle;
        private ArrayList arrayListAlltext;
        private ArrayList arrayListmainText;
        private JTextArea showalldoc;
        private JTextArea tenWords;
        private HashMap hashMap = new HashMap();

        public showalldoc() {
            Container container = this.getContentPane();
            container.setLayout(new BorderLayout());
            showalldoc = new JTextArea();
            tenWords = new JTextArea();
            tenWords.setSize(400, 200);
            setSize(400, 300);
            setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            String temp = input.getText();
            String Path = "";
            if (temp.equalsIgnoreCase("poem")) {
                Path = PATHDAFF;
            } else if (temp.equalsIgnoreCase("play")) {
                Path = PATHEARN;
            } else if (temp.equalsIgnoreCase("Novel")) {
                Path = PATHPRID;
            } else {
                Path = temp;
            }


            Getinfo getinfo = new Getinfo();
            arrayListTitle = getinfo.getAlltexttitle(Path);
            arrayListAlltext = getinfo.getAlldoctext(Path);
            arrayListmainText = getinfo.getmaintext(arrayListTitle, arrayListAlltext);
            String tempMainText = getinfo.getTextString(arrayListmainText);
            //System.out.println(tempMainText);

            HashMap hashMap = getinfo.getmap(tempMainText);
            int number = getinfo.getTemptextStringNumber(arrayListmainText);
            showalldoc.setText(String.valueOf(number));
            showalldoc.setEnabled(false);
            //showalldoc.setFont();
            List list = getinfo.getTopTenWords(hashMap);
            tenWords.setText(getinfo.getTopWordsString(list));
            tenWords.setEnabled(false);
            container.add(showalldoc, BorderLayout.SOUTH);
            container.add(tenWords, BorderLayout.CENTER);


        }
    }


}
