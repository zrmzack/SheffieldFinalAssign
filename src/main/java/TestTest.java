import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zack
 * @create 2019-12-14-11:53
 */
public class TestTest extends JFrame {
    Getinfo getinfo = new Getinfo();

    public TestTest() {
        setSize(400, 300);
        Container container = this.getContentPane();
        container.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container.setLayout(new BorderLayout());
        Button button = new Button("change");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getinfo.addprogress(1,100,);
            }
        });
        container.add(button);

    }

    public static void main(String[] args) {
        TestTest testTesnew = new TestTest();
        testTesnew.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testTesnew.setVisible(true);


    }
}
