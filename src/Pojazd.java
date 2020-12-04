import javax.swing.*;

public class Pojazd extends Thread {

//    private static int liczba = 1;
    private String etykieta;
    private int idEtykiety;
    private String status = "podroz";
    private int szansa;
    private boolean koniec;
    private JLabel most;
    private String stan = "p";
    private JLabel[] polozeniePolnoc;
    private JLabel[] polozeniePoludnie;
    private String polozenie = "polnoc";
    private ThreadGroup pojazdGrupa;
    @Override
    public void run() {

        while(!koniec) {
            if (!stan.equals("P")) {
                szansa = (int) (Math.random() * 3);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(szansa);
                if (szansa == 2) {
                    etykieta = "P" + idEtykiety;
                    stan="P";
                    status = "oczekujacy";
                    if(polozenie.equals("polnoc"))
                    {
                        for(JLabel pozycja : polozeniePolnoc)
                        {
                            if(pozycja.getText().equals(etykieta)) {
                                pozycja.setText(etykieta);
                                break;
                            }
                        }
                    }

                }

                }
            else {

                try {
                    przejazd();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Pojazd(ThreadGroup pojazdGrupa, int idEtykiety, JLabel most,JLabel[] polozeniePolnoc,JLabel[] polozeniePoludnie) {
        this.idEtykiety=idEtykiety;
        this.etykieta="p" + idEtykiety;
        this.most=most;
        this.polozeniePolnoc=polozeniePolnoc;
        this.polozeniePoludnie=polozeniePoludnie;
        this.pojazdGrupa=pojazdGrupa;
//        liczba++;
    }

    public String getEtykieta() {
        return etykieta;
    }

    public synchronized void przejazd() throws InterruptedException {
        if(polozenie.equals("polnoc")) {
            for (JLabel pozycja : polozeniePolnoc) {
                if (pozycja.getText().toUpperCase().equals(etykieta)) {
                    pozycja.setText("--");
                    break;
                }
            }
            most.setText("<html>[" + etykieta + " ]<br>[ ]<br>[ ] ");
            sleep(2000);
            most.setText("<html>[ ]<br>[" + etykieta + "]<br>[ ] ");
            sleep(2000);
            most.setText("<html>[ ]<br>[ ]<br>[" + etykieta + " ] ");
            sleep(2000);
            most.setText("<html>[ ]<br>[ ]<br>[ ] ");
            for (JLabel pozycja : polozeniePoludnie) {
                if (pozycja.getText().equals("--")) {
                    etykieta = "p" + idEtykiety;
                    stan="p";
                    pozycja.setText(etykieta);
                    break;
                }
            }
            polozenie="poludnie";
        }
        else
        {
            for (JLabel pozycja : polozeniePoludnie) {
                if (pozycja.getText().toUpperCase().equals(etykieta)) {
                    pozycja.setText("--");
                    break;
                }
            }
            most.setText("<html>[" + etykieta + " ]<br>[ ]<br>[ ] ");
            sleep(2000);
            most.setText("<html>[ ]<br>[ ]<br>[" + etykieta + " ] ");
            sleep(2000);
            most.setText("<html>[ ]<br>[" + etykieta + "]<br>[ ] ");
            sleep(2000);
            most.setText("<html>[ ]<br>[ ]<br>[ ] ");
            for (JLabel pozycja : polozeniePolnoc) {
                if (pozycja.getText().equals("--")) {
                    etykieta = "p" + idEtykiety;
                    stan="p";
                    pozycja.setText(etykieta);
                    break;
                }
            }
            polozenie="polnoc";
        }

    }
}
