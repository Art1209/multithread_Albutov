package AlbutovArtem.multiThread;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.Arrays;

public class TypeListener implements Runnable{
    Boolean working = true;
    GuiWindow win;

    TypeListener(GuiWindow win){
        this.win = win;
    }

    @Override
    public void run() {
        win.actionSetter();
        int threadCounter = 0;
        while(working){
            if (win.counter!=threadCounter){
                    int tempResult = parseNumList(win.inputedArray.get(win.inputedArray.size()-1));
                    if (tempResult>0){
                        win.numArray.add(tempResult);
                    }
            }
            threadCounter = win.counter;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
// todo Стоит вынести парсеры в статик класс
// todo Нужна защита "от дурака"
    private int parseNumList(String s) { // Анализ строки, в случае неправильного ввода возвращает ноль
        int result = -1;
        int tempResult;
        int thousands = 0;
        int hundreds = 0;
        int decs = 0;
        ArrayList<String> array = new ArrayList<>();
        for (int i =0; i<s.toLowerCase().split(" ").length; i++) {
            array.add(s.toLowerCase().split(" ")[i]);
        }
        if (array.contains("thousand")){thousands=parseNum(array.get(array.indexOf("thousand")-1));}
        if (array.contains("hundred")){hundreds=parseNum(array.get(array.indexOf("hundred")-1));}
        if (parseNum(array.get(array.size()-1))>0 && array.size()>1){
            decs = parseNum(array.get(array.size()-2));
        }
        tempResult=(thousands*1000)+(hundreds*100)+parseNum(array.get(array.size()-1))+decs;
        if (tempResult!=0){
            result=tempResult;
        } else {
            win.inputedArray.remove(win.inputedArray.size()-1);
        }
        return result;

    }
    private int parseNum(String num){ // хандкодим
        int digit;
        switch (num){
            case "one":{digit = 1; break;}
            case "two":{digit = 2;break;}
            case "three":{digit = 3;break;}
            case "four":{digit = 4;break;}
            case "five":{digit = 5;break;}
            case "six":{digit = 6;break;}
            case "seven":{digit = 7;break;}
            case "eight":{digit = 8;break;}
            case "nine":{digit = 9;break;}
            case "ten":{digit = 10;break;}
            case "eleven":{digit = 11;break;}
            case "twelve":{digit = 12;break;}
            case "thirteen":{digit = 13;break;}
            case "fourteen":{digit = 14;break;}
            case "fifteen":{digit = 15;break;}
            case "sixteen":{digit = 16;break;}
            case "seventeen":{digit = 17;break;}
            case "eighteen":{digit = 18;break;}
            case "nineteen":{digit = 19;break;}
            case "twenty":{digit = 20;break;}
            case "thirty":{digit = 30;break;}
            case "fourty":{digit = 40;break;}
            case "fifty":{digit = 50;break;}
            case "sixty":{digit = 60;break;}
            case "seventy":{digit = 70;break;}
            case "eighty":{digit = 80;break;}
            case "ninety":{digit = 90;break;}
            default: digit = 0;
        }
        return digit;
    }

}
