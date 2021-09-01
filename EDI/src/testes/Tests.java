package testes;

import org.junit.jupiter.api.Test;

import edi.EdiField;
import edi.EdiParseable;
import edi.remessa.SantanderCnpj;
import edi.remessa.SantanderCpf;
import edi.remessa.SantanderRemessa;
import edi.remessa.SantanderRemessaDetalhe;
import edi.remessa.SantanderRemessaHeader;

class Tests {

	@Test
	void testGetParsed() {
		EdiParseable test;
		test = new EdiField();
		testGetParsedPrint(test);

		EdiField test1 = new EdiField();
		test1.setValue("teste");
		test = test1;
		testGetParsedPrint(test);

		test = new EdiField(3, "BR");
		testGetParsedPrint(test);

		test = new EdiField(3, "BRRRRRRRRRR");
		testGetParsedPrint(test);

		test = new EdiField(3, "33", true);
		testGetParsedPrint(test);

		test = new EdiField(3, "3333333333333333", true);
		testGetParsedPrint(test);

		SantanderRemessa test2 = new SantanderRemessa();
		test2.setHeader(new SantanderRemessaHeader());
		SantanderRemessaDetalhe detalhe = new SantanderRemessaDetalhe();
		detalhe.getCodInscricao().setValue("01");
		detalhe.setInscrCedente(SantanderCpf.create("94298815072"));
		detalhe.getCodInscrSacado().setValue("02");
		detalhe.setInscrSacado(SantanderCnpj.create("07721692000106"));
		test2.setDetalhe(detalhe);
		test = test2;
		testGetParsedPrint(test, false);
	}

	void testGetParsedPrint(EdiParseable test) {
		testGetParsedPrint(test, true);
	}

	void testGetParsedPrint(EdiParseable test, boolean inline) {
		if (inline) {
			System.out.println("{" + test.getParsed() + "}");
		} else {
			System.out.println("{" + System.lineSeparator() + test.getParsed() + "}");
		}
	}

}
