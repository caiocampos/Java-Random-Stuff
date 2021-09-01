package edi.remessa;

import edi.EdiField;
import edi.EdiUtils;

public class SantanderCnpj extends SantanderInscricao {
	// Número do CNPJ do sacado (Corpo do CNPJ)
	// 221 228 008 Num
	private EdiField corpoCnpj = new EdiField(8, true);

	// Filial (Filial do CNPJ)
	// 229 232 004 Num
	private EdiField filial = new EdiField(4, true);

	// Controle (Controle do CNPJ)
	// 233 234 002 Num
	private EdiField controle = new EdiField(2, true);

	public static SantanderCnpj create(String cnpj) {
		cnpj = EdiUtils.clearNumber(cnpj);
		cnpj = EdiUtils.format(cnpj, 14, true);
		String corpoCnpj = cnpj.substring(0, 8);
		String filial = cnpj.substring(8, 12);
		String controle = cnpj.substring(12, 14);
		return create(corpoCnpj, filial, controle);
	}

	public static SantanderCnpj create(String corpoCnpj, String filial, String controle) {
		corpoCnpj = EdiUtils.clearNumber(corpoCnpj);
		filial = EdiUtils.clearNumber(filial);
		controle = EdiUtils.clearNumber(controle);
		SantanderCnpj instance = new SantanderCnpj();
		instance.corpoCnpj.setValue(corpoCnpj);
		instance.filial.setValue(filial);
		instance.controle.setValue(controle);
		return instance;
	}

	public EdiField getCorpoCnpj() {
		return corpoCnpj;
	}

	public EdiField getFilial() {
		return filial;
	}

	public EdiField getControle() {
		return controle;
	}

}
