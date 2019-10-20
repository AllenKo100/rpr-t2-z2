package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetna;
    private double krajnja;

    public double getPocetna() {
        return pocetna;
    }

    public void setPocetna(double pocetna) {
        this.pocetna = pocetna;
    }

    public double getKrajnja() {
        return krajnja;
    }

    public void setKrajnja(double krajnja) {
        this.krajnja = krajnja;
    }

    private boolean pocetnaPripada;
    private boolean krajnjaPripada;

    public boolean isPocetnaPripada() {
        return pocetnaPripada;
    }

    public void setPocetnaPripada(boolean pocetnaPripada) {
        this.pocetnaPripada = pocetnaPripada;
    }

    public boolean isKrajnjaPripada() {
        return krajnjaPripada;
    }

    public void setKrajnjaPripada(boolean krajnjaPripada) {
        this.krajnjaPripada = krajnjaPripada;
    }

    public Interval (double t1, double t2, boolean t1Pripada, boolean t2Pripada){
        if(t1>t2) throw new IllegalArgumentException("Greška, pogresna vrijednost !");

        this.pocetna=t1;
        this.krajnja=t2;
        this.pocetnaPripada=t1Pripada;
        this.krajnjaPripada=t2Pripada;
    }

    public Interval(){
        this.pocetna=0;
        this.krajnja=0;
        this.pocetnaPripada=false;
        this.krajnjaPripada=false;
    }

    public boolean isNull(){
        return (this.krajnja - this.pocetna == 0 && this.pocetnaPripada==false && this.krajnjaPripada==false);
    }

    public boolean isIn(double tacka){
        if(tacka>=this.pocetna && tacka<=this.krajnja && this.pocetnaPripada && this.krajnjaPripada) return true;
        if(tacka>=this.pocetna && tacka<this.krajnja && this.pocetnaPripada && !this.krajnjaPripada) return true;
        if(tacka>this.pocetna && tacka<=this.krajnja && !this.pocetnaPripada && this.krajnjaPripada) return true;
        else return (tacka>this.pocetna && tacka<this.krajnja && !this.pocetnaPripada && !this.krajnjaPripada);
    }

    @Override
    public String toString(){
        String zagradaPrva="";
        String zagradaDruga="";

        if(this.isNull()) return "()";
        if(this.pocetnaPripada) zagradaPrva="[";
        if(this.krajnjaPripada) zagradaDruga="]";
        if(this.pocetnaPripada==false) zagradaPrva="(";
        if(this.krajnjaPripada==false) zagradaDruga=")";

        return (zagradaPrva + pocetna + "," + krajnja + zagradaDruga);
    }

    @Override
    public boolean equals(Object interval1){
        Interval interval = (Interval) interval1;

        return (this.pocetna==interval.pocetna && this.krajnja==interval.krajnja && this.pocetnaPripada==interval.pocetnaPripada && this.krajnjaPripada == interval.krajnjaPripada);
    }
}
