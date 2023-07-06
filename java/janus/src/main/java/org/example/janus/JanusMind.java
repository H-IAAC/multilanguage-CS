package org.example.janus;

import br.unicamp.cst.core.entities.MemoryContainer;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.entities.Mind;
import org.example.janus.codelets.AffirmativeBehavior;
import org.example.janus.codelets.DisplayMotor;
import org.example.janus.codelets.ExtraPerceptual;
import org.example.janus.codelets.NegativeBehavior;

public class JanusMind extends Mind {
    String url;

    public JanusMind(String url){
        this.url = url;
        this.setupMind();
    }

    public void setupMind(){
        //declaring memories
        MemoryObject perceptualMemory = createMemoryObject("perceptualMemory");
        MemoryContainer answerMemory = createMemoryContainer("answerMemory");

        //declaring, mounting and inserting Codelets
        ExtraPerceptual extraPerceptual = new ExtraPerceptual(this.url);
        extraPerceptual.addOutput(perceptualMemory);
        this.insertCodelet(extraPerceptual);

        AffirmativeBehavior affirmativeBehavior = new AffirmativeBehavior();
        affirmativeBehavior.addInput(perceptualMemory);
        affirmativeBehavior.addOutput(answerMemory);
        this.insertCodelet(affirmativeBehavior);

        NegativeBehavior negativeBehavior = new NegativeBehavior();
        negativeBehavior.addInput(perceptualMemory);
        negativeBehavior.addOutput(answerMemory);
        this.insertCodelet(negativeBehavior);

        DisplayMotor displayMotor = new DisplayMotor();
        displayMotor.addInput(answerMemory);
        this.insertCodelet(displayMotor);

    }

}
