package main.filtrointerfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio
 */
public class PhysicalInterface {

	String description;
	List<LogicalInterface> logicalInterfaces;

	public PhysicalInterface(String description) {
		this.description = description;
		this.logicalInterfaces = new ArrayList<>();
	}
}
