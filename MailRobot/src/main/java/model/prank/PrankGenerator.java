package model.prank;

import config.IConfigurationManager;

import java.util.ArrayList;
import java.util.List;

public class PrankGenerator {

    private IConfigurationManager configurationManager;

    public PrankGenerator(IConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    /**
     * Il faut implémenter les fonctional requirements dans cette classe
     * @return
     */
    public List<Prank> generatesPranks(){
        List<Prank> pranks = new ArrayList<>();

        return pranks;
    }
}
