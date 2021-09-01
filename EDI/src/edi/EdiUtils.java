package edi;

import java.math.BigDecimal;

public class EdiUtils {
	public static String format(String s, int length, boolean isNumber) {
		String newValue = s;
		if (newValue == null) {
			newValue = "";
		}
		int currentLength = newValue.length();
		if (currentLength > length) {
			return newValue.substring(0, length);
		} else if (currentLength < length) {
			int fillSize = length - currentLength;
			StringBuilder sb = new StringBuilder();
			if (isNumber) {
				for (int i = 0; i < fillSize; i++) {
					sb.append("0");
				}
				sb.append(newValue);
				return sb.toString();
			} else {
				sb.append(newValue);
				for (int i = 0; i < fillSize; i++) {
					sb.append(" ");
				}
				return sb.toString();
			}
		}
		return newValue;
	}

	public static String clearNumber(String s) {
		return s == null ? "0" : s.replaceAll("^[0-9]", "");
	}

	public static String formataValor(Number valor) {
		return formataValor(new BigDecimal(valor.doubleValue()));
	}

	public static String formataValor(BigDecimal valor) {
		return valor == null ? "0" : valor.setScale(2, BigDecimal.ROUND_FLOOR).toString().replaceAll("[^0-9]", "");
	}
}
