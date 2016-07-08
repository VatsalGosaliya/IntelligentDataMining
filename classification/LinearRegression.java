package classification;
import helperClasses.DataPoint;
import helperClasses.Serialization;
import java.util.ArrayList;

class LinearRegression {
	public ArrayList<DataPoint> trainer = new ArrayList<DataPoint>();
	
	public void importTrainingData(){
		ArrayList<DataPoint> t = null;
		Serialization S = new Serialization();
		trainer = S.deserialize(t, "linRegTrainingData.ser");
	}
	
	public void updateTrainingData(DataPoint newData){
		//Used to create the training data (".ser" file)
		/*trainer.add(new DataPoint(95, 85));
		trainer.add(new DataPoint(85, 95));
		trainer.add(new DataPoint(80, 70));
		trainer.add(new DataPoint(70, 65));
		trainer.add(new DataPoint(60, 70));*/
		
		trainer.add(newData);
		Serialization S = new Serialization();
		S.serialize(trainer, "linRegTrainingData.ser");
	}
	
	public void dispayTrainingData(){
		ArrayList<DataPoint> T = null;
		Serialization S = new Serialization();
		T = S.deserialize(T, "linRegTrainingData.ser");
		System.out.println("X\tY\t");
		for (DataPoint P : T)
			System.out.println(P.x+"\t"+P.y+"\t");
	}
	
	public void linearRegressionModel(DataPoint input){
		System.out.println("Y = a + bX");
		double Sx=0, Sy=0, Sxx=0, Sxy=0, a=0, b=0;
		double N = this.trainer.size();
		
		for(DataPoint P : this.trainer){
			Sx = Sx + P.x;
			Sy = Sy + P.y;
			Sxx = Sxx + Math.pow(P.x, 2);
			Sxy = Sxy + (P.x * P.y);
		}
		b = ((N*Sxy) - (Sx*Sy)) / ((N*Sxx) - (Sx*Sx));
		a = (Sy - (b*Sx)) / N;
		System.out.println("b = "+b);
		System.out.println("a = "+a);
		
		input.y = a + (b*input.x);
		System.out.println("inputX = "+input.x);
		System.out.println("inputY = "+input.y);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinearRegression sol = new LinearRegression();
		sol.importTrainingData();
		
		DataPoint input = new DataPoint(4);
		sol.linearRegressionModel(input);
		
		System.out.println("\n\n\t***********Updated Training Data************\t");
		System.out.println("Old Size : "+sol.trainer.size());
		
		sol.updateTrainingData(input);
		
		System.out.println("New Size : "+sol.trainer.size());
		sol.dispayTrainingData();
		
	}
}
