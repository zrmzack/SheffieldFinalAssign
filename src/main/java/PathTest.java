import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PathTest extends JFrame {
    public PathTest() {
        this.setTitle("进度条的使用");
        setSize(150, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();

        final JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        Button button = new Button("OK");
        button.setEnabled(false);
        container.add(progressBar, BorderLayout.NORTH);
        container.add(button, BorderLayout.CENTER);
        new Thread() {
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(10);
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
                // TODO Auto-generated method stub
                //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setVisible(false);
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        PathTest example = new PathTest();
    }
}
