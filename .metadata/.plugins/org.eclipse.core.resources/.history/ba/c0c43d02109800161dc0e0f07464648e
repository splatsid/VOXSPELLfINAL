package rewardsNStats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Stats implements Serializable {
	
// This is a singleton class that holds records for statistical purposes.
private static ArrayList<String> correct = new ArrayList<String>();
private static ArrayList<String> faulted = new ArrayList<String>();
private static ArrayList<String> failed = new ArrayList<String>();

//Gets the size of how big the words that are correct.
public static int getCorrectSize()throws FileNotFoundException, IOException, ClassNotFoundException {
	readFromObject();
	return correct.size();
}
//Gets the size of how big faulted words are.
public static int getFaultedSize()throws FileNotFoundException, IOException, ClassNotFoundException {
	readFromObject();
	return faulted.size();
}
// Gets the size of how big failed words are.
public static int getFailedSize() throws FileNotFoundException, IOException, ClassNotFoundException {
	readFromObject();
	return failed.size();
	
}
// Appends a word to the correct list
public static void addtoCorrect(String a) throws FileNotFoundException, IOException, ClassNotFoundException{
	readFromObject();
	correct.add(a);
	writeToObject();

}
// APpends a word to the faulted list
public static void addtoFaulted(String b) throws FileNotFoundException, IOException, ClassNotFoundException{
	readFromObject();
	faulted.add(b);
	writeToObject();

}
// Appends a word to the failed list
public static void addtoFailed(String c) throws FileNotFoundException, IOException, ClassNotFoundException{
	readFromObject();
	failed.add(c);
	writeToObject();
}
// Gives the list for correct words
public static ArrayList<String> getCorrectWords(){
	try {
		readFromObject();
	} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return correct;
}
//Gives the list for faulted words
public static ArrayList<String> getFaultedWords(){
	try {
		readFromObject();
	} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return faulted;
}
// Gives he list for failed words
public static ArrayList<String> getFailedWords(){
	try {
		readFromObject();
	} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return failed;
}
// Clears statistics
public static void clearStats(){
	try {
		readFromObject();
	} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	correct.clear();
	faulted.clear();
	failed.clear();
	try {
		writeToObject();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
// Writes the fields to a notepad so it can be read later
private static void writeToObject() throws FileNotFoundException, IOException{
	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("stats.bin"));
	out.writeObject(correct);
	out.writeObject(faulted);
	out.writeObject(failed);
	out.close();
	
}

// Reads all the fields from the notepad object.
private static void readFromObject() throws FileNotFoundException, IOException, ClassNotFoundException{
	ArrayList<Object> stats = new ArrayList<Object>();
	ObjectInputStream in = new ObjectInputStream(new FileInputStream("stats.bin"));
		for(int i = 0; i < 3; i++)
		stats.add(in.readObject());

	correct = (ArrayList<String>) stats.get(0);
	faulted = (ArrayList<String>) stats.get(1);
	failed = (ArrayList<String>) stats.get(2);
	
	 
}


}