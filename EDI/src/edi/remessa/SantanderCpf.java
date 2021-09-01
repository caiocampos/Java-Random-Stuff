package edi.remessa;

import edi.EdiField;
import edi.EdiUtils;

public class SantanderCpf extends SantanderInscricao {
	// Número do CPF do sacado (Corpo do CPF)
	// 221 229 009 Num
	private EdiField corpoCpf = new EdiField(9, true);

	// Zeros
	// 230 232 003 Num
	private EdiField constZeros1 = new EdiField(3, "000", true);

	// Controle (Controle do CPF)
	// 233 234 002 Num
	private EdiField controle = new EdiField(2, true);

	public static SantanderCpf create(String cpf) {
		cpf = EdiUtils.clearNumber(cpf);
		cpf = EdiUtils.format(cpf, 11, true);
		String corpoCpf = cpf.substring(0, 9);
		String controle = cpf.substring(9, 11);
		return create(corpoCpf, controle);
	}

	public static SantanderCpf create(String corpoCpf, String controle) {
		corpoCpf = EdiUtils.clearNumber(corpoCpf);
		controle = EdiUtils.clearNumber(controle);
		SantanderCpf instance = new SantanderCpf();
		instance.corpoCpf.setValue(corpoCpf);
		instance.controle.setValue(controle);
		return instance;
	}

	public EdiField getCorpoCpf() {
		return corpoCpf;
	}

	public EdiField getControle() {
		return controle;
	}

	public EdiField getConstZeros1() {
		return constZeros1;
	}

}
