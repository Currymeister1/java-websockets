package Guess_The_Number;

import java.util.Random;

public class GuessNumberProtocol {

    private int round = 0;

    private int numberToGuess;

    public GuessNumberProtocol(){
        Random random = new Random();
        this.numberToGuess = random.nextInt(100-1+1)+1;
    }

    public String processInput(String input){
        String output = null;


        int guessedNumber = Integer.parseInt(input);
        System.out.println(guessedNumber);
        if(guessedNumber == numberToGuess){
            output = "Won! You guessed the number! It was " + numberToGuess;
        } else if (guessedNumber > numberToGuess) {
            output = "Lower";
        }
        else{
            output = "Higher";
        }

        return output;


    }


}