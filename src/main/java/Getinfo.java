import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

/**
 * @author zack
 * @create 2019-12-05-4:52
 */
public class Getinfo extends JFrame {
    private static final String PATHDAFF = System.getProperty("user.dir") + "\\src\\main\\java\\daffodils.txt";
    private static final String PATHEARN = System.getProperty("user.dir") + "\\src\\main\\java\\Earnest.txt";
    private static final String PATHPRID = System.getProperty("user.dir") + "\\src\\main\\java\\PrideAndPrejudice.txt";
    private Button button;

    public ArrayList getAlldoctext(String pathname) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            File file = new File(pathname);
            InputStreamReader inputStreamReader = new InputStreamReader((new FileInputStream(file)));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;

            while ((str = bufferedReader.readLine()) != null) {
                arrayList.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Faild load Text", "error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Check Path Of Text", "error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Check Path Of Text");
        }
        return arrayList;
    }

    public ArrayList getAlltexttitle(String pathname) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            File file = new File(pathname);
            InputStreamReader inputStreamReader = new InputStreamReader((new FileInputStream(file)));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains("Text:")) {
                    break;
                } else {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Faild load Text", "error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Check Path Of Text", "error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Check Path Of Text");
        }
        return arrayList;
    }

    //获取文章的主要内容
    public ArrayList getmaintext(ArrayList arrayListtitle, ArrayList arrayListAlltext) {
        for (int x = 0; x < arrayListtitle.size(); x++) {
            for (int y = 0; y < arrayListtitle.size(); y++) {
                if (arrayListtitle.get(x).equals(arrayListAlltext.get(y))) {
                    arrayListAlltext.remove(y);
                }
            }
        }
        return arrayListAlltext;
    }


    //将东西转化成字符串
    public int getTemptextStringNumber(ArrayList arrayList) {
        String tempstring = "";
        String tempnumber[];
        for (int x = 0; x < arrayList.size(); x++) {
            tempstring += arrayList.get(x);
            tempstring = tempstring.toLowerCase();
            tempstring = tempstring.replaceAll("\\pP", "");
            tempstring += " ";
        }
        tempnumber = tempstring.split(" ");

        return tempnumber.length;
    }

    int number = 0;

    //把文字放到 hashmap
    public HashMap<String, Integer> getmap(String x) {
        //x = x.toLowerCase();
        HashMap<String, Integer> hashMap = new HashMap();
        String[] temps = x.split(" ");
        for (int i = 0; i < temps.length; i++) {
            if (!hashMap.containsKey(temps[i])) {
                hashMap.put(temps[i], 1);
            } else {
                hashMap.put(temps[i], hashMap.get(temps[i]) + 1);
            }
        }
        hashMap.remove("");
        return hashMap;
    }

    //统计字数
    public int getNumberOfText(HashMap<String, Integer> hashMap) {
        for (String tempx : hashMap.keySet()) {
            number += hashMap.get(tempx);
        }
        System.out.println(number);
        return number;
    }

    //拿到前10个list
    public List getTopTenWords(HashMap hashMap) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        //System.out.println(list);
        List listwords = new LinkedList<String>();
        for (int x = 0; x <= 9; x++) {
            listwords.add(list.get(x));
        }
        System.out.println(listwords);
        return listwords;
    }

    public String getTextString(ArrayList arrayList) {
        String temp = "";
        for (Object x : arrayList) {
            x += "\n";
            temp += x;
        }
        return temp;
    }

    public String getTopWordsString(List list) {
        String temp = "";
        for (int x = 0; x < list.size(); x++) {
            temp += list.get(x);
            temp += "\n";
        }
        return temp;
    }

    public String titleUserInput(String title) {
        String Path = "";
        if (title.equalsIgnoreCase("poem") || title.equalsIgnoreCase("daffodils")) {
            Path = PATHDAFF;
            return Path;
        } else if (title.equalsIgnoreCase("play") || title.equalsIgnoreCase("Earnest")) {
            Path = PATHEARN;
            return Path;
        } else if (title.equalsIgnoreCase("Novel") || title.equalsIgnoreCase("PrideAndPrejudice")) {
            Path = PATHPRID;
            return Path;
        } else {
            Path = title;
            return Path;
        }
    }


//    public void addprogress(int start, int end, String title) {
//        setVisible(true);
//        setSize(200, 200);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        Container container = this.getContentPane();
//        JProgressBar progressBar = new JProgressBar();
//        progressBar.setStringPainted(true);
//        button = new Button("OK");
//        button.setSize(30, 20);
//        button.setEnabled(false);
//        container.add(progressBar, BorderLayout.NORTH);
//        container.add(button, BorderLayout.CENTER);
//
//        new Thread() {
//            public void run() {
//                for (int i = start; i <= end; i++) {
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    progressBar.setValue(i);
//                    progressBar.setString("loading");
//                }
//                progressBar.setString("loaded");
//                button.setEnabled(true);
//
//            }
//        }.start();
//
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//            }
//        });
//        setVisible(true);
//    }
}

