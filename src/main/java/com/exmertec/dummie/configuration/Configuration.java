package com.exmertec.dummie.configuration;

public class Configuration {
    private Integer floor;
    private CycleLogic cycleLogic;
    private GenerationStrategy generationStrategy;

    public Configuration(CycleLogic cycleLogic, GenerationStrategy strategy) {
        setCycleLogic(cycleLogic);
        setGenerationStrategy(strategy);
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public CycleLogic getCycleLogic() {
        return cycleLogic;
    }

    public void setCycleLogic(CycleLogic cycleLogic) {
        this.cycleLogic = cycleLogic;
        if (cycleLogic == CycleLogic.LEVEL) { // set default floor value
            floor = 2;
        }
    }

    public GenerationStrategy getGenerationStrategy() {
        return generationStrategy;
    }

    public void setGenerationStrategy(GenerationStrategy generationStrategy) {
        this.generationStrategy = generationStrategy;
    }
}
