package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Knjiga;
import model.Oblast;

import java.io.IOException;
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

            TableUtils.dropTable(connectionSource, Oblast.class,true);
            TableUtils.dropTable(connectionSource, Knjiga.class,true);

            TableUtils.createTable(connectionSource, Knjiga.class);
            TableUtils.createTable(connectionSource, Oblast.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connectionSource != null) {
                try {
                    //Zatvaranje konekcije sa bazom
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
