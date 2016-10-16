package quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ReadNWrite {
	private File f;

	public ReadNWrite(File file) {
		this.f = file;
	}

	//Get all the possible words from a particular level.
	public ArrayList<String> chooselevelwordlist(String level) {
		ArrayList<String> wordlist = new ArrayList<String>();
		if (level.equals("%Level 1")) {
			Scanner input = new Scanner(System.in);
			try {
				input = new Scanner(f);
				while (input.hasNextLine()) {
					String line = input.nextLine();
					if (line.equals("%Level 2")) {
						break;
					}
					if (line.equals("%Level 1")) {
						continue;
					}
					wordlist.add(line);
				}
				input.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return wordlist;
		} else {
			String[] splitarray = level.split("\\s+");
			int levelnum = Integer.parseInt(splitarray[1]);
			Scanner input = new Scanner(System.in);
			try {
				input = new Scanner(f);
				while (input.hasNextLine()) {
					String line = input.nextLine();
					if (line.equals(level)) {
						while (input.hasNextLine()) {
							String wordline = input.nextLine();
							int l = levelnum + 1;
							if (wordline.equals("%Level " + l)) {
								break;
							}
							wordlist.add(wordline);
						}
					}
				}
				input.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return wordlist;

		}
	}
	
	public ArrayList<String> readCustomWords(){
		Scanner input = new Scanner(System.in);
		ArrayList<String> words = new ArrayList<String>();
		try {
			input = new Scanner(f);
			while (input.hasNextLine()){
				words.add(input.nextLine());
			}
		}catch (FileNotFoundException e){
			e.getMessage();
		}
		
		return(customWords(10,words));
		
	}
	
	private ArrayList<String> customWords(int num, ArrayList<String> words){
		ArrayList<String> chosenWords = new ArrayList<String>();
		for(int i = 0; i < num; i++){
			chosenWords.add(words.get((int) (Math.random() * ((words.size() - 1) + 1))));
		}		
		return chosenWords;
	}

	//Choose words for a spelling/review quiz via ArrayList position numbers.
	public ArrayList<Integer> choosewords(ArrayList<String> wordlist) {
		int COUNTER = 0;
		int INDEX = 0;
		int numberposition;
		int numberofchosenwordssize = 10;
		if (wordlist.size() < 10) {
			numberofchosenwordssize = wordlist.size();
		}
		ArrayList<Integer> linenumbers = new ArrayList<Integer>();
		while (INDEX < numberofchosenwordssize) {
			int randomNum = (int) (Math.random() * ((wordlist.size() - 1) + 1));
			boolean addnumber = true;
			numberposition = randomNum;

			for (int a : linenumbers) {
				if (a == numberposition) {
					addnumber = false;
				}
			}

			if (addnumber == true) {
				linenumbers.add(numberposition);
				INDEX = INDEX + 1;
			}

			COUNTER = COUNTER + 1;
		}
		return linenumbers;
	}

	//Write a string or line to a file.
	public void writeToFile(String word) {
		try {
			// writes to appropriate text files for
			FileWriter writetoFile = new FileWriter(f, true);
			BufferedWriter writer = new BufferedWriter(writetoFile);
			PrintWriter out = new PrintWriter(writer);
			out.println(word);
			out.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	//Reading statistics.
	public ArrayList<Integer> readStats() throws FileNotFoundException {
		int size = 0;
		ArrayList<Integer> stats = new ArrayList<Integer>();
		ArrayList<File> file = new ArrayList<File>();
		file.add(new File("correct.txt"));
		file.add(new File("Faulted.txt"));
		file.add(new File("Failed.txt"));
		

				Scanner scan = new Scanner(new File("correct.txt"));
				while(scan.hasNextLine()){
				String word = scan.nextLine();
				size++;
				}
				stats.add(size);
				size = 0;
				
				 scan = new Scanner(new File("Faulted.txt"));
				 while(scan.hasNextLine()){
						String word = scan.nextLine();
						size++;
						}
						stats.add(size);
						size = 0;
				scan = new Scanner(new File("Failed.txt"));
				while(scan.hasNextLine()){
					String word = scan.nextLine();
					size++;
					}
					stats.add(size);
		scan.close();
	
		return stats;

	}

	//See if a word already exists in the chosen file.
	public boolean checkifwordisinfile(String word) {
		try {
			Scanner scanner = new Scanner(f);

			int linenumber = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (line.equals(word)) {
					return true;
				}
			}
			return false;
		} catch (FileNotFoundException e) {
			return false;
		}

	}

	//Remove a word from a file.
	public void removeWordFromFile(String currentWord) {
		try {

			File tempfile = new File(f.getAbsolutePath() + ".tmp");

			BufferedReader buffer = new BufferedReader(new FileReader(f));
			PrintWriter print = new PrintWriter(new FileWriter(tempfile));

			String line;

			while ((line = buffer.readLine()) != null) {
				if (!line.trim().equals(currentWord)) {
					print.println(line);
					print.flush();
				}
			}
			print.close();
			buffer.close();

			f.delete();
			tempfile.renameTo(f);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Check if a file is empty.
	public boolean fileisempty() {
		BufferedReader br;
		try {
			String line;
			br = new BufferedReader(new FileReader(f));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					return false;
				}
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	
	// Says the text in a different voice
	public void changevoice(String voice,String phrase){
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(f.getAbsoluteFile());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
	
		try {
			bw.write("(voice_" + voice + ")");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			bw.newLine();
			bw.write("(SayText \""+ phrase+ "\")");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}