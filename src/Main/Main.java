package Main;

import Presentaciones.Controller;
import datas.ItemDom;

public class Main {
    public static void main(String[] args) {

        ItemDom item = new ItemDom("File.xml");
        Controller controller = new Controller();

        controller.Init();

    }
}