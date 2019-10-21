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

    public Interval intersect(Interval i){
        double x1=this.getPocetna();
        double y1=this.getKrajnja();
        double x2=i.getPocetna();
        double y2=i.getKrajnja();

        Interval i1 = new Interval();

        if((x1<x2 && x1<y1) || (x1>y2 && y1>y2)){
            return new Interval();
        }

        if(x1<=x2 && y1>x2 && y1<=y2){
            i1.setPocetna(x2); i1.setKrajnja(y1); i1.setPocetnaPripada(i.isPocetnaPripada()); i1.setKrajnjaPripada(this.isKrajnjaPripada());
            return i1;
        }

        if(x1>=y1 && y1>=x2 && x1<=y2 && y1<=y2)
        {
            i.setPocetna(x1); i.setKrajnja(y1); i.setPocetnaPripada(this.isPocetnaPripada()); i.setKrajnjaPripada(this.isPocetnaPripada());
            return i1;
        }
        if(x1>=x2 && y1>y2 && x1<y2)
        {
            i.setPocetna(x1); i.setKrajnja(y2); i.setPocetnaPripada(this.isPocetnaPripada()); i.setKrajnjaPripada(i.isPocetnaPripada());
            return i1;
        }
        if(x2>x1 && x2<y1 && y2>x1 && y2<y1)
        {
            i.setPocetna(x2); i.setKrajnja(y2); i.setPocetnaPripada(i.isPocetnaPripada()); i.setKrajnjaPripada(i.isKrajnjaPripada());
            return i1;
        }
        return i;
    }

    public static Interval intersect(Interval i1, Interval i2){
        double x1=i1.getPocetna();
        double y1=i1.getKrajnja();
        double x2=i2.getPocetna();
        double y2=i2.getKrajnja();

        Interval i = new Interval();

        if((x1<x2 && x1<y1) || (x1>y2 && y1>y2)){
            return new Interval();
        }

        if(x1<=x2 && y1>x2 && y1<=y2){
            i.setPocetna(x2); i.setKrajnja(y1); i.setPocetnaPripada(i.isPocetnaPripada()); i.setKrajnjaPripada(i1.isKrajnjaPripada());
            return i;
        }

        if(x1>=y1 && y1>=x2 && x1<=y2 && y1<=y2)
        {
            i.setPocetna(x1); i.setKrajnja(y1); i.setPocetnaPripada(i1.isPocetnaPripada()); i.setKrajnjaPripada(i1.isPocetnaPripada());
            return i;
        }
        if(x1>=x2 && y1>y2 && x1<y2)
        {
            i.setPocetna(x1); i.setKrajnja(y2); i.setPocetnaPripada(i1.isPocetnaPripada()); i.setKrajnjaPripada(i.isPocetnaPripada());
            return i;
        }
        if(x2>x1 && x2<y1 && y2>x1 && y2<y1)
        {
            i.setPocetna(x2); i.setKrajnja(y2); i.setPocetnaPripada(i.isPocetnaPripada()); i.setKrajnjaPripada(i.isKrajnjaPripada());
            return i;
        }
        return i;
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
