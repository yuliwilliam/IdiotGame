import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int height = 500;
        int width = (int) (height * 0.618);
        JFrame frame = new JFrame();
        JPanel panel = (JPanel) frame.getContentPane();
        JLabel label = new JLabel("Are you idiot？");
        JButton yesBtn = new JButton("YES");
        JButton noBtn = new JButton("NO");

        panel.setLayout(null);

        Dimension labelSize = label.getPreferredSize();
        Dimension btnSize = yesBtn.getPreferredSize();

        label.setBounds(width / 2 - (int) labelSize.getWidth() / 2, 100, (int) labelSize.getWidth(), (int) labelSize.getHeight());
        yesBtn.setBounds(50, height - 100, (int) btnSize.getWidth(), (int) btnSize.getHeight());
        noBtn.setBounds(width - (int) btnSize.getWidth() - 50, height - 100, (int) btnSize.getWidth(), (int) btnSize.getHeight());

        yesBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "You are right!");
            System.exit(0);
        });

        panel.add(label);
        panel.add(yesBtn);
        panel.add(noBtn);


        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                JOptionPane.showMessageDialog(null, "Nope, you think too much");
            }
        });

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(new Dimension(width, height));
        frame.setVisible(true);


        while (true) {

            height = frame.getHeight();
            width = frame.getWidth();

            Point frameLocation = frame.getLocation();
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

            //find relative position of mouse, 20 is the size of menu bar
            double x = mouseLocation.getX() - frameLocation.getX();
            double y = mouseLocation.getY() - frameLocation.getY() - 20;

            if (noBtn.getBounds().contains(x, y)) {
                Random rand = new Random();
                noBtn.setBounds(rand.nextInt(width - (int) btnSize.getWidth()), rand.nextInt(height - (int) btnSize.getHeight()), (int) btnSize.getWidth(), (int) btnSize.getHeight());

            }
            label.setBounds(width / 2 - (int) labelSize.getWidth() / 2, 100, (int) labelSize.getWidth(), (int) labelSize.getHeight());
            yesBtn.setBounds(50, height - 100, (int) btnSize.getWidth(), (int) btnSize.getHeight());

        }

    }


}
