package digitrecognition;

import java.util.ArrayList;


public class NeuralNetwork {
    static double LearningRate = .1;
    int numInputs;
    int numHidden;
    int numOutput;
    
    NeuronLayer hiddenLayer;
    NeuronLayer outputLayer;

    
    public NeuralNetwork(int numInputs, int numHidden, int numOutput){

        this.numInputs = numInputs;
        this.numHidden = numHidden;
        this.numOutput = numOutput;
        
        hiddenLayer = new NeuronLayer(numHidden, numInputs);
        outputLayer = new NeuronLayer(numOutput, numHidden);
    }
    
    public double[] train(double[] inputs, double[] targetOutputs){
        double[] outputs = think(inputs);
        
        
        double hlTargetOutput;
        
        
        //adjustment for output layer's neurons weights
        for(int o=0; o<numOutput;o++){
            
            //this loop adjusts weights
            for(int i=0;i<outputLayer.neurons.get(o).weights.length;i++)
                outputLayer.neurons.get(o).weights[i] -= LearningRate * outputLayer.neurons.get(o).errorChange(targetOutputs[o]) * outputLayer.neurons.get(o).inputs[i];
            
        }
        
        
        //adjustment for hidden layer's neurons weights
        for(int h=0;h<numHidden;h++){
        
            hlTargetOutput = 0;
            
            //this loop gets hidden layer's neurons target output
            for(int o=0;o<numOutput;o++){
                hlTargetOutput += outputLayer.neurons.get(o).errorChange(targetOutputs[o]) * outputLayer.neurons.get(o).weights[h];
            }
            
            //and this loop adjusts weights
            for(int i=0;i<hiddenLayer.neurons.get(h).weights.length;i++)
                hiddenLayer.neurons.get(h).weights[i] -= LearningRate * hlTargetOutput * hiddenLayer.neurons.get(h).inputs[i];
            

            
        }
        return outputs;
    }
    
    //feedforwards and gets outputs 
    public double[] think(double[] inputs){
        double[] outputH = hiddenLayer.forward(inputs);
        double[] outputO = outputLayer.forward(outputH);
        return outputO;
        
    }
    
    
    
    
    
    
    
}
