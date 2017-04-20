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
           /*
            //Pronalazenje robe koja za vrednost kolone opis ima
            // vrednost "Plasticna stolica"
            List<Roba> pronadjenaRoba=robaDao.queryForEq(Roba.POLJE_OPIS,"Plasticna stolica");

            Roba robaZaIzmenu=pronadjenaRoba.get(0);//Preuzimamo prvi pronadjeni
            //Menjamo vrednost atributa opis na Drvena stolica
            robaZaIzmenu.setOpis("Drvena stolica");
            //Cuvamo izmene u bazi, menja se vrednost kolone opis
            robaDao.update(robaZaIzmenu);

            /*Prikaz vrednosti tabele Roba
               da potvrdimo da je vrednost izmenjena

            roba=robaDao.queryForAll();
            for(Roba r:roba)
                System.out.println("Roba = " + r);*/


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
