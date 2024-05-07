package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HistoryPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    JLabel[][] matrixInfor;
    JButton exitButton;
    HistoryListener listener;

    public HistoryPanel() {
        setLayout(null); // Sử dụng layout null

        JLabel title = new JLabel("LỊCH SỬ");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Thoma", Font.BOLD, 20)); // Chỉnh kích thước font
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(150, 80, 300, 30); // Đặt vị trí và kích thước của tiêu đề
        add(title);

        exitButton = new JButton("Exit");
        exitButton.setBounds(535, 570, 60, 25); // Đặt vị trí và kích thước của nút Exit
        exitButton.addActionListener(this); // Thêm action listener cho nút Exit
        add(exitButton); // Thêm nút Exit vào panel

        createMatrixInfor();
        printJLabel(matrixInfor, matrixInfor.length, matrixInfor[0].length);
    }

    private void createMatrixInfor() {
        DataBase connect = new DataBase();
        ArrayList<Information> arrInf = connect.getDataFromDB();

        // Lấy 5 kết quả mới nhất hoặc ít hơn nếu số kết quả ít hơn 5
        int size = Math.min(arrInf.size(), 5);
        ArrayList<Information> latestResults = new ArrayList<>(arrInf.subList(arrInf.size() - size, arrInf.size()));

        // Khởi tạo ma trận với kích thước phù hợp
        matrixInfor = new JLabel[size + 1][1];
        matrixInfor[0][0] = new JLabel("điểm");
        matrixInfor[0][0].setFont(new Font("Tahoma", Font.BOLD, 16)); // Chỉnh kích thước font và độ đậm cho từ "điểm"

        // Đổ dữ liệu vào ma trận và chỉnh sửa font
        for (int i = 0; i < size; i++) {
            Information info = latestResults.get(i);
            matrixInfor[i + 1][0] = new JLabel(latestResults.get(size - i - 1).score);
            matrixInfor[i + 1][0].setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
    }

    private void printJLabel(JLabel[][] jlabel, int row, int column) {
        int labelWidth = 150; // Chiều rộng của mỗi nhãn
        int labelHeight = 20; // Chiều cao của mỗi nhãn
        int startX = 280; // Vị trí bắt đầu của hàng đầu tiên
        int startY = 180; // Vị trí bắt đầu của cột đầu tiên
        int horizontalGap = 20; // Khoảng cách giữa các cột
        int verticalGap = 10; // Khoảng cách giữa các hàng

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                JLabel label = jlabel[i][j];
                int x = startX + j * (labelWidth + horizontalGap); // Tính toán vị trí X
                int y = startY + i * (labelHeight + verticalGap); // Tính toán vị trí Y
                label.setBounds(x, y, labelWidth, labelHeight); // Đặt vị trí và kích thước của mỗi nhãn
                add(label); // Thêm nhãn vào panel
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            listener.onSelectExitFromHistory();
        }
    }



    public void setHistorylListener(GameFrame gameFrame) {
        this.listener = gameFrame;
    }
}
