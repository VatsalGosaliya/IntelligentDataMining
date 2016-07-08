package classification;
import helperClasses.DataPoint;
import helperClasses.Serialization;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class KNearestNeighbor {
	public int  K = 3;
	public ArrayList<DataPoint> trainer = new ArrayList<DataPoint>();
	
	public void importTrainingData(){
		ArrayList<DataPoint> t = null;
		Serialization S = new Serialization();
		trainer = S.deserialize(t, "knnTrainingData.ser");
	}
	
	public void updateTrainingData(DataPoint newData){
		//Used to create the training data (".ser" file)
		/*trainer.add(new DataPoint(25, 4, "N"));
		trainer.add(new DataPoint(35, 6, "N"));
		trainer.add(new DataPoint(45, 8, "N"));
		trainer.add(new DataPoint(20, 2, "N"));
		trainer.add(new DataPoint(35, 12, "N"));
		trainer.add(new DataPoint(52, 1.8, "N"));
		trainer.add(new DataPoint(23, 9.5, "Y"));
		trainer.add(new DataPoint(40, 6.2, "Y"));
		trainer.add(new DataPoint(60, 10, "Y"));
		trainer.add(new DataPoint(48, 22, "Y"));
		trainer.add(new DataPoint(33, 15, "Y"));*/
		
		trainer.add(newData);
		Serialization S = new Serialization();
		S.serialize(trainer, "knnTrainingData.ser");
	}
	
	public void dispayTrainingData(){
		ArrayList<DataPoint> T = null;
		Serialization S = new Serialization();
		T = S.deserialize(T, "knnTrainingData.ser");
		System.out.println("X\tY\tClass");
		for (DataPoint P : T)
			System.out.println(P.x+"\t"+P.y+"\t"+P.C);
	}
	
	
	
	public void knnClassify(DataPoint input){
		for(DataPoint P : this.trainer)
			P.dist = euclideanDistance(P, input);
		Collections.sort(this.trainer, DataPoint.eucDist);
		
		int cN = 0, cY = 0;
		for(int i=0; i<this.K; i++){
			if(this.trainer.get(i).C.equals("N"))
				cN++;
			else
				cY++;
		}
		
		if(cN >= cY) input.C = "N";
		else input.C = "Y";
		System.out.println("Class of input : "+input.C);
	}
	
	
	private double euclideanDistance(DataPoint p, DataPoint input) {
		return Math.sqrt(Math.pow((p.x-input.x),2) + Math.pow((p.y-input.y),2));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		KNearestNeighbor sol = new KNearestNeighbor();
		sol.importTrainingData();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter x: ");
		double x = scan.nextDouble();
		System.out.println("Enter y: ");
		double y = scan.nextDouble();
		scan.close();
		
		DataPoint input = new DataPoint(x, y);
		sol.knnClassify(input);
		
		System.out.println("\n\n\t***********Updated Training Data************\t");
		System.out.println("Old Size : "+sol.trainer.size());
		
		sol.updateTrainingData(input);
		
		System.out.println("New Size : "+sol.trainer.size());
		sol.dispayTrainingData();
		
	}
}
