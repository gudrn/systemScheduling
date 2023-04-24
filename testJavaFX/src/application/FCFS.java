package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;

public class FCFS {
	public ObservableList<Process> run(ObservableList<Process> list, int numProcessors, int core) {
		ObservableList<Process> FCFSList=FXCollections.observableArrayList();
		for(Process p: list)//깊은 복사
		{
			FCFSList.add(new Process(p.getProcessName(),p.getArrivalTime(),p.getBurstTime()));
		}
        int n = FCFSList.size();
        int[] processorTime = new int[numProcessors];
        Arrays.fill(processorTime, 0);
        int Time = 0;
        int completedProcessCount = 0;
        FXCollections.sort(FCFSList, Comparator.comparingInt(Process::getArrivalTime));
        
        int currentTime = 0;
        
        for (Process p : FCFSList) {
        	int waitingTime = Math.max(0, currentTime - p.getArrivalTime()); // 대기 시간이 음수가 되지 않도록 조건문 추가
            p.setWaitingTime(waitingTime);
            currentTime = Math.max(currentTime, p.getArrivalTime()) + p.getBurstTime(); // 종료 시간 계산
            p.setturnaroundTime(currentTime - p.getArrivalTime());
        }
        for (Process process : FCFSList) {
            double ntt = (double) process.getturnaroundTime() / process.getBurstTime();
            process.setNormalizedTT(ntt);
        }
        
        return FCFSList;
    }
}
