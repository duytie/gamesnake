package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class HistoryPanel extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    JLabel [][] matrixInfor;
    JButton exitButton;
    HistoryListener listener;

    public HistoryPanel() {
        JLabel title = new JLabel("HISTORY");

        title.setForeground(Color.BLACK);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(150, 120, 300, 30);
        add(title);

        exitButton = new JButton("Exit");
        exitButton.setBounds(535, 570, 60, 25);
        this.setLayout(null);
        this.add(exitButton);
        exitButton.addActionListener(this);

//        createMatrixInfor();
//        printJLabel(this, matrixInfor,7, 4); // 7 người chơi, mỗi người chơi gồm 4 cột thông tin

    }
    private void createMatrixInfor() {
        DataBase connect = new DataBase();
        ArrayList<Information> arrInf = new  ArrayList<>();
        arrInf = connect.getDataFromDB();
        matrixInfor = new JLabel[arrInf.size() + 1][4];

        matrixInfor[0][0] = new JLabel();
        matrixInfor[0][0].setText("ID");
        matrixInfor[0][1] = new JLabel();
        matrixInfor[0][1].setText("NamePlayer");
        matrixInfor[0][2] = new JLabel();
        matrixInfor[0][2].setText("Score");
        matrixInfor[0][3] = new JLabel();
        matrixInfor[0][3].setText("Times");

        for (int i = 0; i < arrInf.size(); i++) {
            for (int j = 0; j < 4; j++) {
                matrixInfor[i + 1][j] = new JLabel();
                if (j == 0) {
                    matrixInfor[i + 1][j].setText(arrInf.get(i).id);
                } else if (j == 1) {
                    matrixInfor[i + 1][j].setText(arrInf.get(i).ten);
                } else if (j == 2) {
                    matrixInfor[i + 1][j].setText(arrInf.get(i).diem);
                } else if (j == 3) {
                    matrixInfor[i + 1][j].setText(arrInf.get(i).ngay);
                }
            }
        }
    }
    private void printJLabel(JPanel panel,JLabel [][]jlabel, int sohangmuoninra, int socotmuoninra) {
        for(int i=0;i< sohangmuoninra;i++) {
            for(int j=0;j<socotmuoninra;j++) {
                panel.add(jlabel[i][j]);
            }

        }
    }
    public void setHistorylListener(HistoryListener listener) {
        this.listener = listener;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== exitButton) {
            listener.onSelectExitFromHistory();
        }

    }

}

