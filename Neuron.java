
package digitrecognition;


public class Neuron {
    
    int numInputs;
    double bias;
    double output;
    
    
    double[] weights;
    double[] inputs;
    
    
    
    public Neuron(int numInputs, double bias){
        
        //initializes weights array according to number of inputs
        weights = new double[numInputs];
        
        //gives random weights
        for(int i=0;i<numInputs;i++){
            weights[i] = Math.random();
        }
    }
    
    
    
    public double output(double[] inputs){
        //assigns to this.inputs for further use
        this.inputs = inputs;
        double total=0;
        
        for(int i=0;i<inputs.length;i++){
            //makes summation
            total += inputs[i] * weights[i];
        }
        output = sigmoid(total + bias);
        return output;
    }
    
    
    
    
    public double errorChange(double targetOutput){
        return derivative_sigmoid(output) * error(targetOutput);
    }
    
    
    
    private double error(double targetOutput){
        return -1*(targetOutput-output);
    }
    
    
    private double sigmoid(double x){
        return 1/(1+ Math.exp(-1*x));
    }
    private double derivative_sigmoid(double x){
        return x * (1-x);
    }
}
