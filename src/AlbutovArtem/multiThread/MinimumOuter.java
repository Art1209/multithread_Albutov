package AlbutovArtem.multiThread;

import java.util.ArrayList;
import java.util.Collections;

public class MinimumOuter implements Runnable {
    public Boolean working = true;
    GuiWindow win;

    MinimumOuter(GuiWindow win){
        this.win = win;
    }
    @Override
    public void run() {
        while (working){
            if (win.numArray.size()!=0){
                getMinAndRemove();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private void getMinAndRemove(){ // Выводит в GUI и удаляет элемент с минимальным значением
        win.numToShowSetter(Collections.min(win.numArray).toString());
        int current = win.numArray.indexOf(Collections.min(win.numArray));
        win.numArray.remove(current);
        win.inputedArray.remove(current);
        win.textToShowUpdater();
    }
}
