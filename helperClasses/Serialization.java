package helperClasses;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import NeuralNetworks.Neuron;
/**
 * 
 * This is a universal class for this entire project.
 * Therefore it contains all the attributes and methods required in any of the programs.
 * Hence, you might find some attributes and methods that are irrelevant to some programs.
 * Please ignore that. In case you don't need them, you can delete them.
 *
 */
public class Serialization {
	
	public void serialize(ArrayList<DataPoint> trainer, String fileName){
		try{
			FileOutputStream fileout = new FileOutputStream(fileName);
			ObjectOutputStream objout = new ObjectOutputStream(fileout);
			objout.writeObject(trainer);
			objout.close();
			fileout.close();
			//System.out.println("Trainer Serialized");
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<DataPoint> deserialize(ArrayList<DataPoint> trainer, String fileName){
		try{
			FileInputStream filein = new FileInputStream(fileName);
			ObjectInputStream objin = new ObjectInputStream(filein);
			trainer = (ArrayList<DataPoint>)objin.readObject();
			objin.close();
			filein.close();
			//System.out.println("Trainer Deserialized");
		}catch(Exception e){
			System.out.println(e);
		}
		return trainer;
	}
	
	public void nnSerialize(ArrayList<Neuron> trainer, String fileName){
		try{
			FileOutputStream fileout = new FileOutputStream(fileName);
			ObjectOutputStream objout = new ObjectOutputStream(fileout);
			objout.writeObject(trainer);
			objout.close();
			fileout.close();
			//System.out.println("Trainer Serialized");
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Neuron> nnDeserialize(ArrayList<Neuron> trainer, String fileName){
		try{
			FileInputStream filein = new FileInputStream(fileName);
			ObjectInputStream objin = new ObjectInputStream(filein);
			trainer = (ArrayList<Neuron>)objin.readObject();
			objin.close();
			filein.close();
			//System.out.println("Trainer Deserialized");
		}catch(Exception e){
			System.out.println(e);
		}
		return trainer;
	}
	
	public void clustSerialize(List<List<DataPoint>> clusters, String fileName){
		try{
			FileOutputStream fileout = new FileOutputStream(fileName);
			ObjectOutputStream objout = new ObjectOutputStream(fileout);
			objout.writeObject(clusters);
			objout.close();
			fileout.close();
			//System.out.println("Cluster Serialized");
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<List<DataPoint>> clustDeserialize(ArrayList<List<DataPoint>> clusterData, String fileName){
		try{
			FileInputStream filein = new FileInputStream(fileName);
			ObjectInputStream objin = new ObjectInputStream(filein);
			clusterData = (ArrayList<List<DataPoint>>)objin.readObject();
			objin.close();
			filein.close();
			//System.out.println("Cluster Deserialized");
		}catch(Exception e){
			System.out.println(e);
		}
		return clusterData;
	}
	
	public static void main(String[] args) {
		System.out.println("Serialization Running");
	}
}

