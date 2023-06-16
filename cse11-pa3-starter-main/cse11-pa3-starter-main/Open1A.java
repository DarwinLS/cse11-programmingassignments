class square{
    double area;
    
    square(double a){
        area = a;
    }
}

class circle{
    double area;

    circle(double b){
        area = b;
    }

}

class Open1A{
    square theSquare = new square(8);
    circle theCircle = new circle(5);

    double answer1 = theSquare.area; //expected answer 8
    double answer2 = theCircle.area; //expected answer 5
}