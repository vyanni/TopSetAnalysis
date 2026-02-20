import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Lower Set Bound? (Previous 10): ");
        int lowerSetBound = userInput.nextInt();

        System.out.println("Higher Set Bound? (Previous 25): ");
        int higherSetBound = userInput.nextInt();

        System.out.println("Set Size Bound? (Previous 50): ");
        int setSizeBound = userInput.nextInt();

        BufferedWriter dataWriter = new BufferedWriter(new FileWriter("src\\dataOutput.CSV"));
        dataWriter.write("Set Size,Highest Value");

        int lowerSetQuantityBound = lowerSetBound;
        int higherSetQuantityBound = higherSetBound;
        int setQuantity = lowerSetQuantityBound + (int)(Math.random() * ((higherSetQuantityBound - lowerSetQuantityBound) + 1));

        for(int i = 0; i < 1000; i++){
            dataDistribution(setQuantity, setSizeBound, dataWriter);
        }

        userInput.close();
        dataWriter.close();
    }

    public static void dataDistribution(int setQuantity, int higherSizeBound, BufferedWriter dataWriter) throws Exception{        
        int higherSetSizeBound = higherSizeBound;

        ArrayList<Integer> numberList = new ArrayList<Integer>();
        for(int i = 0; i < 100; i++){
            numberList.add(i+1);
        }

        ArrayList<ArrayList<Integer> > setDistribution = new ArrayList<ArrayList<Integer> >(setQuantity);
        for(int i = 0; i < setQuantity; i++){
            setDistribution.add(new ArrayList<Integer>());

            for(int j = 0; j < 3; j++){
                int randomIndex = (int)(Math.random() * numberList.size());
                int randomNumber = numberList.remove(randomIndex);

                setDistribution.get(i).add(randomNumber);
            }
        }

        while(!numberList.isEmpty()){
            int randomArray = (int)(Math.random() * setDistribution.size());

            while(setDistribution.get(randomArray).size() == higherSetSizeBound){
                randomArray = (int)(Math.random() * setDistribution.size());
            }
            
            int randomIndex = (int)(Math.random() * numberList.size());
            int randomNumber = numberList.remove(randomIndex);

            setDistribution.get(randomArray).add(randomNumber);
        }

        dataWriter.newLine();
        for(int i = 0; i < setDistribution.size(); i++){
            dataWriter.write(setDistribution.get(i).size() + "," + Collections.max(setDistribution.get(i)));
            dataWriter.newLine();
        }

        /*for(int i = 0; i < setDistribution.size(); i++){
            System.out.println("Set " + (i+1) + ": " + setDistribution.get(i));

            System.out.println("Set size is " + setDistribution.get(i).size());

            int biggestValue = Collections.max(setDistribution.get(i));
            System.out.println("Highest value is " + biggestValue);

            System.out.println("");
        }*/
    }
}
