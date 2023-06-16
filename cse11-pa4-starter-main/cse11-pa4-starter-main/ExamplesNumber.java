import tester.*;

interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    String toString();
    double toDouble();
}

class WholeNumber implements Number{
    int n;

    WholeNumber(int i){
        n = i;
    }

    public int numerator(){
        return n;
    }
    public int denominator(){
        return 1;
    }
    public Number add(Number other){
        return new Fraction(other.numerator()+other.denominator()*this.n,other.denominator()); 
    }
    public Number multiply(Number other){
        return new Fraction(this.n*other.numerator(),other.denominator());
    }
    public String toString(){
        return Integer.toString(n);
    }
    public double toDouble(){
        return n;
    }
}

class Fraction implements Number{
    int n;
    int d;

    Fraction(int i,int j){
        n = i;
        d = j;
    }

    public int numerator(){
        return n;
    }
    public int denominator(){
        return d;
    }
    public Number add(Number other){
        return new Fraction(this.d*other.numerator()+other.denominator()*this.n,this.d*other.denominator());
    }
    public Number multiply(Number other){
        return new Fraction(this.n*other.numerator(),this.d*other.denominator());
    }
    public String toString(){
        return Integer.toString(n) + "/" + Integer.toString(d);
    }
    public double toDouble(){
        double num = n;
        double den = d;
        return num/den;
    }
}

class ExamplesNumber{
    WholeNumber n1 = new WholeNumber(2);
    Fraction n2 = new Fraction(2,5);
    int t1 = n1.numerator();
    int t2 = n2.numerator();
    int t3 = n1.denominator();
    int t4 = n2.denominator();
    Number t5 = n1.add(n2);
    Number t6 = n2.add(n1);
    Number t7 = n1.multiply(n2);
    Number t8 = n2.multiply(n1);
    String t9 = n1.toString();
    String t10 = n2.toString();
    double t11 = n1.toDouble();
    double t12 = n2.toDouble();
    
    //Exploration
    double a1 = 0.1 + 0.2 + 0.3;
    double a2 = 0.1 + (0.2 + 0.3);
    Fraction f1 = new Fraction(1,10);
    Fraction f2 = new Fraction(1,5);
    Fraction f3 = new Fraction(3,10);
    Number c1 = f1.add(f2);
    Number c2 = f3.add(c1);
    String a3 = c2.toString();
    Number c3 = f2.add(f3);
    Number c4 = f1.add(c3);
    String a4 = c4.toString();

    void testerMethod(Tester t){
        t.checkExpect(t1,2);
        t.checkExpect(t2,2);
        t.checkExpect(t3,1);
        t.checkExpect(t4,5);
        t.checkExpect(t5,new Fraction(12,5));
        t.checkExpect(t6,new Fraction(12,5));
        t.checkExpect(t7,new Fraction(4,5));
        t.checkExpect(t8,new Fraction(4,5));
        t.checkExpect(t9,"2");
        t.checkExpect(t10,"2/5");
        t.checkExpect(t11,2.0);
        double d1 = 2;
        double d2 = 5;
        t.checkExpect(t12,d1/d2);
    }
}