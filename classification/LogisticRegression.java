package classification;
import helperClasses.DataPoint;
import helperClasses.Serialization;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

class LogisticRegression {
	public ArrayList<DataPoint> trainer = new ArrayList<DataPoint>();
	
	public void importTrainingData(){
		ArrayList<DataPoint> t = null;
		Serialization S = new Serialization();
		trainer = S.deserialize(t, "logRegTrainingData.ser");
	}
	
	public void updateTrainingData(DataPoint newData){
		//Used to create the training data (".ser" file)
		/*trainer.add(new DataPoint(28, 0.3333));
		trainer.add(new DataPoint(29, 0.4000));
		trainer.add(new DataPoint(30, 0.7778));
		trainer.add(new DataPoint(31, 0.7778));
		trainer.add(new DataPoint(32, 0.8000));
		trainer.add(new DataPoint(33, 0.9333));*/
		
		trainer.add(newData);
		Serialization S = new Serialization();
		S.serialize(trainer, "logRegTrainingData.ser");
	}
	
	public void dispayTrainingData(){
		ArrayList<DataPoint> T = null;
		Serialization S = new Serialization();
		T = S.deserialize(T, "logRegTrainingData.ser");
		System.out.println("X\tY\t");
		for (DataPoint P : T)
			System.out.println(P.x+"\t"+P.y+"\t");
	}
	
	public void logisticRegressionModel(DataPoint input){
		LinkedHashMap<Double, Double> logOdds = new LinkedHashMap<Double, Double>();
		
		for(DataPoint P : this.trainer){
			double odds = P.y / (1-P.y);
			double logOdd = Math.log(odds);
			logOdds.put(P.x, logOdd);
		}
		
		double Sx=0, Sy=0, Sxx=0, Sxy=0, a=0, b=0;
		double N = logOdds.size();
		for(Map.Entry<Double,Double> m : logOdds.entrySet()){
			Sx = Sx + (double)m.getKey();
			Sy = Sy + (double)m.getValue();
			Sxx = Sxx + Math.pow((double)m.getKey(), 2);
			Sxy = Sxy + ((double)m.getKey() * (double)m.getValue());
		}
		b = ((N*Sxy) - (Sx*Sy)) / ((N*Sxx) - (Sx*Sx));
		a = (Sy - (b*Sx)) / N;
		System.out.println("b = "+b);
		System.out.println("a = "+a);
		
		double predLogOdd = a + (b*input.x);
		double predOdd = Math.exp(predLogOdd);
		double predProb = predOdd / (1+predOdd);
		
		input.y = predProb;
		System.out.println("inputX = "+input.x);
		System.out.println("inputY = "+input.y);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LogisticRegression sol = new LogisticRegression();
		sol.importTrainingData();
		
		DataPoint input = new DataPoint(31);
		sol.logisticRegressionModel(input);
		
		System.out.println("\n\n\t***********Updated Training Data************\t");
		System.out.println("Old Size : "+sol.trainer.size());
		
		sol.updateTrainingData(input);
		System.out.println("New Size : "+sol.trainer.size());
		sol.dispayTrainingData();
		
	}
}
