package org.example.janus.codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;

public class NegativeBehavior extends Codelet {
    Memory perceptual;
    MemoryContainer answerMemory;

    @Override
    public void accessMemoryObjects() {
        if(perceptual == null){
            perceptual = this.getInput("perceptualMemory");
        }
        if(answerMemory == null){
            answerMemory = (MemoryContainer) this.getOutput("answerMemory");
        }
    }
    @Override
    public void proc() {
        Object obj = perceptual.getI();
        if(obj != null){
            answerMemory.setI("Not Recidivism", this.activation, "negative");
        }
    }

    @Override
    public void calculateActivation() {
        Object obj = perceptual.getI();
        if(obj != null){
            double activation;
            if((Integer)obj == 0){
                activation = 1.0;
            }
            else{
                activation = 0.0;
            }
            try{this.setActivation(activation);}
            catch (Exception e){e.printStackTrace();}
        }
    }
}
