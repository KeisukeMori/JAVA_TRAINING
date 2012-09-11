package ch17.ex17_03;

public interface Resource {
	void use(Object key, Object... args);
	void release();
}
