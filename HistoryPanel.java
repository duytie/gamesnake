package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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


    }
    private void createMatrixInfor() {
        DataBase connect = new DataBase();
        ArrayList<Information> arrInf = new  ArrayList<>();
        arrInf = connect.getDataFromDB();
        matrixInfor = new JLabel[arrInf.size() + 1][2];

        matrixInfor[0][0] = new JLabel();
        matrixInfor[0][0].setText("score");
        matrixInfor[0][1] = new JLabel();
        matrixInfor[0][1].setText("time");



        for (int i = 0; i < arrInf.size(); i++) {
            for (int j = 0; j < 2; j++) {
                matrixInfor[i + 1][j] = new JLabel();
                if (j == 0) {
                    matrixInfor[i + 1][j].setText(arrInf.get(i).time);
                } else if (j == 1) {
                    matrixInfor[i + 1][j].setText(arrInf.get(i).score);
                }
            }
        }
    }
    private void printJLabel(JPanel panel,JLabel [][]jlabel, int row, int colum) {
        for(int i=0;i< row;i++) {
            for(int j=0;j<colum;j++) {
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

