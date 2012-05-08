package ch06.ex06_05;

enum Color {
	GREEN("GREEN") {
		String getColor() {
			return this.name;
		}
	},
	YELLOW("YELLOW") {
		String getColor() {
			return this.name;
		}
	},
	RED("RED") {
		String getColor() {
			return this.name;
		}
	};
	String name;

	Color(String name) {
		this.name = name;
	}
	abstract String getColor();}
