import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {
            //fancy title of program
            System.out.println("|-------------------------------|");
            System.out.println("|                               |");
            System.out.println("|           Mastermind          |");
            System.out.println("|                               |");
            System.out.println("|-------------------------------|");
            System.out.println("By Surafel Uloro");

            System.out.println();
            System.out.println();

            System.out.println("Please enter the number of the option you would like to select and then press enter.");
            System.out.println();
            System.out.println("1. Start Game");
            System.out.println("2. Quit");

            Scanner s = new Scanner(System.in); // make a new scanner to read user input

            if (s.nextLine().equals("1")) { //check if user entered 1
                CPUGame();
            }
            else if (s.nextLine().equals("2")) { //check if user enterd 2
                System.exit(0); //exit
            }
        }
    }


    private static void CPUGame() {

        String[] code = new String[4]; //make a new code array
        String[] validColors = { "R", "B", "G", "Y", "BR", "O", "W", "BL", "P" }; //create an array with valid colors to use
        String[] lastGuess; //store the last guess for reference

        for (int i = 0; i < code.length; i++) { //go through each element of array
            int randomNumber = (int) ((Math.random() * 100) % 9); //pick a random number between 0 and 8
            code[i] = validColors[ randomNumber ]; //add the color with that index from the valid color array into the code array
        }

        clear(); //clear the console
        System.out.println("The Computer has chosen a code.");
        System.out.println("Please enter a combination of 4 colors to guess the code");

        for (int numOfAttempts = 1; numOfAttempts <= 20; numOfAttempts++) { //keep repeating this 20 times to give the user 20 chances
            boolean invalidUserInput = true;

            while (invalidUserInput) { //keep asking the user to input a guess and keep repeating until the user makes a proper guess
                System.out.println();
                System.out.println("Enter the letters which represent the color according to the following legend:");
                System.out.println("R = Red     B = Blue       G = Green     Y = Yellow      Br = Brown     O = Orange");
                System.out.println("W = White   Bl = Black     P = Pink");

                System.out.println();
                System.out.println("Please separate the 4 colors with a comma. Capitalization does not matter. Example: R,br,g,Y");
                System.out.println("This is attempt " + Integer.toString(numOfAttempts) + " of 20");

                Scanner scanner = new Scanner(System.in); //make a new scanner to read user input
                String[] guesses = scanner.nextLine().replaceAll(" ", "").toUpperCase().split(","); //convert the guesses to upper case, remove spaces, and split the guess into an array separated by commas

                if (guesses.length == 4) {

                    boolean validInputColors = true;
                    for (int i = 0; i < guesses.length; i++) {
                        if (!(guesses[i].equals("R") || guesses[i].equals("B") || guesses[i].equals("G") || guesses[i].equals("Y") || guesses[i].equals("BR") || guesses[i].equals("O") || guesses[i].equals("W") || guesses[i].equals("BL") || guesses[i].equals("P"))) { //check if user entered correct color
                             validInputColors = false; //the user inputted an invalid color
                        }
                    }


                    if (validInputColors) { //only do this if the user selected correct colors
                        lastGuess = guesses.clone(); //update the last guess variable with the current guess
                        int numberOfCorrect = 0;
                        int numberOfMisplaced = 0;
                        invalidUserInput = false;

                        String[] aCode = code.clone(); //make a copy of the actual code generated since the copy of the code is going to be modified
                        for (int i = 0; i < aCode.length; i++) {
                            if (aCode[i].equals(guesses[i])) { //if the user guesses the color in the right spot, change that guess to "" to avoid it from being taken into account as being misplaced later on
                                guesses[i] = "";
                                aCode[i] = "";
                                numberOfCorrect++;
                            }
                        }

                        for (int c = 0; c < aCode.length; c++) {
                            for (int i = 0; i < aCode.length; i++) {
                                if (aCode[c].equals(guesses[i]) && !aCode[c].equals("")) { //at this point, the guesses of the colors in the right spot are already set to "", so this will find the misplaced guesses and count that.
                                        numberOfMisplaced++;
                                        guesses[i] = ""; //this is set to "" to avoid it from being taken into account as being misplaced when the loop goes through it again
                                        break;
                                }
                            }
                        }


                        clear();
                        if (numberOfCorrect == 4) { //if the user correctly guesses all the colors...
                            System.out.println("Congratulations! You have cracked the code. You are a true mastermind.");
                            System.out.println();
                            System.out.println("Press enter to continue...");
                            clear();
                            return; //end the function
                        }
                        else { //print the last guess, the number of colors in guessed correctly in the right spot, and number of colors misplaced
                            System.out.println("Your last guess was " + lastGuess[0] + "," + lastGuess[1] + "," + lastGuess[2] + "," + lastGuess[3]);
                            System.out.println("You have " + Integer.toString(numberOfCorrect) + " correct in the right place");
                            System.out.println("You have " + Integer.toString(numberOfMisplaced) + " correct, but in the wrong place");
                        }
                    }
                }
            }
        }
        clear(); //this part will run only, if all the attempts have been made, but the user hasn't found the correct code.
        System.out.println("Game Over! You didn't guess the code in 20 attempts.");
        System.out.println("The code was: " + code[0] + "," + code[1] + "," + code[2] + "," + code[3]);
        System.out.println("Better luck next time.");
        System.out.println();
        System.out.println("Press enter to continue...");

        Scanner s = new Scanner(System.in); //wait for user to press enter before continuing
        s.nextLine();
    }


    private static void clear() {
        for (int i = 0; i < 10; i++) { //print an empty line 10 times to simulate the console being cleared
            System.out.println();
        }
    }
}
