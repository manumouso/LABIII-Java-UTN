package edu.utn.view;

import edu.utn.manager.GameManager;

public interface View {
    void header();
    void menu(GameManager manager);
    void footer();
}
