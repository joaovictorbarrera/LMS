package org.joaobarrera;

import org.joaobarrera.service.Library;
import org.joaobarrera.ui.Menu;

public class Main {
    public static void main(String[] args) {
        initializeLMS();
    }

    private static void initializeLMS() {
        Library library = new Library();

        Menu menu = new Menu(library);

        menu.launchMenu();
    }

}