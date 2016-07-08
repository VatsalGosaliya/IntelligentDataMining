package helperClasses;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 
 * This is a universal class for this entire project.
 * Therefore it contains all the attributes and methods required in any of the programs.
 * Hence, you might find some attributes and methods that are irrelevant to some programs.
 * Please ignore that. In case you don't need them, you can delete them.
 *
 */
@SuppressWarnings("serial")
public class DataPoint implements Serializable{
	public double x;
	public double y;
	public double dist;
	
	public String outlook;
	public String temperature;
	public String humidity;
	public String wind;
	
	public String C;
	
	public DataPoint(double x) {
		this.x = x;
	}
	
	public DataPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public DataPoint(double x, double y, String C) {
		this.x = x;
		this.y = y;
		this.C = C;
	}
	
	public DataPoint(String O, String T, String H, String W){
		this.outlook = O;
		this.temperature = T;
		this.humidity = H;
		this.wind = W;
	}
	
	public DataPoint(String O, String T, String H, String W, String C){
		this.outlook = O;
		this.temperature = T;
		this.humidity = H;
		this.wind = W;
		this.C = C;
	}
	
	public double euclideanDistance(DataPoint a, DataPoint b){
		return Math.sqrt(Math.pow((a.x-b.x),2) + Math.pow((a.y-b.y),2));
	}
	
	public static Comparator<DataPoint> eucDist = new Comparator<DataPoint>() {
		public int compare(DataPoint s1, DataPoint s2) {
		   return (int) (s1.dist - s2.dist);
	   }};
	   
	
	public static void main(String args[]){
		System.out.println("Data Point Running");
	}
}
