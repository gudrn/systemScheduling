package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import core.*;

public class Process implements Comparable<Process>{
	
	private SimpleStringProperty processName;
    private SimpleIntegerProperty arrivalTime;
    private SimpleIntegerProperty burstTime;
    private SimpleIntegerProperty waitingTime;
    private SimpleIntegerProperty turnaroundTime;
    private SimpleDoubleProperty NormalizedTT;
    private SimpleIntegerProperty TimeburstTime;//실시간 처리
	public boolean done;
	public int processor;
	public boolean clear=false;
	
    public Process(String processName, int arrivalTime, int burstTime) {
        this.processName = new SimpleStringProperty(processName);
        this.arrivalTime = new SimpleIntegerProperty(arrivalTime);
        this.burstTime = new SimpleIntegerProperty(burstTime);
        this.TimeburstTime=new SimpleIntegerProperty(burstTime);
        this.waitingTime = new SimpleIntegerProperty(0);
        this.turnaroundTime = new SimpleIntegerProperty(0);
        this.NormalizedTT = new SimpleDoubleProperty(0);
        done = false;
        processor = -1;
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
    public Integer getWaitingTime() {
    	return waitingTime.get();
    }
    public Integer getturnaroundTime() {
    	return turnaroundTime.get();
    }
    public Integer getNormalizedTT() {
    	return turnaroundTime.get();
    }
    public Integer getTimeburstTime() {
    	return TimeburstTime.get();
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
    public void setWaitingTime(Integer waitingtimes) {
    	this.waitingTime.set(waitingtimes);
    }
    public void setNormalizedTT(double d) {
    	this.NormalizedTT.set(d);
    }
    public void setturnaroundTime(Integer turnaroundTime) {
    	this.turnaroundTime.set(turnaroundTime);
    }
    public void setTimeburstTime(Integer time) {
    	this.TimeburstTime.set(time);
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
    public SimpleDoubleProperty NormalizedTTProperty() {
        return NormalizedTT;
    }
    
    @Override
    public int compareTo(Process otherProcess) {
        if (this.arrivalTime != otherProcess.arrivalTime) {
            return this.arrivalTime.get() - otherProcess.getArrivalTime();
        } else {
            return this.burstTime.get() - otherProcess.getBurstTime();
        }
    }

}

