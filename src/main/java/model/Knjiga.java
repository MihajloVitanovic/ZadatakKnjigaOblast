package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 20.4.17..
 */
@DatabaseTable(tableName = "knjiga")
public class Knjiga {

    @DatabaseField(generatedId = true)//primarni kljuc koji se automatski generise
    private int id;
    @DatabaseField(columnName = POLJE_NASLOV,canBeNull = false)
    private String naslov;
    @DatabaseField(columnName = POLJE_BROJ_STRANA,canBeNull = false)
    private int brojStrana;
    @DatabaseField(columnName = POLJE_DATUM_IZDAVANJA,canBeNull = false)
    private java.util.Date datumIzdanja;
    private boolean prisutna; //ne treba anotacija

    //Staticki atributi
    //ne treba anotacija
    public static final String POLJE_NASLOV="naslov";
    public static final String POLJE_BROJ_STRANA="broj_strana";
    public static final String POLJE_DATUM_IZDAVANJA="datum_izdavanja";

    //NE ZABORAVI
    //U klasi Knjiga ubaciti odgovarajući atribut i anotaciju
    //atributa kako bi se predstavio više kraj veze izmed̄u Oblast i Knjiga klasa.

    //Konstruktori
    public Knjiga(){}
    public Knjiga(String naslov, int brojStrana, java.util.Date datumIzdanja){
        this.naslov = naslov;
        this.brojStrana = brojStrana;
        this.datumIzdanja = datumIzdanja;
    }

    //get/set
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNaslov(){return naslov;}
    public void setNaslov(String naslov){this.naslov = naslov;}

    public int getBrojStrana(){return  brojStrana;}
    public void setBrojStrana(int brojStrana){this.brojStrana = brojStrana;}

    public java.util.Date getDatumIzdanja(){return datumIzdanja;}
    public void setDatumIzdanja(java.util.Date datumIzdanja){this.datumIzdanja = datumIzdanja;}

    //Redefinisana metoda toString
    @Override
    public String toString() {
        return "Knjiga{" +
                "id=" + id +
                ", naslov=" + naslov +
                ", brojStrana=" + brojStrana +
                ", datumIzdavanja=" + datumIzdanja +
                "}";
    }

}

/*    //atributi za upisu u bazu
    @DatabaseField(generatedId = true)//primarni kljuc koji se automatski generise
    private int id;
    @DatabaseField(columnName = POLJE_OZNAKA,canBeNull = false)
    private String oznaka;
    @DatabaseField(columnName = POLJE_RASPON_KRILA,canBeNull = false)
    private int rasponKrila;

    //Atribut za vise kraj veze izmendju klasa Roba i Avion
    @ForeignCollectionField(foreignFieldName = "avion")
    private ForeignCollection<Roba> roba;*/
