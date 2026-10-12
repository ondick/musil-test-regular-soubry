import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Pattern pJmeno = Pattern.compile("^[A-ZÁ-Ž][a-zá-ž]{3,20}$");
    private Pattern pZkusenosti = Pattern.compile("^\\d{1,3}");

    public boolean validujRadek(String radek, List<String> chyby, List<Zadosti> platnyZadosti) {
        // limit -1 zajistí, že nepřehlédne prázdné hodnoty na konci řádku
        String[] casti = radek.split(";");

        if (casti.length != 5) {
            chyby.add("neplatný počet údajů na řádku");
            return false;
        }

        String jmeno = casti[0].trim();
        String rasa = casti[1].trim();
        String datumNarozeni = casti[2].trim();
        String typZbrane = casti[3].trim();
        String levelZkusenosti = casti[4].trim();

        boolean ok = true;

        Matcher mJmeno = pJmeno.matcher(jmeno);
        if (!mJmeno.matches()) {
            chyby.add("neplatne jmeno");
            ok = false;
        }

        if (!rasa.equals("Elf") &&
                !rasa.equals("Trpaslík") &&
                !rasa.equals("Člověk") &&
                !rasa.equals("Ork") &&
                !rasa.equals("Hobit")) {
            chyby.add("neplatna rasa");
            ok = false;
        }

        LocalDate datum = null;
        try {
            String[] d = datumNarozeni.split("\\.");
            if (d.length == 3) {
                int den = Integer.parseInt(d[0]);
                int mesic = Integer.parseInt(d[1]);
                int rok = Integer.parseInt(d[2]);
                datum = LocalDate.of(rok, mesic, den);

                if (datum.isAfter(LocalDate.now())) {
                    chyby.add("neplatné datum");
                    ok = false;
                }
            } else {
                chyby.add("neplatné datum");
                ok = false;
            }
        } catch (Exception e) {
            chyby.add("neplatné datum");
            ok = false;
        }

        if (!typZbrane.equals("Meč") &&
                !typZbrane.equals("Kuše") &&
                !typZbrane.equals("Hůl") &&
                !typZbrane.equals("Sekera") &&
                !typZbrane.equals("Dýka")) {
            chyby.add("neplatna zbran");
            ok = false;
        }


        Matcher mZkusenosti = pZkusenosti.matcher(levelZkusenosti);
        if (!mZkusenosti.matches()) {
            chyby.add("neplatny level zkusenosti");
            ok = false;
        } else if (Integer.parseInt(levelZkusenosti) < 1 || Integer.parseInt(levelZkusenosti) > 100) {
            chyby.add("neplatny level zkusenosti");
            ok = false;
        }


        if (ok) {
            Zadosti novaZadost = new Zadosti(
                    jmeno,
                    rasa,
                    datum,
                    typZbrane,
                    Integer.parseInt(levelZkusenosti)
            );
            platnyZadosti.add(novaZadost);
        }


        return ok;


    }
}