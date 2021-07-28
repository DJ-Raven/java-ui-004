package com.raven.component;

import javax.swing.JPanel;

public class BreadcrumbItem extends JPanel {

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;
}
