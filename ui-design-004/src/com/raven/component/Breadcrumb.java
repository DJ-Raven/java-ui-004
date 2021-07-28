package com.raven.component;

import com.raven.event.EventItemSelected;
import com.raven.shadow.ShadowRenderer;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Breadcrumb extends javax.swing.JPanel {

    public EventItemSelected getEvent() {
        return event;
    }

    public void setEvent(EventItemSelected event) {
        this.event = event;
    }

    public Color getColorSelected() {
        return colorSelected;
    }

    public void setColorSelected(Color colorSelected) {
        this.colorSelected = colorSelected;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Breadcrumb() {
        initComponents();
        setOpaque(false);
        home.setEvent(new EventItemSelected() {
            @Override
            public void selected(BreadcrumbItem item) {
                itemSelected = item;
                repaint();
                if (event != null) {
                    event.selected(item);
                }
            }
        });
    }

    private Color color1 = Color.WHITE;
    private Color color2 = Color.WHITE;
    private Color colorSelected = Color.GRAY;
    private BreadcrumbItem itemSelected;
    private EventItemSelected event;
    private int index = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home = new com.raven.component.Home();
        empty = new javax.swing.JLabel();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        add(home);

        empty.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        add(empty);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        BufferedImage img = new BufferedImage(getWidth() - 10, getHeight() - 10, BufferedImage.TYPE_INT_ARGB);
        int width = img.getWidth();
        int height = img.getHeight();
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  paint gradient from left to right
        GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
        g2.setPaint(gra);
        int px[] = {0, width - 20, width, width - 20, 0};
        int py[] = {0, 0, height / 2, height, height};
        g2.fillPolygon(px, py, px.length);
        //  Create Shadow
        grphcs.drawImage(new ShadowRenderer(5, 0.3f, new Color(60, 60, 60)).createShadow(img), 0, 0, null);
        grphcs.drawImage(img, 5, 5, null);
        if (itemSelected != null) {
            //  Create color on item selected
            int x = itemSelected.getX() - 3;
            int w = itemSelected.getWidth() - 7;
            int margin = 5;
            Graphics2D g = (Graphics2D) grphcs;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //  Create color gradient
            int xx[];
            int yy[];
            if (itemSelected.getIndex() == 0) {
                //  Home
                x = 5;
                w -= 6;
                xx = new int[]{x, x + w, x + w + 20, x + w, x};
                yy = new int[]{margin, margin, height / 2 + margin, height + margin, height + margin};
            } else if (itemSelected.getIndex() == index) {
                //  Last Item
                w += 36;
                xx = new int[]{x, x + w, x + w + 20, x + w, x, x + 20};
                yy = new int[]{margin, margin, height / 2 + margin, height + margin, height + margin, height / 2 + margin};
            } else {
                xx = new int[]{x, x + w, x + w + 20, x + w, x, x + 20};
                yy = new int[]{margin, margin, height / 2 + margin, height + margin, height + margin, height / 2 + margin};
            }
            //  Draw color top to bot
            GradientPaint gra1 = new GradientPaint(0, margin, new Color(colorSelected.getRed(), colorSelected.getGreen(), colorSelected.getBlue(), 100), 0, height / 2, new Color(0, 0, 0, 0));
            g.setPaint(gra1);
            g.fillPolygon(xx, yy, xx.length);
            //  Draw color bot to top
            GradientPaint gra2 = new GradientPaint(0, height + margin, new Color(colorSelected.getRed(), colorSelected.getGreen(), colorSelected.getBlue(), 100), 0, height / 2, new Color(0, 0, 0, 0));
            g.setPaint(gra2);
            g.fillPolygon(xx, yy, xx.length);
        }
        super.paintComponent(grphcs);
    }

    public void addItem(String name) {
        //  So index item start from 1
        Item item = new Item(name, getForeground(), ++index);
        item.setEvent(new EventItemSelected() {
            @Override
            public void selected(BreadcrumbItem item) {
                itemSelected = item;
                repaint();
                if (event != null) {
                    event.selected(item);
                }
            }
        });
        add(item, getComponentCount() - 1);
        repaint();
        revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empty;
    private com.raven.component.Home home;
    // End of variables declaration//GEN-END:variables
}
