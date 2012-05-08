package ch06.ex06_03;

	public enum Verbose {
	SILENT(0),
	TERSE(1),
	NORMAL(2),
	VERBOSE(3);
	
	int level;
	Verbose(int level) {
		this.level = level;
	}
	
	void setVerbosity(int level) {
		
	}
	int getVerbosity() {
		return level;
	}
}

