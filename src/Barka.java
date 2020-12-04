public class Barka extends Thread {


//    private static int liczba = 1;
    private String etykieta;
    private int idEtykiety;
    private String status;
    private boolean koniec;
    private int szansa;
    private ThreadGroup barkaGrupa;


    @Override
    public void run() {


        while(!koniec) {
            if (!etykieta.equals("B" + idEtykiety)) {
                szansa = (int) (Math.random() * 5);
                if (szansa == 5)
                    etykieta = "B" + idEtykiety;
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
        }

    }


    public Barka(int idEtykiety) {
        this.idEtykiety=idEtykiety;
        etykieta="b" + idEtykiety;
//        liczba++;
    }

    public String getEtykieta() {
        return etykieta;
    }
}
