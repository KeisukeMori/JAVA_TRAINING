package ch03.ex03_02;

class X {	
    {
    	traceStep();
    }
    
    protected int xMask = 0x00ff;
    
    {
    	traceStep();
    }
    
    protected int fullMask;    
	private static int step=0;
    
    public X() {
        fullMask = xMask;
        traceStep();
    }
    
    public int mask(int orig) {
        return (orig & fullMask);
    }
    
	public void traceStep(){
		System.out.printf("%-2d 0x%-4x 0x%-4x ", step, getXMask(), getFullMask());
		addStep();
	}
	
	public int getStep() {
		return step;
		}
	public void addStep() {
		step++;
		}
	
	public int getXMask() {
		return xMask;
		}
	
	public int getFullMask(){ 
		return fullMask;
		}

}


