package ch24.ex24_02;

import java.util.Locale;
import java.util.Currency;

class CurrencyDisplay {
	public static void main(String[] args) {
		
		Locale[] locales = {
				Locale.US,
				Locale.CHINA,
				Locale.FRANCE,
				Locale.GERMANY,
				Locale.JAPAN,
				Locale.UK,
				};

		
		Currency[] currencies = {
				Currency.getInstance("USD"),
				Currency.getInstance("CNY"),
				Currency.getInstance("DEM"),
				Currency.getInstance("FRF"),
				Currency.getInstance("JPY"),
				Currency.getInstance("GBP"),
				};
		
		for (int i = 0; i < currencies.length; ++i) {
				System.out.println(currencies[i] + ": " +  currencies[i].getSymbol(locales[i]));
				}
		}
	}
