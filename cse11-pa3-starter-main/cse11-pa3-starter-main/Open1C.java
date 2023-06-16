class Open1C{
    boolean fever(int temp){
        if (temp > 99.5){
            return true;
        }
        return false;
    }
    String isItCold(int temp){
        if (temp < 70){
            return "It is cold";
        }
        return "It is not cold";
    }

    boolean answer1 = fever(100); //expected answer true
    String answer2 = isItCold(20); //expected answer It is cold
}