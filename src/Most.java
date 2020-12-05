import javax.swing.*;

public class Most extends Thread {

    private JLabel mostLabel;
    private JLabel[] polozeniePolnoc;
    private JLabel[] polozeniePoludnie;
    private JLabel[] polozenieWschod;
    private JLabel[] polozenieZachod;
    private int oczekujaceBarki = 0;
    private int czasOczekiwania = 0;


    public void dodajOczekujacaBarke()
    {
        oczekujaceBarki++;
    }
    public Most(JLabel mostLabel, JLabel[] polozeniePolnoc, JLabel[] polozeniePoludnie, JLabel[] polozenieWschod, JLabel[] polozenieZachod)
    {
        this.mostLabel=mostLabel;
        this.polozeniePolnoc=polozeniePolnoc;
        this.polozeniePoludnie=polozeniePoludnie;
        this.polozenieWschod=polozenieWschod;
        this.polozenieZachod=polozenieZachod;
    }

    public synchronized void przejazd(String polozenie,String etykieta, int idEtykiety, String stan) throws InterruptedException {
        while (oczekujaceBarki>=2 || czasOczekiwania==5)
            wait();
        if(polozenie.equals("polnoc")) {
            for (JLabel pozycja : polozeniePolnoc) {
                if (pozycja.getText().toUpperCase().equals(etykieta)) {
                    pozycja.setText("--");
                    break;
                }
            }
            mostLabel.setText("<html>[" + etykieta + "]<br>[ ]<br>[ ] ");
            sleep(500);
            mostLabel.setText("<html>[ ]<br>[" + etykieta + "]<br>[ ] ");
            sleep(500);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[" + etykieta + "] ");
            sleep(500);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[ ] ");
            for (JLabel pozycja : polozeniePoludnie) {
                if (pozycja.getText().equals("--")) {
                    etykieta = "p" + idEtykiety;
                    stan="p";
                    pozycja.setText(etykieta);
                    sleep(500);
                    break;
                }
            }
            if(oczekujaceBarki==1)
                czasOczekiwania++;
        }
        else
        {
            for (JLabel pozycja : polozeniePoludnie) {
                if (pozycja.getText().toUpperCase().equals(etykieta)) {
                    pozycja.setText("--");
                    break;
                }
            }
            mostLabel.setText("<html>[ ]<br>[ ]<br>[" + etykieta + "] ");
            sleep(500);
            mostLabel.setText("<html>[ ]<br>[" + etykieta + "]<br>[ ] ");
            sleep(500);
            mostLabel.setText("<html>[" + etykieta + "]<br>[ ]<br>[ ] ");
            sleep(500);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[ ] ");
            for (JLabel pozycja : polozeniePolnoc) {
                if (pozycja.getText().equals("--")) {
                    etykieta = "p" + idEtykiety;
                    stan="p";
                    pozycja.setText(etykieta);
                    sleep(500);
                    break;
                }
            }
            sleep(500);
            if(oczekujaceBarki==1)
                czasOczekiwania++;
        }
        System.out.println(czasOczekiwania);
        if(oczekujaceBarki<2 && czasOczekiwania!=4)
            notify();
    }
    public synchronized void przeplyn(String polozenie, String etykieta,int idEtykiety,String stan) throws InterruptedException {


        while (oczekujaceBarki<2 && czasOczekiwania<4)
        {
            wait();
        }
        mostLabel.setText("<html>[ ]<br><br>[ ] ");
        sleep(500);
        if(polozenie.equals("wschod"))
        {
            for(JLabel pozycja : polozenieWschod)
            {
                if(pozycja.getText().toUpperCase().equals(etykieta)){
                    pozycja.setText("--");
                    break;
                }
            }

            mostLabel.setText("<html>[ ]<br>" + etykieta +"<br>[ ] ");
            sleep(500);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[ ] ");
            for (JLabel pozycja : polozenieZachod) {
                if (pozycja.getText().equals("--")) {
                    etykieta = "b" + idEtykiety;
                    stan="b";
                    pozycja.setText(etykieta);
                    break;
                }
            }
            sleep(500);
            oczekujaceBarki--;
            czasOczekiwania=0;
        }
        else {
            for(JLabel pozycja : polozenieZachod)
            {
                if(pozycja.getText().toUpperCase().equals(etykieta)){
                    pozycja.setText("--");
                    break;
                }
            }

            mostLabel.setText("<html>[ ]<br>" + etykieta +"<br>[ ] ");
            sleep(500);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[ ] ");
            for (JLabel pozycja : polozenieWschod) {
                if (pozycja.getText().equals("--")) {
                    etykieta = "b" + idEtykiety;
                    stan="b";
                    pozycja.setText(etykieta);
                    break;
                }
            }
            sleep(500);
            polozenie="polnoc";
            oczekujaceBarki--;
            czasOczekiwania=0;

        }
        if(oczekujaceBarki>=2 || czasOczekiwania>=5)
            notify();
    }
}
