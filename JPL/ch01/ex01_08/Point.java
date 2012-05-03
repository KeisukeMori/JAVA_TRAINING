package ch01.ex01_08;

public class Point {
    double x;
    double y;
    
    public void clear() {
    	this.x = 0.0;
    	this.y = 0.0;
    }
    
    public double distance(Point that) {
    	double xdiff = x - that.x;
    	double ydiff = y - that.y;
    	
    	return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
    }
    
    public void setLocation(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public static void main(String[] args) {
        Point point_1 = new Point();
        Point point_2 = new Point();
        
        point_1.x = 123.0;
        point_1.y = 456.0;
       
        point_2.x = 78.0;
        point_2.y = 90.0;

        point_2.setLocation(point_1);
       
        System.out.println("point_2 location: " + point_2.x + ", " + point_2.y);
    }
}
