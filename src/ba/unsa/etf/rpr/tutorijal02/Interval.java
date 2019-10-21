package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetna;
    private double krajnja;
    private boolean pocetnaPripada;
    private boolean krajnjaPripada;

    public void setPocetna(double pocetna) {
        this.pocetna = pocetna;
    }

    public void setKrajnja(double krajnja) {
        this.krajnja = krajnja;
    }

    public void setPocetnaPripada(boolean pocetnaPripada) {
        this.pocetnaPripada = pocetnaPripada;
    }

    public void setKrajnjaPripada(boolean krajnjaPripada) {
        this.krajnjaPripada = krajnjaPripada;
    }

    public double getPocetna() {
        return pocetna;
    }

    public double getKrajnja() {
        return krajnja;
    }

    public boolean isPocetnaPripada() {
        return pocetnaPripada;
    }

    public boolean isKrajnjaPripada() {
        return krajnjaPripada;
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

    public Interval intersect(Interval interval1)
    {
        Interval i=new Interval();
        double t1=this.getPocetna();
        double t2=this.getKrajnja();
        double t3=interval1.getPocetna();
        double t4=interval1.getKrajnja();

        if((t1<t3 && t2<t3) || (t1>t4 && t2>t4))
        {
            return new Interval();
        }
        if(t1<=t3 && t2>t3 && t2<=t4)
        {
            //printf("Rezultantni interval je [%g,%g].", c, b);
            i.setPocetna(t3); i.setKrajnja(t2); i.setPocetnaPripada(interval1.isPocetnaPripada()); i.setKrajnjaPripada(this.isKrajnjaPripada());
            return i;
        }
        if(t1>=t3 && t2>=t3 && t1<=t4 && t2<=t4)
        {
            //printf("Rezultantni interval je [%g,%g].", a, b);
            i.setPocetna(t1); i.setKrajnja(t2); i.setPocetnaPripada(this.isPocetnaPripada()); i.setKrajnjaPripada(this.isKrajnjaPripada());
            return i;
        }
        if(t1>=t3 && t2>t4 && t1<t4)
        {
            //printf("Rezultantni interval je [%g,%g].", a, d);
            i.setPocetna(t1); i.setKrajnja(t4); i.setPocetnaPripada(this.isPocetnaPripada()); i.setKrajnjaPripada(interval1.isKrajnjaPripada());
            return i;
        }
        if(t3>t1 && t3<t2 && t4>t1 && t4<t2)
        {
            //printf("Rezultantni interval je [%g,%g].", c, d);
            i.setPocetna(t3); i.setKrajnja(t4); i.setPocetnaPripada(interval1.isPocetnaPripada()); i.setKrajnjaPripada(interval1.isKrajnjaPripada());
            return i;
        }
        return i;
    }

    public static Interval intersect(Interval interval1, Interval interval2)
    {
        Interval i=new Interval();
        double t1=interval1.getPocetna();
        double t2=interval1.getKrajnja();
        double t3=interval2.getPocetna();
        double t4=interval2.getKrajnja();

        if((t1<t3 && t2<t3) || (t1>t4 && t2>t4))
        {
            return new Interval();
        }
        if(t1<=t3 && t2>t3 && t2<=t4)
        {
            //printf("Rezultantni interval je [%g,%g].", c, b);
            i.setPocetna(t3); i.setKrajnja(t2); i.setPocetnaPripada(interval2.isPocetnaPripada()); i.setKrajnjaPripada(interval1.isKrajnjaPripada());
            return i;
        }
        if(t1>=t3 && t2>=t3 && t1<=t4 && t2<=t4)
        {
            //printf("Rezultantni interval je [%g,%g].", a, b);
            i.setPocetna(t1); i.setKrajnja(t2); i.setPocetnaPripada(interval1.isPocetnaPripada()); i.setKrajnjaPripada(interval1.isKrajnjaPripada());
            return i;
        }
        if(t1>=t3 && t2>t4 && t1<t4)
        {
            //printf("Rezultantni interval je [%g,%g].", a, d);
            i.setPocetna(t1); i.setKrajnja(t4); i.setPocetnaPripada(interval1.isPocetnaPripada()); i.setKrajnjaPripada(interval2.isKrajnjaPripada());
            return i;
        }
        if(t3>t1 && t3<t2 && t4>t1 && t4<t2)
        {
            //printf("Rezultantni interval je [%g,%g].", c, d);
            i.setPocetna(t3); i.setKrajnja(t4); i.setPocetnaPripada(interval2.isPocetnaPripada()); i.setKrajnjaPripada(interval2.isKrajnjaPripada());
            return i;
        }
        return i;
    }

    @Override
    public boolean equals(Object interval1){
        Interval i = (Interval) interval1;

        return (this.pocetna==i.pocetna && this.krajnja==i.krajnja && this.pocetnaPripada==i.pocetnaPripada && this.krajnjaPripada == i.krajnjaPripada);
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
}
