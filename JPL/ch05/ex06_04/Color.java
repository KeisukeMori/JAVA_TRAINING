package ch05.ex06_04;

enum Color {
	GREEN("GREEN"),
	YELLOW("YELLOW"),
	RED("RED");

	String name;
	Color(String name) {
		this.name = name;
	}
	String getColor() {
		return this.name;
	}
}
