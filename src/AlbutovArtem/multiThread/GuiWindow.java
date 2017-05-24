package AlbutovArtem.multiThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GuiWindow extends JFrame {
    protected ArrayList<Integer> numArray; // Список числовых представлений введенных пользователем чисел
    protected ArrayList<String> inputedArray; // Список текстовых представлений введенных пользователем чисел
    protected int counter = 0; // Счетчик количества введенных строк пользователем, при изменении значения в TypeListener запускается анализ новой строки
    private Font font = new Font("Verdana", Font.BOLD, 18);

    private JTextArea textToType, textToShow, numToShow;
    private JButton button;

    public GuiWindow(){
        numArray = new ArrayList<>();
        inputedArray = new ArrayList<>();


    /*
    Инициализация графических элементов
     */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Главная");
        setBounds(300,300,400,420);
        setLayout(new BorderLayout());

        JPanel panelToShow = new JPanel();
        panelToShow.setLayout(new BorderLayout());
        add(panelToShow,BorderLayout.CENTER);

        numToShow = new JTextArea();
        numToShow.setEditable(false);
        numToShow.setFont(font);
        panelToShow.add(numToShow, BorderLayout.PAGE_START);

        textToShow = new JTextArea();
        textToShow.setEditable(false);
        panelToShow.add(textToShow, BorderLayout.CENTER);


        JScrollPane jsp = new JScrollPane(textToShow);
        panelToShow.add(jsp);

        JPanel panelToType = new JPanel();
        add(panelToType,BorderLayout.PAGE_END);
        panelToType.setLayout(new BorderLayout());

        textToType = new JTextArea();
        textToType.setRows(3);
        panelToType.add(textToType, BorderLayout.CENTER);


        button = new JButton();
        button.setText("Готово");
        panelToType.add(button, BorderLayout.LINE_END);


        setVisible(true);
    }

    /*
    Публичные методы для работы с графикой из потоков
     */
    public void textToShowSetter(String text){
        textToShow.setText(text);
    }
    public void numToShowSetter(String text){
        numToShow.setText(text);
    }
    public void textToTypeSetter(String text){
        textToType.setText(text);
    }
    public String textToTypeGetter(){
        return textToType.getText();
    }
    public void textToShowUpdater(){ // Вывод inputedArray в качестве отдельных строк
        String out="";
        for (String num: inputedArray){
            out= out + (num+"\n");
        }
        textToShowSetter(out);
    }
    public void actionSetter(){
        inputedArray = new ArrayList<>();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputedStringMover();
            }
        });
        textToType.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    inputedStringMover();
                }

            }
        });

    }
    private Boolean inputedStringNullChecker(String string){
        if (string!=null && string.length()>2){
            return true;
        }
        else return false;
    }
    private void inputedStringMover(){
        if (inputedStringNullChecker(textToTypeGetter())){
            inputedArray.add(textToTypeGetter());
            counter++;
            textToTypeSetter("");
            textToShowUpdater();
        }
    }



}
