package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Process {
	private SimpleStringProperty processName;
    private SimpleIntegerProperty arrivalTime;
    private SimpleIntegerProperty burstTime;
    private SimpleIntegerProperty waitingTime;
    private SimpleIntegerProperty turnaroundTime;
    private SimpleIntegerProperty NormalizedTT;
    
    public Process(String processName, int arrivalTime, int burstTime) {
        this.processName = new SimpleStringProperty(processName);
        this.arrivalTime = new SimpleIntegerProperty(arrivalTime);
        this.burstTime = new SimpleIntegerProperty(burstTime);
        this.waitingTime = new SimpleIntegerProperty(0);
        this.turnaroundTime = new SimpleIntegerProperty(0);
        this.NormalizedTT = new SimpleIntegerProperty(0);
    }
    
    public String getProcessName() {
        return processName.get();
    }
    public Integer getArrivalTime() {
        return arrivalTime.get();
    }
    public Integer getBurstTime() {
        return burstTime.get();
    }

    public void setProcessName(String processName) {
        this.processName.set(processName);
    }
    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime.set(arrivalTime);
    }
    public void setBurstTime(Integer burstTime) {
        this.burstTime.set(burstTime);
    }

    public SimpleStringProperty processNameProperty() {
        return processName;
    }
    public SimpleIntegerProperty arrivalTimeProperty() {
        return arrivalTime;
    }
    public SimpleIntegerProperty burstTimeProperty() {
        return burstTime;
    }
    public SimpleIntegerProperty waitingTimeProperty() {
        return waitingTime;
    }
    public SimpleIntegerProperty turnaroundTimeProperty() {
        return turnaroundTime;
    }
    public SimpleIntegerProperty NormalizedTTProperty() {
        return NormalizedTT;
    }

}

