class Point {
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    boolean belowLeftOf(Point other) {
      return this.x <= other.x && this.y <= other.y;
    }
    boolean aboveRightOf(Point other) {
      return this.x >= other.x && this.y >= other.y;
    }
    double distance(Point other) {
      return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}

public class ClosestPoints {
    public static void main(String[] args){
        double smallest = 0;
        int temp = 0;
        Point a = new Point(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
    }
}

