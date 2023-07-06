package org.example.janus.codelets;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.core.entities.MemoryContainer;

public class DisplayMotor extends Codelet {
    MemoryContainer answerMemory;

    @Override
    public void accessMemoryObjects() {
        if(answerMemory == null){
            answerMemory = (MemoryContainer) this.getInput("answerMemory");
        }
    }

    @Override
    public void proc() {
        Object answer = answerMemory.getI();

        if(answer != null){
            System.out.println("The individual is predicted to: " + answer);
        }

    }

    @Override
    public void calculateActivation() {

    }

}
