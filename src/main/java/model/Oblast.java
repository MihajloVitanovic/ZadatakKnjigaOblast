package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 20.4.17..
 */
@DatabaseTable(tableName = "oblast")
public class Oblast {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = POLJE_NAZIV, canBeNull = false)
    private String naziv;
    @DatabaseField(columnName = POLJE_POCETNA_STRANA, canBeNull = false)
    private int pocetnaStrana;

    //Staticki atributi
    //ne treba anotacija
    public static final String POLJE_NAZIV="naziv";
    public static final String POLJE_POCETNA_STRANA="pocetna_strana";

    //Atribut za vezu
    @DatabaseField(foreign = true,foreignAutoRefresh = true, canBeNull = true)
    private Knjiga knjiga;

    //Konstruktori
    public Oblast(){}
    public Oblast(String naziv, int pocetnaStrana){
        this.naziv = naziv;
        this.pocetnaStrana = pocetnaStrana;
    }

    //get/set
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNaziv(){return naziv;}
    public void setNaziv(String naziv){this.naziv = naziv;}

    public int getPocetnaStrana(){return pocetnaStrana;}
    public void setPocetnaStrana(int pocetnaStrana){this.pocetnaStrana = pocetnaStrana;}

    public Knjiga getKnjiga(){return knjiga;}
    public void setKnjiga(Knjiga knjiga){this.knjiga = knjiga;}


    //Redefinisana metoda toString
    @Override
    public String toString() {
        return "Knjiga{" +
                "id=" + id +
                ", naziv=" + naziv +
                ", pocetnaStrana=" + pocetnaStrana +
                "}";
    }

}
