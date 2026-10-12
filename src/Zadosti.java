import java.time.LocalDate;

public class Zadosti {
    private String jmeno;
    private String rasa;
    private LocalDate datumNarozeni;
    private String typZbrane;
    private int lvlZkusenosti;


    public Zadosti(String jmeno, String rasa, LocalDate datumNarozeni, String typZbrane, int lvlZkusenosti) {
        this.jmeno = jmeno;
        this.rasa = rasa;
        this.datumNarozeni = datumNarozeni;
        this.typZbrane = typZbrane;
        this.lvlZkusenosti = lvlZkusenosti;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public int getRokNarozeni() {
        return datumNarozeni.getYear();
    }

    public void setDatumNarozeni(LocalDate datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    public String getTypZbrane() {
        return typZbrane;
    }

    public void setTypZbrane(String typZbrane) {
        this.typZbrane = typZbrane;
    }

    public int getLvlZkusenosti() {
        return lvlZkusenosti;
    }

    public void setLvlZkusenosti(int lvlZkusenosti) {
        this.lvlZkusenosti = lvlZkusenosti;
    }
}
