import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*	Armora Rama - Homework 6 
 * 
 */
public class Hangman {
	List<String> wordList = new ArrayList<String>();

	public void readWords(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));

		while (s.hasNext())
			wordList.add(s.next());

		s.close();
	}

	public String getWord() {
		// returns a random word from wordList

		Random r = new Random();

		return wordList.get(r.nextInt(wordList.size()));
	}
	

	public void playGame(String word) {
		char nextChoice;
       
        for (int i = 0; i < word.length(); i++)
            System.out.print(" _ ");
        
        System.out.println();
		
		Scanner reader = new Scanner(System.in); 
		
		//this array converts the array in small parts of charts 
		char[] arrayOfLetters = word.toCharArray();
		//this array is for the letters you guess
		char [] emptyArray = new char[word.length()];
	
		int numberOfGuessing = 6; 
	//the capacity of the word array 
		int capacity = 0;
		
		while (numberOfGuessing>0 && capacity!=word.length()) {
		 
			boolean isCharintheWord = false; 	
			System.out.println("Your choice: ");
			nextChoice = reader.next().charAt(0);

			System.out.println("you entered " + nextChoice);
			
			/*checking through the array
			 * if the input from the keyboard is the same with any of the elements in the word array 
			 * then we put this in the array and increment the capacity. 
			 */
			
			for(int i =0; i <word.length(); i++){
				if(nextChoice==arrayOfLetters[i]){
					emptyArray[i] = nextChoice;
					isCharintheWord=true;  
					capacity++;
					System.out.print(" " + emptyArray[i]+ " ");
				}
		
				else {
					if(emptyArray[i]=='\0'){
					System.out.print(" _ ");
					}
					else
						System.out.print(" " + emptyArray[i]+ " ");
					
				}
			
				
			}
			//we are checking if our guess is correct, if not then decrement the number of guessing that is left 
			System.out.println();
			if(isCharintheWord==false){
				numberOfGuessing--;
				System.out.println("Incorrect choice =" + " " + numberOfGuessing);
			}
			
			
         
			
		}
		
		//Two of the cases when the player lost or win
		if(numberOfGuessing==0){
			System.out.println("You lost!");
		}
		if(capacity == word.length()){
			System.out.println("You Won!");
		}
		
	}

	public static void main(String[] args) {
		Hangman game = new Hangman();

		try {
			game.readWords("H:\\data\\Myfiles\\Eclipse\\workspace\\Homework6\\src\\words.txt");
		} catch (FileNotFoundException fnf) {
			System.err.println("File Not Found");
		}

		game.playGame(game.getWord());
	}

}
