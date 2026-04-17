package ru.gr094622;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.gr094622.animating.Animator;
import ru.gr094622.animating.Mover;
import ru.gr094622.model.GeometryObject;
import ru.gr094622.painting.Circle;
import ru.gr094622.painting.Rect;

public class MainWindow extends JFrame {
    private static final int W = 800;
    private static final int H = 600;

    private final List<GeometryObject> objects = new ArrayList<>();
    private final JPanel renderPanel;
    private final Mover mover;
    private final Animator animator;

    public MainWindow() {
        setTitle("Geometry Animator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(W, H);
        setLayout(new BorderLayout());

        Random rnd = new Random();
        int wSize;
        int hSize;
        int x, y;
        Dimension sz;
        Color c;
        wSize = rnd.nextInt(40, 101);
        hSize = rnd.nextInt(40, 101);
        sz = new Dimension(wSize, hSize);
        x = rnd.nextInt(0, getWidth()-wSize);
        y = rnd.nextInt(0, getHeight()-hSize);
        c = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        objects.add(new Circle(x, y, sz, c));
        wSize = rnd.nextInt(40, 101);
        hSize = rnd.nextInt(40, 101);
        sz = new Dimension(wSize, hSize);
        x = rnd.nextInt(0, getWidth()-wSize);
        y = rnd.nextInt(0, getHeight()-hSize);
        c = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        objects.add(new Rect(x, y, sz, c));
        wSize = rnd.nextInt(40, 101);
        hSize = rnd.nextInt(40, 101);
        sz = new Dimension(wSize, hSize);
        x = rnd.nextInt(0, getWidth()-wSize);
        y = rnd.nextInt(0, getHeight()-hSize);
        c = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        objects.add(new Circle(x, y, sz, c));

        renderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (GeometryObject obj : objects) {
                    obj.paint(g);
                }
            }
        };
        renderPanel.setBackground(Color.WHITE);

        JButton btnStart = new JButton("Старт");
        JButton btnStop = new JButton("Стоп");

        JPanel controlPanel = new JPanel();
        controlPanel.add(btnStart);
        controlPanel.add(btnStop);

        add(renderPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        Dimension panelSize = new Dimension(W, H);
        mover = new Mover(objects);
        mover.setSize(panelSize);
        animator = new Animator(objects, panelSize, renderPanel);

        btnStart.addActionListener(e -> {
            mover.start();
            animator.start();
        });

        btnStop.addActionListener(e -> {
            mover.stop();
            animator.stop();
        });
    }
}
