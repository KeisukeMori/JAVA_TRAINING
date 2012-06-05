package ch09.ex09_01;

public class Infinity {
	public static void main(String[] args) {
		Double pInfinity = Double.POSITIVE_INFINITY;
		Double nInfinity = Double.NEGATIVE_INFINITY;
		System.out.println("------------------------------------");
		System.out.println("∞ + ∞  = " + (pInfinity + pInfinity));
		System.out.println("∞ + -∞ = " + (pInfinity + nInfinity));
		System.out.println("------------------------------------");		
		System.out.println("∞ - ∞  = " + (pInfinity - pInfinity));
		System.out.println("∞ - -∞ = " + (pInfinity - nInfinity));
		System.out.println("------------------------------------");		
		System.out.println("∞ * ∞  = " + (pInfinity * pInfinity));		
		System.out.println("∞ * -∞ = " + (pInfinity * nInfinity));
		System.out.println("------------------------------------");		
		System.out.println("∞ / ∞  = " + (pInfinity / pInfinity));
		System.out.println("∞ / -∞ = " + (pInfinity / nInfinity));
		System.out.println("------------------------------------");
	}
}