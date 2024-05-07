package game;

import java.sql.*;
import java.util.ArrayList;

//tao database mới  đi
public class DataBase {
    private final String connectionUrl;

    public DataBase() {
        this.connectionUrl = "jdbc:sqlserver://localhost;databaseName=Java;user=sa;password=123456789;trustServerCertificate=true";
    }

    public void addDataToDB( int Score ) {
        try (Connection con = DriverManager.getConnection(this.connectionUrl);
             Statement stmt = con.createStatement()) {

            String SQL =
                    "INSERT INTO Table_5 (Score) VALUES (" + Score + ")";
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
            String SQL = "SELECT * FROM Table_5";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                arrInformation.add(new Information(rs.getString("Score")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return arrInformation;
    }
    public static void main(String[] args) {

        DataBase connect = new DataBase();
        //connect.addDataToDB(7);
        ArrayList<Information> arrInf = new  ArrayList<>();
        arrInf = connect.getDataFromDB();
        System.out.println(arrInf.size());
    }


}