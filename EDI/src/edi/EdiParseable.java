package edi;

import java.lang.reflect.Field;

public abstract class EdiParseable {
	public String getParsed() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		for (Field field : fields) {
			Object o = null;
			field.setAccessible(true);
			try {
				o = field.get(this);
				EdiParseable parseable = (o instanceof EdiParseable ? (EdiParseable) o : null);
				sb.append(o == null ? "" : parseable.getParsed());
			} catch (Exception e) {
				sb.append("¢");
				e.printStackTrace();
			}
			field.setAccessible(false);
		}
		return sb.toString();
	}
}
