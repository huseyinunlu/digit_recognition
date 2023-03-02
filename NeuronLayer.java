/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digitrecognition;

import java.util.ArrayList;


public class NeuronLayer {
    int numNeurons;
    int numInputs;
    double bias = Math.random();
    ArrayList<Neuron> neurons = new ArrayList<>();
    
    
    NeuronLayer(int numNeurons, int numInputs){
        this.numNeurons = numNeurons;
        this.numInputs = numInputs;
        for(int i=0;i<numNeurons;i++)
        neurons.add(new Neuron(numInputs, bias));
    }
    
    
    
    
    public double[] forward(double[] inputs){
        double[] outputs = new double[numNeurons];
        for(int i=0; i<neurons.size();i++){
            outputs[i] = neurons.get(i).output(inputs);
        }
        return outputs;
    }
    
}
