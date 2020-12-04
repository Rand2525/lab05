import javax.swing.*;

public class Most extends Thread {

    private JLabel mostLabel;

    public Most(JLabel mostLabel)
    {
        this.mostLabel=mostLabel;
    }

    public synchronized void przejazd(String polozenie,JLabel[] polozeniePolnoc,JLabel[] polozeniePoludnie,String etykieta, int idEtykiety, String stan) throws InterruptedException {
        if(polozenie.equals("polnoc")) {
            for (JLabel pozycja : polozeniePolnoc) {
                if (pozycja.getText().toUpperCase().equals(etykieta)) {
                    pozycja.setText("--");
                    break;
                }
            }
            mostLabel.setText("<html>[" + etykieta + " ]<br>[ ]<br>[ ] ");
            sleep(2000);
            mostLabel.setText("<html>[ ]<br>[" + etykieta + "]<br>[ ] ");
            sleep(2000);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[" + etykieta + " ] ");
            sleep(2000);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[ ] ");
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
            mostLabel.setText("<html>[ ]<br>[ ]<br>[" + etykieta + " ] ");
            sleep(2000);
            mostLabel.setText("<html>[ ]<br>[" + etykieta + "]<br>[ ] ");
            sleep(2000);
            mostLabel.setText("<html>[" + etykieta + " ]<br>[ ]<br>[ ] ");
            sleep(2000);
            mostLabel.setText("<html>[ ]<br>[ ]<br>[ ] ");
            for (JLabel pozycja : polozeniePolnoc) {
                if (pozycja.getText().equals("--")) {
                    etykieta = "p" + idEtykiety;
                    stan="p";
                    pozycja.setText(etykieta);
                    break;
                }
            }
            sleep(2000);
            polozenie="polnoc";
        }

    }
}
