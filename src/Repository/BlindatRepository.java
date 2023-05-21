package Repository;

import Config.DataBaseConfiguration;
import Model.Blindat;
import Utile.UtilizareVehicul;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Constants.Constants.*;

public class BlindatRepository {
    private final DataBaseConfiguration dataBaseConfiguration;

    public BlindatRepository(DataBaseConfiguration dataBaseConfiguration){
        this.dataBaseConfiguration = dataBaseConfiguration;
    }

    public List<Blindat> getAllBlindate() throws SQLException{
        Statement statement = dataBaseConfiguration.getDatabaseConnection().createStatement();
        List<Blindat> listBlindate = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_BLINDATE);
        while (resultSet.next()){
            Blindat blindat = new Blindat(resultSet.getInt("codIdentificare"),
                    resultSet.getInt("idGestionarBlindat"),
                    resultSet.getString("denumire"),
                    UtilizareVehicul.valueOf(resultSet.getString("utilizare")),
                    resultSet.getString("taraProvenienta"),
                    resultSet.getBoolean("blindat"),
                    resultSet.getInt("nrLocuri"),
                    resultSet.getInt("autonomie"),
                    resultSet.getInt("vitezaMaxima"),
                    resultSet.getBoolean("suportRemorca"),
                    resultSet.getBoolean("suportArma"),
                    resultSet.getString("arma"));

            listBlindate.add(blindat);
        }
        return listBlindate;
    }

    public List<Blindat> getBlindatByDenumire(String denumire) throws SQLException{
        PreparedStatement preparedStatement = dataBaseConfiguration.getDatabaseConnection().prepareStatement(QUERY_GET_BLINDATE_BY_DENUMIRE);
        preparedStatement.setString(1, denumire);
        List<Blindat> listBlindate = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Blindat blindat = new Blindat(resultSet.getInt("codIdentificare"),
                    resultSet.getInt("idGestionarBlindat"),
                    resultSet.getString("denumire"),
                    UtilizareVehicul.valueOf(resultSet.getString("utilizare")),
                    resultSet.getString("taraProvenienta"),
                    resultSet.getBoolean("blindat"),
                    resultSet.getInt("nrLocuri"),
                    resultSet.getInt("autonomie"),
                    resultSet.getInt("vitezaMaxima"),
                    resultSet.getBoolean("suportRemorca"),
                    resultSet.getBoolean("suportArma"),
                    resultSet.getString("arma"));

            listBlindate.add(blindat);
        }
        return listBlindate;
    }

    public Blindat getBlindatById(int id) throws SQLException{
        PreparedStatement preparedStatement = dataBaseConfiguration.getDatabaseConnection().prepareStatement(QUERY_GET_BLINDAT_BY_ID);
        preparedStatement.setInt(1, id);
        Blindat blindat = new Blindat();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            blindat.setCodIdentificare(resultSet.getInt("codIdentificare"));
            blindat.setIdGestionar(resultSet.getInt("idGestionarBlindat"));
            blindat.setDenumire(resultSet.getString("denumire"));
            blindat.setUtilizare(UtilizareVehicul.valueOf(resultSet.getString("utilizare")));
            blindat.setTaraProvenienta(resultSet.getString("taraProvenienta"));
            blindat.setBlindat(resultSet.getBoolean("blindat"));
            blindat.setNrLocuri(resultSet.getInt("nrLocuri"));
            blindat.setAutonomie(resultSet.getInt("autonomie"));
            blindat.setVitezaMaxima(resultSet.getInt("vitezaMaxima"));
            blindat.setSuportRemorca(resultSet.getBoolean("suportRemorca"));
            blindat.setSuportArma(resultSet.getBoolean("suportArma"));
            blindat.setArma(resultSet.getString("arma"));
        }
        return blindat;
    }

    public void deleteBlindatById(int id) throws SQLException{
        PreparedStatement preparedStatement = dataBaseConfiguration.getDatabaseConnection().prepareStatement(QUERY_DELETE_BLINDAT_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void deleteTransportorByDenumire(String denumire) throws SQLException{
        PreparedStatement preparedStatement = dataBaseConfiguration.getDatabaseConnection().prepareStatement(QUERY_DELETE_BLINDATE_BY_DENUMIRE);
        preparedStatement.setString(1, denumire);
        preparedStatement.executeUpdate();
    }
}
