package classification;
import helperClasses.DataPoint;
import helperClasses.Serialization;
import java.util.ArrayList;

class NaiveBayes {
	public ArrayList<DataPoint> trainer = new ArrayList<DataPoint>();
	
	public void importTrainingData(){
		ArrayList<DataPoint> t = null;
		Serialization S = new Serialization();
		trainer = S.deserialize(t, "nbcTrainingData.ser");
	}
	
	public void updateTrainingData(DataPoint newData){
		//Used to create the training data (".ser" file)
		
		/*trainer.add(new DataPoint("Sunny", "Hot", "H", "W", "NO"));
		trainer.add(new DataPoint("Sunny", "Hot", "H", "S", "NO"));
		trainer.add(new DataPoint("Overcast", "Hot", "H", "W", "YES"));
		trainer.add(new DataPoint("Rain", "Mild", "H", "W", "YES"));
		trainer.add(new DataPoint("Rain", "Cool", "N", "W", "YES"));
		trainer.add(new DataPoint("Rain", "Cool", "N", "S", "NO"));
		trainer.add(new DataPoint("Overcast", "Cool", "N", "S", "YES"));
		trainer.add(new DataPoint("Sunny", "Mild", "H", "W", "NO"));
		trainer.add(new DataPoint("Sunny", "Cool", "N", "W", "YES"));
		trainer.add(new DataPoint("Rain", "Mild", "N", "W", "YES"));
		trainer.add(new DataPoint("Sunny", "Mild", "N", "S", "YES"));
		trainer.add(new DataPoint("Overcast", "Mild", "H", "S", "YES"));
		trainer.add(new DataPoint("Overcast", "Hot", "N", "W", "YES"));
		trainer.add(new DataPoint("Rain", "Mild", "H", "S", "NO"));*/
		
		trainer.add(newData);
		Serialization S = new Serialization();
		S.serialize(trainer, "nbcTrainingData.ser");
	}
	
	public void dispayTrainingData(){
		ArrayList<DataPoint> T = null;
		Serialization S = new Serialization();
		T = S.deserialize(T, "nbcTrainingData.ser");
		System.out.println("Outlook\t\tTemperature\tHumidity\tWind\tClass");
		for (DataPoint P : T)
			System.out.println(P.outlook+"\t\t"+P.temperature+"\t\t"+P.humidity+"\t\t"+P.wind+"\t"+P.C);
	}
	
	public void nbcClassify(DataPoint input){
		double nYES=0, nNO=0, nOY=0, nTY=0, nHY=0, nWY=0, nON=0, nTN=0, nHN=0, nWN=0;
		double total = trainer.size();

		for(DataPoint R : trainer){
			if(R.C.equals("YES")){
				nYES++;
				if(R.outlook.equals(input.outlook)) nOY++;
				if(R.temperature.equals(input.temperature)) nTY++;
				if(R.humidity.equals(input.humidity)) nHY++;
				if(R.wind.equals(input.wind)) nWY++;
				continue;
			}
			else{
				nNO++;
				if(R.outlook.equals(input.outlook)) nON++;
				if(R.temperature.equals(input.temperature)) nTN++;
				if(R.humidity.equals(input.humidity)) nHN++;
				if(R.wind.equals(input.wind)) nWN++;
			}
		}
		
		double pYES = nYES/total;
		double pNO = nNO/total;
		
		double pOY = nOY/nYES;
		double pON = nON/nNO;
	
		double pTY = nTY/nYES;
		double pTN = nTN/nNO;
	
		double pHY = nHY/nYES;
		double pHN = nHN/nNO;
		
		double pWY = nWY/nYES;
		double pWN = nWN/nNO;
		
		double inputY = pYES * pOY * pTY * pHY * pWY;
		double inputN = pNO * pON * pTN * pHN * pWN;
		
		if(inputY >= inputN) input.C = "YES";
		else input.C = "NO";
		
		System.out.println("Class of input : "+input.C);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NaiveBayes sol = new NaiveBayes();
		sol.importTrainingData();
		
		DataPoint input = new DataPoint("Rain", "Hot", "N", "W");
		sol.nbcClassify(input);
		
		System.out.println("\n\n\t***********Updated Training Data************\t");
		System.out.println("Old Size : "+sol.trainer.size());
		
		sol.updateTrainingData(input);
		
		System.out.println("New Size : "+sol.trainer.size());
		sol.dispayTrainingData();
	
	}
}
