package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

public class StartAppPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private StartAppListener listener;
    private JButton troChoiMoi;
    private JButton lichSu;
    private JButton thoat;
    public StartAppPanel() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 600));
        setBorder(new EmptyBorder(50, 50, 50, 50));
        setBackground(new Color(38,41,43));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);

        ImageIcon originalIcon = new ImageIcon("C:/Users/Opengate/IdeaProjects/unit1/src/game/img1.jpg");
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel snakeLabel = new JLabel(scaledIcon);
        add(snakeLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);

        troChoiMoi = new JButton("Trò chơi mới");
        lichSu = new JButton("Lịch Sử");
        thoat = new JButton("Thoát");

        troChoiMoi.addActionListener(this);
        lichSu.addActionListener(this);
        thoat.addActionListener(this);

        Dimension buttonSize = new Dimension(180, 40);
        troChoiMoi.setPreferredSize(buttonSize);
        lichSu.setPreferredSize(buttonSize);
        thoat.setPreferredSize(buttonSize);

        troChoiMoi.setBackground(new Color(255, 153, 51));
        lichSu.setBackground(new Color(255, 153, 51));
        thoat.setBackground(new Color(255, 153, 51));

        add(troChoiMoi, gbc);

        gbc.gridy = 2;
        add(lichSu, gbc);

        gbc.gridy = 3;
        add(thoat, gbc);
    }

    public void setStartAppListener(StartAppListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener != null) {
            if (e.getSource() == thoat) {
                listener.onThoatClicked();
            } else if (e.getSource() == troChoiMoi) {
                listener.onTroChoiMoiClicked();
            } else if (e.getSource() == lichSu) {
                listener.onLichSuClicked();
            }
        }
    }
}