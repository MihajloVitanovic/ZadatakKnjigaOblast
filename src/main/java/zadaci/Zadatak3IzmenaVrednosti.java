package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Knjiga;
import model.Oblast;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 20.4.17..
 */
public class Zadatak3IzmenaVrednosti {
    static Dao<Knjiga,Integer> knjigaDao;
    static Dao<Oblast,Integer> oblastDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            //Potrebno je prvo konektovati se na bazu
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");
            //Instanciranje Dao objekata
            knjigaDao = DaoManager.createDao(connectionSource, Knjiga.class);
            oblastDao = DaoManager.createDao(connectionSource, Oblast.class);

            List<Oblast> oblast=oblastDao.queryForAll();
            for (Oblast o:oblast)
                System.out.println("Oblast = " + o);

            List<Oblast> pronadjenaOblast=oblastDao.queryForEq(Oblast.POLJE_NAZIV, "Activity klasa");
            Oblast oblastZaIzmenu=pronadjenaOblast.get(0);
            oblastZaIzmenu.setPocetnaStrana(35);
            oblastDao.update(oblastZaIzmenu);

            oblast=oblastDao.queryForAll();
            for (Oblast o:oblast)
                System.out.println("Oblast = " + o);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
