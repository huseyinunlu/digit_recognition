
package digitrecognition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
        

        File trainData = new File(args[0]);
        Scanner line = new Scanner(trainData);
        NeuralNetwork nn = new NeuralNetwork(64,24,10);
        String[] splittedString;
        ArrayList<double[]> inputs = new ArrayList<double[]>();
        ArrayList<double[]> outputs = new ArrayList<double[]>();
        
        int result;
        double totaltests=0;
        double trueResults=0;
       //reading training file and training neural network
        while(line.hasNext()){
            splittedString  = line.next().split(",");
            double[] input = new double[64];
            double[] output = new double[10];
            for(int i=0;i<64;i++)
                input[i] = (Double.parseDouble(splittedString[i])/160) -.05;
            for(int j=0;j<10;j++){
                if(Integer.parseInt(splittedString[64]) == j)
                    output[j] = 1;
                else
                    output[j] = 0;
            }
                
            outputs.add(output);
            inputs.add(input);
        }
        
        
        for(int i=0;i<100;i++){
            double[] o = new double[10];
            for(int j=0;j<inputs.size();j++){
                o = nn.train(inputs.get(j), outputs.get(j));
                
                int index=0;
                double biggest=0;
                //gets biggest max element of the output
                for(int k=0;k<o.length;k++){
                    //System.out.println("Out ["+i+"]: "+ Math.round(o[i]*100));
                    if(o[k] > biggest){
                        biggest = o[k];
                        index = k;
                    }
                }



                if(outputs.get(j)[index] == 1.0) trueResults++;
                totaltests++;
            }
            
            
        }
        System.out.println("Percentage of true answers for train: "+(trueResults/totaltests)*100);

        double[] inputss = new double[64];
        
        File testData = new File(args[1]);
        line = new Scanner(testData);
        totaltests=0;
        trueResults=0;

        //reading test file and testing neural network
        while(line.hasNext()){
            String input = line.next();
            String[] splittedInput = input.split(",");
            
            //reading file 
            for(int i=0;i<64;i++){
                inputss[i] = Double.parseDouble(splittedInput[i])/160 -.05;
            }
            int expOutput = Integer.parseInt(splittedInput[64]);
            double[] o = nn.think(inputss);
            
            int index=0;
            double biggest=0;
            
            
            //System.out.println("Expected: "+expOutput);
            
            //gets biggest max element of the output
            for(int i=0;i<o.length;i++){
                //System.out.println("Out ["+i+"]: "+ Math.round(o[i]*100));
                if(o[i] > biggest){
                    biggest = o[i];
                    index = i;
                }
            }
            
            
            
            if(expOutput == index) trueResults++;
            totaltests++;
            
            
            
        }

        System.out.println("Percentage of true answers for test: "+(trueResults/totaltests)*100);
    }
    
}
