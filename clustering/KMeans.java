package clustering;
import java.util.ArrayList;
import java.util.List;
import helperClasses.DataPoint;
import helperClasses.Serialization;

public class KMeans {
	static int K = 3;
	public List<Double> means = new ArrayList<Double>();
	public List<List<DataPoint>> clusters = new ArrayList<List<DataPoint>>();
	
	public void importClusterData(){
		ArrayList<List<DataPoint>> c = null;
		Serialization S = new Serialization();
		clusters = S.clustDeserialize(c, "kMeansClusterData.ser");
	}
	
	public double getClusterMean(int current){
		double sum = 0, mean=clusters.get(current).get(0).x;
		for(int i=1; i<clusters.get(current).size(); i++)
			sum = sum + clusters.get(current).get(i).x;
		mean = sum / (clusters.get(current).size()-1);
		return mean;
	}
	
	public List<List<DataPoint>> reviseClusters(){
		for(int i=0; i<K; i++){
			for(int j=1; j<clusters.get(i).size(); j++){
				int current = i;
				int closest = searchClosestCluster(clusters.get(current).get(j));
				if(closest == current){
					continue;
				}
				else{
					DataPoint toBeTransferred = clusters.get(current).remove(j);
					clusters.get(current).get(0).x = getClusterMean(current);
					updateCluster(toBeTransferred, closest);
				}
			}
		}
		
		return clusters;
	}
	
	public void updateCluster(DataPoint newData, int closest){
		//Initial means for 3 clusters. Not really useful after the first execution. Change the mean values by uncommenting.
		/*means.add(3.0);
		means.add(4.0);
		means.add(6.0);
		for(int i=0; i<K; i++){
			clusters.add(new ArrayList<DataPoint>());
		}
		for(int i=0; i<K; i++){
			clusters.get(i).add(new DataPoint(means.get(i)));
		}*/

		clusters.get(closest).add(newData);
		clusters.get(closest).get(0).x = getClusterMean(closest);
		
		clusters = reviseClusters();
		
		Serialization S = new Serialization();
		S.clustSerialize(clusters, "kMeansClusterData.ser");
	}
	
	public void dispayClusterData(){
		ArrayList<List<DataPoint>> c = null;
		Serialization S = new Serialization();
		c = S.clustDeserialize(c, "kMeansClusterData.ser");
		System.out.println("Clusters : ");
		for(int i=0; i<K; i++){
			System.out.print("[");
			for(int j=1; j<c.get(i).size(); j++){
				System.out.print(c.get(i).get(j).x+", ");
			}
			System.out.println("]\tMean = "+c.get(i).get(0).x);
		}
	}
	
	public int searchClosestCluster(DataPoint input){
		double min = Math.abs(input.x - clusters.get(0).get(0).x);
		int closest = 0;
		for(int i=1; i<K; i++){
			if(Math.abs(input.x - clusters.get(i).get(0).x) < min){
				min = Math.abs(input.x - clusters.get(i).get(0).x);
				closest = i;
				continue;
			}
		}
		
		return closest;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KMeans sol = new KMeans();
		sol.importClusterData();
	    
		int closest=-1;
		DataPoint input = new DataPoint(25);
		closest = sol.searchClosestCluster(input);
		
		System.out.println("Closest : "+closest);
		System.out.println("\n\n\t***********Updated Cluster Data************\t");
		sol.updateCluster(input, closest);
		sol.dispayClusterData();
		
		
	}

}