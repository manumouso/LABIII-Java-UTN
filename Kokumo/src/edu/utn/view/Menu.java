package edu.utn.view;

import edu.utn.manager.GameManager;

public interface Menu {
    void header();
    void menu(GameManager manager);
    void footer();
}
