package org.example.janus.codelets;

import br.unicamp.cst.core.entities.Memory;
import br.unicamp.cst.io.rest.HttpCodelet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ExtraPerceptual extends HttpCodelet {
    Memory perceptual;
    String GET_URL;

    public ExtraPerceptual(String GET_URL){
        super();
        this.GET_URL = GET_URL;
    }

    @Override
    public void accessMemoryObjects() {
        if(perceptual == null){
            perceptual = this.getOutput("perceptualMemory");
        }
    }

    @Override
    public void proc() {
        try{

            String json = sendGET(GET_URL);
            JsonArray jsonAnswer = (JsonArray) JsonParser.parseString(json);
            JsonObject dctMemory = jsonAnswer.get(0).getAsJsonObject();

            int I = dctMemory.get("I").getAsJsonArray().get(0).getAsInt();

            perceptual.setI(I);
        }
        catch (Exception e){e.printStackTrace();}


    }

    @Override
    public void calculateActivation() {

    }
}
