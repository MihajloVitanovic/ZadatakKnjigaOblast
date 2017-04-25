package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Knjiga;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Mihajlo on 25-Apr-17.
 */
public class KnjigaNit extends Thread {

    private String imeClana;
    private Knjiga knjiga;
    static Dao<Knjiga, Integer> knjigaDao;
    
    public KnjigaNit(){}

    public KnjigaNit(String imeClana, Knjiga knjiga){
        this.imeClana = imeClana;
        this.knjiga = knjiga;
    }

    private void traziKnjigu(){
        System.out.println("Clan " + imeClana + " trazi knjigu " + knjiga);
        try {
            this.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        boolean uzeoKnjigu = false;
        traziKnjigu();
        try {
            this.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        do {
            synchronized (knjiga) {
                if (knjiga.isPrisutna()){
                System.out.println("Clan " + imeClana + " je pozajmio knjigu " + knjiga);
                uzeoKnjigu = true;
                knjiga.setPrisutna(false);
                    Random random = new Random();
                    try {
                        this.sleep(random.nextInt(5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (knjiga) {
                        knjiga.setPrisutna(true);
                    System.out.println("Clan " + imeClana + " je vratio knjigu " + knjiga);
                }
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }else{
                    System.out.println("Clan " + imeClana + " ceka da se vrati knjiga " + knjiga);
                }
            }

        }while (!uzeoKnjigu);
    }

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            //Potrebno je prvo konektovati se na bazu
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:knjigaOblast.db");
            //Instanciranje Dao objekata
            knjigaDao = DaoManager.createDao(connectionSource, Knjiga.class);
            List<Knjiga> sveKnjige = knjigaDao.queryForAll();

            KnjigaNit k1 = new KnjigaNit("Petar", sveKnjige.get(0));
            KnjigaNit k2 = new KnjigaNit("Marko", sveKnjige.get(0));
            KnjigaNit k3 = new KnjigaNit("Jovan", sveKnjige.get(0));

            KnjigaNit k4 = new KnjigaNit("Milan", sveKnjige.get(1));
            KnjigaNit k5 = new KnjigaNit("Ivan", sveKnjige.get(1));
            KnjigaNit k6 = new KnjigaNit("Bojan", sveKnjige.get(1));

            k1.start();
            k2.start();
            k3.start();
            k4.start();
            k5.start();
            k6.start();
            try {
                k1.join();
                k2.join();
                k3.join();
                k4.join();
                k5.join();
                k6.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Biblioteka se zatvara");
        } catch (Exception e) {
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
