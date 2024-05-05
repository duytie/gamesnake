package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;

//tao database mới  đi
public class DataBase {
    private String connectionUrl;

    public DataBase() {
        this.connectionUrl = "jdbc:sqlserver://192.168.43.24:1433;databaseName=player;user=sa;password=nghia;trustServerCertificate=true";
    }

    public void addDataToDB(int ID,String UserName,  int Score,String Times ) {
        try (Connection con = DriverManager.getConnection(this.connectionUrl);
             Statement stmt = con.createStatement()) {

            String SQL =
                    "INSERT INTO player (ID, UserName, Score, Times) VALUES ("+ ID  + ", '" + UserName + "', " + Score + ", '" + Times + "')";
            stmt.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Information> getDataFromDB() {
        ArrayList<Information> arrInformation = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(this.connectionUrl);
             Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM player";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) { //them thong tin
                arrInformation.add(new Information(rs.getString("ID"),rs.getString("UserName"),rs.getString("Score"),rs.getString("Times")));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return arrInformation;
    }
    public static void main(String[] args) {
        DataBase connect = new DataBase();
        connect.addDataToDB(7, "user7", 20, "15-02-2024 15:11:21");
        ArrayList<Information> arrInf = new  ArrayList<>();
        arrInf = connect.getDataFromDB();
        System.out.println(arrInf.size());
    }


}