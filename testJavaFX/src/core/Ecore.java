package core;
import application.Process;
public class Ecore {
	Process p;
	private int visit=0;//방문 여부
	private static int TotalTime=0;//실시간
	private static final int excuteTime=1;
	private static int timeq=0;
	
	public Ecore(Process p) {
		this.p=p;
	}
	
	public Ecore(Process p,int timeq) {
		this.p=p;
		this.timeq=timeq;
	}
	
	private void FCFS() {
		
		
	}
	
	public void setTotalTime(int time) {
		this.TotalTime=time;
	}
	
	

}
