package edi.remessa;

import edi.EdiField;
import edi.EdiParseable;

public class SantanderRemessaHeader extends EdiParseable {
	// Código do Registro
	// 001 001 001 Num
	private EdiField codRegistro = new EdiField(1, "0", true);

	// Constante
	// 002 026 025 Alfa
	private EdiField const1 = new EdiField(25, "1REMESSA01COBRANCA");

	// Zero
	// 027 027 001 Num
	private EdiField constZeros1 = new EdiField(1, "0", true);

	// Código de transmissão (Código fornecido pelo banco)
	// 028 039 012 Num
	private EdiField codTransmissao = new EdiField(12, true);

	// Brancos
	// 040 046 007 Alfa
	private EdiField constBrancos1 = new EdiField(7);

	// Nome do Cedente
	// 047 076 030 Alfa
	private EdiField nomeCedente = new EdiField(30);

	// Identif. do Banco
	// 077 079 003 Num
	private EdiField IdBanco = new EdiField(3, "033", true);

	// Nome do Banco
	// 080 094 015 Alfa
	private EdiField nomeBanco = new EdiField(15);

	// Data do Processamento (DDMMAA)
	// 095 100 006 Num
	private EdiField dtProcessamento = new EdiField(6, true);

	// Constante
	// 101 108 008 Alfa
	private EdiField const2 = new EdiField(8, "01600BPI");

	// Brancos
	// 109 394 286 Alfa
	private EdiField constBrancos2 = new EdiField(286);

	// Número de Seqüência
	// 395 400 006 Num
	private EdiField numSequencia = new EdiField(6, true);

	public EdiField getCodTransmissao() {
		return codTransmissao;
	}

	public EdiField getNomeCedente() {
		return nomeCedente;
	}

	public EdiField getNomeBanco() {
		return nomeBanco;
	}

	public EdiField getDtProcessamento() {
		return dtProcessamento;
	}

	public EdiField getNumSequencia() {
		return numSequencia;
	}

	public EdiField getCodRegistro() {
		return codRegistro;
	}

	public EdiField getConst1() {
		return const1;
	}

	public EdiField getConstZeros1() {
		return constZeros1;
	}

	public EdiField getConstBrancos1() {
		return constBrancos1;
	}

	public EdiField getIdBanco() {
		return IdBanco;
	}

	public EdiField getConst2() {
		return const2;
	}

	public EdiField getConstBrancos2() {
		return constBrancos2;
	}

}
