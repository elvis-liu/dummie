package com.exmertec.dummie;

public class Configuration {
    private Integer floor;
    private CycleLogic cycleLogic;

    public Configuration(CycleLogic cycleLogic) {
        setCycleLogic(cycleLogic);
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
}
