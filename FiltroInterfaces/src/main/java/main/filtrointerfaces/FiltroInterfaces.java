package main.filtrointerfaces;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author Caio
 */
public class FiltroInterfaces {

	/**
	 * @param args argumentos da linah de comando
	 * 
	 * args[0]: caminho do arquivo a ser analisado
	 * args[1]: Se 'S' então imprime a interface física
	 */
	public static void main(String[] args) {
		String fileName;
		if (args.length > 0) {
			fileName = args[0];
		} else {
			fileName = "C:\\data\\FiltroInterfaces\\a-bb-rt-vrd-001-mx80.txt";
		}
		final ArrayList<PhysicalInterface> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			Pattern physicalPattern = Pattern.compile("Physical interface: (.*?),");
			stream
					.filter(line -> !line.isEmpty() && line.contains("interface"))
					.forEach(line -> {
						if (line.startsWith("Physical")) {
							String desc = getMatch(line, physicalPattern);
							if (desc != null) {
								PhysicalInterface phys = new PhysicalInterface(desc);
								list.add(phys);
							}
						} else if (list.size() > 0) {
							PhysicalInterface phys = list.get(list.size() - 1);
							String desc = getMatch(line, Pattern.compile(phys.description + "\\.([0-9]+)"));
							if (desc != null) {
								LogicalInterface logical = new LogicalInterface(desc);
								phys.logicalInterfaces.add(logical);
							}
						}
					});
		} catch (Exception e) {
			System.out.println("Houve um erro ao processar o arquivo!");
		}
		final boolean showPhysical = args.length < 2 ? true : args[1].equalsIgnoreCase("S");
		list.forEach(phys -> {
			if (showPhysical) {
				System.out.println(phys.description);
			}
			phys.logicalInterfaces.forEach(logical -> {
				System.out.println(phys.description + " - " + logical.description);
			});
		});
	}

	/**
	 * Faz a busca na linha segundo um padrão
	 *
	 * @param line texto a ser verificado
	 * @param p padrão a ser encontrado
	 */
	private static String getMatch(String line, Pattern p) {
		String res = null;
		Matcher matcher = p.matcher(line);
		if (matcher.find()) {
			try {
				res = matcher.group(1);
			} catch (Exception e) {
				res = null;
			}
		}
		return res;
	}
}
