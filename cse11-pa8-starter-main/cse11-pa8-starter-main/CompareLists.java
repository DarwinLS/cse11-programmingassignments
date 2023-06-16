import tester.*;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

class PointCompare implements Comparator<Point>{
  public int compare(Point a, Point b){
    if (a.y < b.y){return -1;}
    if (a.y > b.y){return 1;}
    if (a.x < b.x){return -1;}
    if (a.x > b.x){return 1;}
    return 0;
  }
}

class PointDistanceCompare implements Comparator<Point>{
  public int compare(Point a, Point b){
    Point origin = new Point(0,0);
    if (a.distance(origin) < b.distance(origin)){return -1;}
    if (a.distance(origin) > b.distance(origin)){return 1;}
    return 0;
  }
}

class StringCompare implements Comparator<String>{
  public int compare(String a, String b){
    if (a.compareTo(b) < 0){return -1;}
    if (a.compareTo(b) > 0){return 1;}
    return 0;
  }
}

class StringLengthCompare implements Comparator<String>{
  public int compare(String a, String b){
    if (a.length() < b.length()){return -1;}
    if (a.length() > b.length()){return 1;}
    return 0;
  }
}

class BooleanCompare implements Comparator<Boolean>{
  public int compare(Boolean a, Boolean b){
    if (a == b){return 0;}
    if (a){return 1;}
    return -1;
  }
}

class CompareLists{
  public <E> E minimum(List<E> l, Comparator<E> c){
    E min = l.get(0);
    for (E x : l){
      if (c.compare(x,min) < 0){min = x;}
    }
    return min;
  }

  public <E> E minimum(E[] arr, Comparator<E> c){
    E min = arr[0];
    for (E x : arr){
      if (c.compare(x,min) < 0){min = x;}
    }
    return min;
  }

  public <E> List<E> greaterThan(List<E> l, Comparator<E> c, E e){
    List<E> returnList = new ArrayList<>();
    for (E x : l){
      if (c.compare(x,e) > 0){returnList.add(x);}
    }
    return returnList;
  }

  public <E> boolean inOrder(List<E> l, Comparator<E> c){
    if (l.contains(null)){throw new IllegalArgumentException("null value in list");}
    for (int i = 1; i < l.size(); i++){
      if (c.compare(l.get(i-1),l.get(i)) > 0){return false;}
    }
    return true;
  }

  public <E> boolean inOrder(E[] arr, Comparator<E> c){
      for (int i = 1; i < arr.length; i++){
        if (arr[i] == null){
          throw new IllegalArgumentException("null value in array");
        }
        else if (c.compare(arr[i-1],arr[i]) > 0){return false;}
      }
      return true;
  }

  public <E> List<E> merge(Comparator<E> c, List<E> l1, List<E> l2){
    if (l1.contains(null)){throw new IllegalArgumentException("null value in first list");}
    if (l2.contains(null)){throw new IllegalArgumentException("null value in second list");}
    List<E> returnList = new ArrayList<>();
    returnList.addAll(l1);
    returnList.addAll(l2);
    returnList.sort(c);
    return returnList;
  }

  void testCompareLists(Tester t){
    PointCompare pc = new PointCompare();
    PointDistanceCompare pdc = new PointDistanceCompare();
    StringCompare sc = new StringCompare();
    StringLengthCompare slc = new StringLengthCompare();
    BooleanCompare bc = new BooleanCompare();
    List<Point> l1 = new ArrayList<>();
    l1.add(new Point(0,1));
    l1.add(new Point(3,5));
    l1.add(new Point(2,0));
    Point[] arr1 = {new Point(3,4),new Point(5,5), new Point(1,2)};
    List<String> l2 = new ArrayList<>();
    l2.add("bob");
    l2.add("refrigerator");
    l2.add("brother");
    String[] arr2 = {"dude", "that", "is", "crazy"};
    List<Boolean> l3 = new ArrayList<>();
    l3.add(false);
    l3.add(true);
    l3.add(false);
    Boolean[] arr3 = {false, false, true};
    CompareLists test = new CompareLists();

    t.checkExpect(test.minimum(l1,pc),new Point(2,0));
    t.checkExpect(test.minimum(l1,pdc),new Point(0,1));
    t.checkExpect(test.minimum(l2,sc),"bob");
    t.checkExpect(test.minimum(arr2,sc),"crazy");
    t.checkExpect(test.minimum(arr2,slc),"is");
    t.checkExpect(test.minimum(arr3,bc),false);

    List<String> nullList1 = new ArrayList<>();
    nullList1.add("wow");
    nullList1.add("no way");
    nullList1.add(null);
    List<String> nullList2 = new ArrayList<>();
    nullList2.add("no cap");
    nullList2.add(null);
    nullList2.add("fo real");
    String[] nullArray = {"bruh",null,"lol"};

    t.checkException(new IllegalArgumentException("null value in list"),this,"inOrder",nullList1,slc);
    t.checkExpect(test.inOrder(l3,bc),false);
    t.checkExpect(test.inOrder(l2,slc),false);
    t.checkException(new IllegalArgumentException("null value in array"),this,"inOrder",nullArray,sc);
    t.checkExpect(test.inOrder(arr3,bc),true);
    t.checkExpect(test.inOrder(arr1,pdc),false);

    List<String> l4 = new ArrayList<>();
    l4.add("joe");
    l4.add("charles");
    l4.add("water");
    List<Boolean> l5 = new ArrayList<>();
    l5.add(false);
    l5.add(true);
    l5.add(true);
    List<String> expectL1 = new ArrayList<>();
    expectL1.add("bob");
    expectL1.add("brother");
    expectL1.add("charles");
    expectL1.add("joe");
    expectL1.add("refrigerator");
    expectL1.add("water");
    List<Boolean> expectL2 = new ArrayList<>();
    expectL2.add(false);
    expectL2.add(false);
    expectL2.add(false);
    expectL2.add(true);
    expectL2.add(true);
    expectL2.add(true);

    t.checkException(new IllegalArgumentException("null value in second list"),this,"merge",sc,l2,nullList2);
    t.checkExpect(test.merge(sc,l2,l4),expectL1);
    t.checkExpect(test.merge(bc,l3,l5),expectL2);
  }

  void testComparator(Tester t){
    PointCompare pc = new PointCompare();

    t.checkExpect(pc.compare(new Point(0,1),new Point(3,3)),-1);
    t.checkExpect(pc.compare(new Point(3,6),new Point(5,6)),-1);
    t.checkExpect(pc.compare(new Point(4,5),new Point(2,5)),1);
    t.checkExpect(pc.compare(new Point(2,3),new Point(2,3)),0);

    PointDistanceCompare pdc = new PointDistanceCompare();

    t.checkExpect(pdc.compare(new Point(1,1),new Point(2,2)),-1);
    t.checkExpect(pdc.compare(new Point(3,3),new Point(2,2)),1);
    t.checkExpect(pdc.compare(new Point(1,3),new Point(7,2)),-1);
    t.checkExpect(pdc.compare(new Point(-1,-1),new Point(1,1)),0);

    StringCompare sc = new StringCompare();

    t.checkExpect(sc.compare("bruh","noob"),-1);
    t.checkExpect(sc.compare("bruh","bruh"),0);
    t.checkExpect(sc.compare("wow","crazy"),1);
    t.checkExpect(sc.compare("ten","text"),-1);

    StringLengthCompare slc = new StringLengthCompare();

    t.checkExpect(slc.compare("bad","family"),-1);
    t.checkExpect(slc.compare("computer","parts"),1);
    t.checkExpect(slc.compare("insane","insane"),0);
    t.checkExpect(slc.compare("ez","ez clap"),-1);

    BooleanCompare bc = new BooleanCompare();

    t.checkExpect(bc.compare(true,false),1);
    t.checkExpect(bc.compare(false,false),0);
    t.checkExpect(bc.compare(true,true),0);
    t.checkExpect(bc.compare(false,true),-1);
  }
}