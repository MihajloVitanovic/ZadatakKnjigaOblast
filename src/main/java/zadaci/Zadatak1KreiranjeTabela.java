package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 * Created by androiddevelopment on 20.4.17..
 */
public class Zadatak1KreiranjeTabela {
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            //Potrebno je prvo konektovati se na bazu
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
