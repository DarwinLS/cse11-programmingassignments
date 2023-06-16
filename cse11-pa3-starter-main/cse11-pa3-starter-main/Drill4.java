class Drill4{
    String phaseOfWater(int num){
        if (num >= 100){
            return "vapor";
        } if (num <= 0){
            return "solid";
        } 
        return "liquid";
    }

    int maxDifference(int one,int two,int three){
        int largest = Math.max(one,two);
        largest = Math.max(largest,three);
        int smallest = Math.min(one,two);
        smallest = Math.min(smallest,three);
        return largest - smallest;
    }

    double ringArea(double r1,double r2){
        return ((r2 * r2) - (r1 * r1)) * Math.PI;
    }

    String answer1 = phaseOfWater(322); //expected answer vapor
    String answer2 = phaseOfWater(-132); //expected answer solid
    String answer3 = phaseOfWater(0); //expected answer solid
    int answer4 = maxDifference(-10,-11,22); //expected answer 33
    int answer5 = maxDifference(1,0,0); //expected answer 1
    int answer6 = maxDifference(-2,-2,100); //expected answer 102
    double answer7 = ringArea(2, 3); //expected answer 15.708
    double answer8 = ringArea(1, 5); //expected answer 75.398
    double answer9 = ringArea(3, 4.5); //expected answer 35.343

}