import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

class Producte {
    String codiBarres;
    String nom;
    double preu;

    public Producte(String codiBarres, String nom, double preu) {
        this.codiBarres = codiBarres;
        this.nom = nom;
        this.preu = preu;
    }

    public String getCodiBarres() {
        return codiBarres;
    }

    public String getNom() {
        return nom;
    }

    public double getPreu() {
        return preu;
    }
}

class Alimentacio extends Producte {
    Date dataCaducitat;

    public Alimentacio(String codiBarres, String nom, double preu, Date dataCaducitat) {
        super(codiBarres, nom, preu);
        this.dataCaducitat = dataCaducitat;
    }

    public Date getDataCaducitat() {
        return dataCaducitat;
    }

    // Calcular preu segons la fórmula
    public double calcularPreu() {
        Date dataActual = new Date();
        long diesCaducitat = (dataCaducitat.getTime() - dataActual.getTime()) / (24 * 60 * 60 * 1000);
        return preu - preu * (1.0 / (diesCaducitat + 1)) + (preu * 0.1);
    }
}

class Tèxtil extends Producte {
    String composicio;

    public Tèxtil(String codiBarres, String nom, double preu, String composicio) {
        super(codiBarres, nom, preu);
        this.composicio = composicio;
    }
}

class Electrònica extends Producte {
    int diesGarantia;

    public Electrònica(String codiBarres, String nom, double preu, int diesGarantia) {
        super(codiBarres, nom, preu);
        this.diesGarantia = diesGarantia;
    }

    // Calcular preu segons la fórmula
    public double calcularPreu() {
        return preu + preu * (diesGarantia / 365.0) * 0.1;
    }
}

public class SubirNota {
    public static void main(String[] args) {
        ArrayList<Producte> carroCompra = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataActual = new Date();
        String nomSupermercat = "Supermercat XYZ";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Introduir producte");
            System.out.println("2. Passar per caixa");
            System.out.println("3. Mostrar carro de la compra");
            System.out.println("0. Sortir");
            System.out.print("Escull una opció: ");
            int opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    System.out.println("----- Introduir producte -----");
                    System.out.println("Quin tipus de producte vols afegir?");
                    System.out.println("1. Alimentació");
                    System.out.println("2. Tèxtil");
                    System.out.println("3. Electrònica");
                    System.out.println("0. Tornar");
                    System.out.print("Escull una opció: ");
                    int tipusProducte = scanner.nextInt();
                    scanner.nextLine();

                    if (tipusProducte == 0) {
                        break;
                    }

                    System.out.print("Codi de barres: ");
                    String codiBarres = scanner.nextLine();
                    System.out.print("Nom del producte: ");
                    String nomProducte = scanner.nextLine();
                    System.out.print("Preu unitari: ");
                    double preuUnitari = scanner.nextDouble();

                    if (tipusProducte == 1) {
                        System.out.print("Data de caducitat (dd/MM/yyyy): ");
                        String dataCaducitatStr = scanner.next();
                        try {
                            Date dataCaducitat = dateFormat.parse(dataCaducitatStr);
                            Alimentacio alimentacio = new Alimentacio(codiBarres, nomProducte, preuUnitari, dataCaducitat);
                            carroCompra.add(alimentacio);
                        } catch (Exception e) {
                            System.out.println("Format de data incorrecte. No s'ha afegit l'Alimentació.");
                        }
                    } else if (tipusProducte == 2) {
                        System.out.print("Composició tèxtil: ");
                        String composicio = scanner.next();
                        Tèxtil textil = new Tèxtil(codiBarres, nomProducte, preuUnitari, composicio);
                        carroCompra.add(textil);
                    } else if (tipusProducte == 3) {
                        System.out.print("Dies de garantia: ");
                        int diesGarantia = scanner.nextInt();
                        Electrònica electronica = new Electrònica(codiBarres, nomProducte, preuUnitari, diesGarantia);
                        carroCompra.add(electronica);
                    }
                    System.out.println("Producte afegit al carro de la compra.");
                    break;

                case 2:
                    System.out.println("----- Passar per caixa -----");
                    double totalCompra = 0.0;
                    System.out.println("Data de la compra: " + dateFormat.format(dataActual));
                    System.out.println("Nom del supermercat: " + nomSupermercat);
                    System.out.println("Detall de la compra:");

                    ArrayList<Producte> productesVistos = new ArrayList<>();
                    for (Producte producte : carroCompra) {
                        if (!productesVistos.contains(producte)) {
                            int quantitat = contarQuantitat(producte, carroCompra);
                            productesVistos.add(producte);
                            double preuTotal = producte.getPreu() * quantitat;
                            System.out.println("Nom: " + producte.getNom() + " | Unitats: " + quantitat + " | Preu unitari: " + producte.getPreu() + " | Preu total: " + preuTotal);
                            totalCompra += preuTotal;
                        }
                    }
                    System.out.println("Total a pagar: " + totalCompra);
                    carroCompra.clear();
                    break;

                case 3:
                    System.out.println("----- Carro de la compra -----");
                    for (Producte producte : carroCompra) {
                        System.out.println("Nom: " + producte.getNom());
                    }
                    break;

                case 0:
                    System.out.println("Adéu!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opció no vàlida. Torna a escollir.");
            }
        }
    }

    public static int contarQuantitat(Producte producte, ArrayList<Producte> carro) {
        int count = 0;
        for (Producte p : carro) {
            if (p.getCodiBarres().equals(producte.getCodiBarres()) && p.getPreu() == producte.getPreu()) {
                count++;
            }
        }
        return count;
    }
}