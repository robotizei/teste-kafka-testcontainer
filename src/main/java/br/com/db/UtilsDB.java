package br.com.db;

import org.jooq.DSLContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.sql.DriverManager.getConnection;
import static org.jooq.impl.DSL.using;


public class UtilsDB {

    private static Connection connection;
    private static DSLContext dsl;

    public static DSLContext getDslContext(String url, String user, String pass) {
        try {
            connection = getConnection(url, user, pass);
            dsl = using(connection);
            return dsl;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String formatToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
    }

    public static String covertDate_yMd_To_dMy(String date) {
        return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String formatToDateTime(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).toString();
    }

}
