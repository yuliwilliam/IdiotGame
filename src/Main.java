import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //add questions
        LinkedList questions = new LinkedList();
        questions.add("Are you Idiot?");
        questions.add("Want to be happier?");


        //init JFrame size
        int height = 500;
        int width = (int) (height * 0.618);

        JFrame frame = new JFrame();
        JPanel panel = (JPanel) frame.getContentPane();
        JLabel label = new JLabel(questions.pop().toString());
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

            //exit if no more questions to ask
            if (questions.size() == 0) {
                System.exit(0);
            }

            //set label to new question
            label.setText(questions.pop().toString());
            Dimension newLabelSize = label.getPreferredSize();
            label.setBounds(frame.getWidth() / 2 - (int) newLabelSize.getWidth() / 2, 100, (int) newLabelSize.getWidth(), (int) newLabelSize.getHeight());

        });

        noBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Wrong answer!");
        });


        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                JOptionPane.showMessageDialog(null, "Nope, you think too much");
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {

                //limit the minimum JFrame height
                if (frame.getHeight() < height) {
                    frame.setSize(new Dimension(frame.getWidth(), height));
                }

                //limit the minimum JFrame width
                if (frame.getWidth() < width) {
                    frame.setSize(new Dimension(width, frame.getHeight()));
                }

                //random change location if outside frame
                if (noBtn.getX() > frame.getWidth() - (int) btnSize.getWidth() - frame.getInsets().left - frame.getInsets().right || noBtn.getY() >= frame.getHeight() - (int) btnSize.getHeight() - frame.getInsets().top - frame.getInsets().bottom) {
                    Random rand = new Random();
                    noBtn.setBounds(rand.nextInt(frame.getWidth() - (int) btnSize.getWidth() - frame.getInsets().left - frame.getInsets().right), rand.nextInt(frame.getHeight() - (int) btnSize.getHeight() - frame.getInsets().top - frame.getInsets().bottom), (int) btnSize.getWidth(), (int) btnSize.getHeight());
                }

                Dimension labelSize = label.getPreferredSize();
                label.setBounds(frame.getWidth() / 2 - (int) labelSize.getWidth() / 2, 100, (int) labelSize.getWidth(), (int) labelSize.getHeight());
                yesBtn.setBounds(50, frame.getHeight() - 100, (int) btnSize.getWidth(), (int) btnSize.getHeight());

            }
        });


        noBtn.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                Point frameLocation = frame.getLocation();
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

                //find relative position of mouse, frame.getInsets().top is the size of title bar, frame.getInsets().left is the left boarder
                double x = mouseLocation.getX() - frameLocation.getX() - frame.getInsets().left;
                double y = mouseLocation.getY() - frameLocation.getY() - frame.getInsets().top;

                Rectangle btnBoundary = noBtn.getBounds();
//                btnBoundary.grow(10,10);

                //random change location
                if (btnBoundary.contains(x, y)) {
                    Random rand = new Random();
                    noBtn.setBounds(rand.nextInt(frame.getWidth() - (int) btnSize.getWidth() - frame.getInsets().left - frame.getInsets().right), rand.nextInt(frame.getHeight() - (int) btnSize.getHeight() - frame.getInsets().top - frame.getInsets().bottom), (int) btnSize.getWidth(), (int) btnSize.getHeight());
                }
            }

        });

        panel.add(label);
        panel.add(yesBtn);
        panel.add(noBtn);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(new Dimension(width, height));
        frame.setVisible(true);
    }
}
