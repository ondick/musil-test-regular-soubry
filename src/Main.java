import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Zadosti> platnyZadosti = new ArrayList<>();
        Validator validator = new Validator();

        try {
            File soubor = new File("data.txt");
            Scanner scanner = new Scanner(soubor);

            int cisloRadku = 1;
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                if (radek.trim().isEmpty()) {
                    cisloRadku++;
                    continue;
                }

                List<String> chyby = new ArrayList<>();
                boolean jePlatny = validator.validujRadek(radek, chyby, platnyZadosti);

                if (jePlatny) {
                    for (int i = 0; i < radek.length(); i++) {

                    }

                    System.out.println("Žádost postavy " + platnyZadosti.getLast().getJmeno() + " je validní");

                } else {
                    System.out.println("Žádost není validní: \n - " + String.join(", ", chyby));
                }

                cisloRadku++;
            }
            System.out.println("");

            //Vypis mladsich 18 let
            if (platnyZadosti.isEmpty()) {
                System.out.println("Seznam prazdnej");
            } else {
                System.out.println("-----Mladsi 18 let-----");
                for (Zadosti zadosti : platnyZadosti) {
                    if (2026 - zadosti.getRokNarozeni() <= 18) {
                        System.out.println(zadosti.getJmeno() + " " + zadosti.getDatumNarozeni());
                    }
                }
            }

            //Vypis statistik ras
            int elf = 0;
            int trpaslik = 0;
            int clovek = 0;
            int ork = 0;
            int hobit = 0;
            if (platnyZadosti.isEmpty()) {
                System.out.println("Seznam prazdnej");
            } else {
                for (Zadosti zadosti : platnyZadosti) {
                    if (zadosti.getRasa().equals("Elf")) {
                        elf++;
                    } else if (zadosti.getRasa().equals("Trpaslík")) {
                        trpaslik++;
                    } else if (zadosti.getRasa().equals("Člověk")) {
                        clovek++;
                    } else if (zadosti.getRasa().equals("Ork")) {
                        ork++;
                    } else if (zadosti.getRasa().equals("Hobit")) {
                        hobit++;
                    }

                }
                System.out.println("");
                System.out.println("-----Statistiky ras-----");
                System.out.println("Valiní žádosti elfů:" + elf);
                System.out.println("Valiní žádosti trpaslíků:" + trpaslik);
                System.out.println("Valiní žádosti lidí:" + clovek);
                System.out.println("Valiní žádosti okrů:" + ork);
                System.out.println("Valiní žádosti Hobitů:" + hobit);
            }

            //Vypis nejvyššího levelu
            if (platnyZadosti.isEmpty()) {
                System.out.println("Seznam prazdnej");
            } else {
                int maxLvl = -1;
                for (Zadosti zadosti : platnyZadosti) {
                    if (zadosti.getLvlZkusenosti() > maxLvl) {
                        maxLvl = zadosti.getLvlZkusenosti();
                    }
                }
                System.out.println("");
                System.out.println("Zadost s nejvetsim levelem:");
                for (Zadosti zadosti : platnyZadosti) {
                    if (zadosti.getLvlZkusenosti() == maxLvl) {
                        System.out.println(zadosti.getJmeno() + " " + zadosti.getRasa() + " " + zadosti.getDatumNarozeni() + " " + zadosti.getTypZbrane() + ",  level: " + zadosti.getLvlZkusenosti());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Soubor se nepodařilo načíst.");
        }
    }
}