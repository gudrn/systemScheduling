package core;

import application.Process;

public class Pcore {
	Process p=null;
	private boolean visit=false;//방문 여부
	private int TotalTime=0;//실시간
	private static final int excuteTime=2;
	private static int timeq=0;
	
	public Process getP() {
		return p;
	}

	public void setP(Process p) {
		this.p = p;
	}

	public boolean isVisit() {
		return visit;
	}

	public void setVisit(boolean visit) {
		this.visit = visit;
	}

	public int getTotalTime() {
		return TotalTime;
	}

	public void setTotalTime(int totalTime) {
		TotalTime = totalTime;
	}

	public static int getTimeq() {
		return timeq;
	}

	public static void setTimeq(int timeq) {
		Pcore.timeq = timeq;
	}

	public void FCFS(int Totaltime) {
		if(this.p==null)
			return;
		else if(this.p!=null) {
			this.TotalTime=Totaltime;
			this.p.setTimeburstTime(this.p.getTimeburstTime()-excuteTime);
			if(this.p.getTimeburstTime()<=0) {
				this.p.setturnaroundTime(Totaltime-this.p.getArrivalTime());
				this.p.setWaitingTime(this.p.getNormalizedTT()-this.p.getBurstTime());
				this.p.setNormalizedTT((double)this.p.getturnaroundTime()/this.p.getBurstTime());
				this.p=null;
				this.visit=false;
				return;
			}
		}
		return;
	}
}
