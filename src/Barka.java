import javax.swing.*;

public class Barka extends Thread {


//    private static int liczba = 1;
    private String etykieta;
    private int idEtykiety;
    private String status;
    private boolean koniec;
    private int szansa;
    private String polozenie = "wschod";
    private String stan = "b";
    private ThreadGroup barkaGrupa;
    private Most most;
    private JLabel[] polozenieWschod;
    private JLabel[] polozenieZachod;

    @Override
    public void run() {


        while(!koniec) {
            if (!stan.equals("B")) {
                szansa = (int) (Math.random() * 10);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (szansa == 9) {
                    etykieta = "B" + idEtykiety;
                    stan = "B";

                    if(polozenie.equals("wschod"))
                    {

                        for(JLabel pozycja : polozenieWschod)
                        {
                            if(pozycja.getText().toUpperCase().equals(etykieta)) {
                                pozycja.setText(etykieta);
                                System.out.println("Zmieniono bhjsbdv");
                                try {
                                    sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                    else
                    {
                        for(JLabel pozycja : polozenieZachod)
                        {
                            if(pozycja.getText().toUpperCase().equals(etykieta)) {
                                pozycja.setText(etykieta);
                                System.out.println("Zmieniono");
                                try {
                                    sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                }

                status = "oczekujacy";
                Aplikacja.dodajOczekujacaBarke();
//                if(Aplikacja.getOczekujaceBarki()>=2)
//                {
//                    //przejazd
//                }
//                else
//                    notify();
//                przejazd();
            }
            else {
                try {
                    most.przeplyn(polozenie,etykieta,idEtykiety,stan);
                    stan = "b";
                    if(polozenie.equals("wschod"))
                        polozenie="zachod";
                    else
                        polozenie="wschod";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    public Barka(ThreadGroup barkaGrupa, int idEtykiety, Most most, JLabel[] polozenieWschod, JLabel[] polozenieZachod) {
        this.idEtykiety=idEtykiety;
        this.etykieta="b" + idEtykiety;
        this.most=most;
        this.polozenieWschod=polozenieWschod;
        this.polozenieZachod=polozenieZachod;
        this.barkaGrupa=barkaGrupa;
    }


    public String getEtykieta() {
        return etykieta;
    }
}
