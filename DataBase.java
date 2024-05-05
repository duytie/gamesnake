package game;

import java.sql.*;
import java.util.ArrayList;

//tao database mới  đi
public class DataBase {
    private final String connectionUrl;

    public DataBase() {
        this.connectionUrl = "jdbc:sqlserver://192.168.0.2:1433;databaseName=DUYTIEN;user=sa;password=123456789;trustServerCertificate=true";
    }

    public void addDataToDB( int Score,String Times ) {
        try (Connection con = DriverManager.getConnection(this.connectionUrl);
             Statement stmt = con.createStatement()) {

            String SQL =
                    "INSERT INTO player ( Score, Times) VALUES (" + Score + ", '" + Times + "')";
            stmt.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.printf("thanh cong");
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
                arrInformation.add(new Information(rs.getString("Score"),rs.getString("Times")));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return arrInformation;
    }
    public static void main(String[] args) {

        DataBase connect = new DataBase();
        connect.addDataToDB(7,  "4");
        ArrayList<Information> arrInf = new  ArrayList<>();
        arrInf = connect.getDataFromDB();
        System.out.println(arrInf.size());
    }


}