import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.org.objectweb.asm.Handle;
import sun.applet.Main;

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
    private static String MainTextButton = "show";
    private static String OverStatsButton = "alldoc";
    private int timeused = 10;

    public DocumentViewer() {
        setSize(400, 300);
        Container container = this.getContentPane();
        container.setVisible(true);
        container.setLayout(new BorderLayout());
        System.out.println(PATHDAFF);

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
        showmaintext = new JButton(MainTextButton);
        showmaintext.addActionListener(this);
        statistics = new JButton(OverStatsButton);
        statistics.addActionListener(this);
        jPanel3.add(showmaintext);
        jPanel3.add(statistics);
        container.add(jPanel3, BorderLayout.SOUTH);

    }


    public static void main(String[] args) {
        DocumentViewer documentViewer = new DocumentViewer();
        documentViewer.setVisible(true);
        documentViewer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        drawProgress drawProgress = new drawProgress();
        Getinfo getinfo = new Getinfo();
        String filename = input.getText();
        if (filename.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please input the title", "alert", JOptionPane.ERROR_MESSAGE);
        } else {
            Object source = e.getSource();
            if (((JButton) source).getText().equals("load")) {
                if (filename.equalsIgnoreCase("poem") || filename.equalsIgnoreCase("daffodils")) {
                    Path = PATHDAFF;
                    System.out.println(Path);
                } else if (filename.equalsIgnoreCase("play") || filename.equalsIgnoreCase("Earnest")) {
                    Path = PATHEARN;
                } else if (filename.equalsIgnoreCase("Novel") || filename.equalsIgnoreCase("PrideAndPrejudice")) {
                    Path = PATHPRID;
                } else {
                    Path = filename;
                }
                arrayListTitle = getinfo.getAlltexttitle(Path);
                String title = getinfo.getTextString(arrayListTitle);
                showTitle.setText(title);
            } else if (((JButton) source).getText().equals("show")) {
                drawProgress.addprogress(1, 100, Path, MainTextButton);

            } else {
                drawProgress.addprogress(1, 100, Path, OverStatsButton);
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
            Path = getinfo.titleUserInput(mainText);
            try {
                arrayListTitle = getinfo.getAlltexttitle(Path);
                arrayListAlltext = getinfo.getAlldoctext(Path);
                arrayListmainText = getinfo.getmaintext(arrayListTitle, arrayListAlltext);
            } catch (Exception e) {
                System.out.println("File Load Error");
            }
            setSize(400, 300);
            Container container = this.getContentPane();
            jScrollPane = new JScrollPane();
            String TextString = getinfo.getTextString(arrayListmainText);
            showMaintext.setText(TextString);
            jScrollPane.add(showMaintext);
            jScrollPane.setViewportView(showMaintext);
            container.add(jScrollPane, BorderLayout.CENTER);


            setVisible(true);


//            if (mainText.equalsIgnoreCase("poem")) {
//                timeused = 5000;
//                new Thread(() -> {
//                    try {
//                        Thread.sleep(timeused);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }


            this.setDefaultCloseOperation(2);
        }


    }

    class showalldoc extends JFrame {
        private ArrayList arrayListTitle;
        private ArrayList arrayListAlltext;
        private ArrayList arrayListmainText;
        private JTextArea showalldoc;
        private JTextArea tenWords;
        private HashMap hashMap = new HashMap();
        Getinfo getinfo = new Getinfo();

        public showalldoc() {
            Container container = this.getContentPane();
            container.setLayout(new BorderLayout());
            showalldoc = new JTextArea();
            tenWords = new JTextArea();
            tenWords.setSize(400, 200);
            setSize(400, 300);
            setVisible(true);
            this.setDefaultCloseOperation(2);
            String temp = input.getText();
            Path = getinfo.titleUserInput(temp);


            Getinfo getinfo = new Getinfo();
            try {
                arrayListTitle = getinfo.getAlltexttitle(Path);
                arrayListAlltext = getinfo.getAlldoctext(Path);
                arrayListmainText = getinfo.getmaintext(arrayListTitle, arrayListAlltext);
            } catch (Exception e) {
                System.out.println("File Load Error");
            }
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

    class drawProgress extends JFrame {
        Button button;
        int time = 100;

        public void addprogress(int start, int end, String title, String buttonName) {
            //setSize(200, 200);
            setBounds(500, 500, 200, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            Container container = this.getContentPane();
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);

            button = new Button("OK");
            button.setSize(30, 20);
            button.setEnabled(false);
            container.add(progressBar, BorderLayout.NORTH);
            container.add(button, BorderLayout.CENTER);
            if (title.equalsIgnoreCase(PATHDAFF)) {
                time = 10;
            } else if (title.equalsIgnoreCase(PATHEARN)) {
                time = 20;
            } else {
                time = 10;
            }
            new Thread() {
                public void run() {
                    for (int i = start; i <= end; i++) {
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressBar.setValue(i);
                        progressBar.setString("loading");
                    }
                    progressBar.setString("loaded");
                    button.setEnabled(true);

                }
            }.start();

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    if (buttonName.equalsIgnoreCase(MainTextButton)) {
                        if (title.equalsIgnoreCase(PATHEARN) || title.equalsIgnoreCase(PATHDAFF)) {
                            new ShowMainText();
                        } else if (title.equalsIgnoreCase(PATHPRID)) {
                            JOptionPane.showMessageDialog(null, "Need More Time To Analyse text, Please Wait!");
                            new ShowMainText();
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Path Of Text", "error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        //|| title.equalsIgnoreCase(PATHDAFF)
                        if (title.equalsIgnoreCase(PATHDAFF)) {
                            new showalldoc();
                        } else if (title.equalsIgnoreCase(PATHPRID)) {
                            JOptionPane.showMessageDialog(null, "Need More Time To Analyse text, Please Wait!");
                            new showalldoc();
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Path Of Text", "error", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
            });
            setVisible(true);
        }
    }


}




