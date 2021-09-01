package edi.remessa;

import edi.EdiField;
import edi.EdiParseable;

public class SantanderRemessaDetalhe extends EdiParseable {
	// Código do Registro
	// 001 001 001 Num
	private EdiField codRegistro = new EdiField(1, "1", true);

	// Código de Inscrição
	// '01' - CPF
	// '02' - CNPJ
	// 002 003 002 Num
	private EdiField codInscricao = new EdiField(2, true);

	// CPF/CNPJ do Cedente
	// 004 017 014 Num
	private SantanderInscricao inscrCedente;

	// Zero
	// 018 018 001 Num
	private EdiField constZeros1 = new EdiField(1, "0", true);

	// Agência do Cedente
	// 019 022 004 Num
	private EdiField AgCedente = new EdiField(4, true);

	// Zero
	// 023 023 001 Num
	private EdiField constZeros2 = new EdiField(1, "0", true);

	// Código do convênio
	// 024 030 007 Num
	private EdiField codConvenio = new EdiField(7, true);

	// Brancos
	// 031 038 008 Alfa
	private EdiField constBrancos1 = new EdiField(8);

	// Controle do participante
	// 039 062 025 Alfa
	private EdiField contrlParticipante = new EdiField(25);

	// Zeros
	// 063 064 002 Num
	private EdiField constZeros3 = new EdiField(2, "00", true);

	// Num. do Título no Banco (Nosso Número)
	// 065 071 007 Num
	private EdiField nossoNumero = new EdiField(7, true);

	// Incidência da Multa
	// '0' - Sobre o valor Título
	// '1' - Sobre o valor Corrigido
	// 072 072 001 Num
	private EdiField incidenciaMulta = new EdiField(1, true);

	// Número de Dias para Multa
	// '00' - Após Vencimento
	// '01 - 99' - Número de Dias Após Vencimento
	// 073 074 002 Num
	private EdiField numDiasMulta = new EdiField(2, true);

	// Tipo da Multa
	// '0' - Valor
	// '1' - Taxa
	// 075 075 001 Num
	private EdiField tpMulta = new EdiField(1, true);

	// Multa
	// V99 - Valor ou Taxa
	// 076 088 013 Num
	private EdiField multa = new EdiField(13, true);

	// Brancos
	// 089 095 007 Alfa
	private EdiField constBrancos2 = new EdiField(7);

	// Zeros
	// 096 104 009 Num
	private EdiField constZeros4 = new EdiField(9, true);

	// Brancos
	// 105 105 001 Alfa
	private EdiField constBrancos3 = new EdiField(1);

	// Zeros
	// 106 107 002 Num
	private EdiField constZeros5 = new EdiField(2, "00", true);

	// Uso do banco
	// 108 108 001 Num
	private EdiField usoBanco1 = new EdiField(1, "1", true);

	// Código da Ocorrência
	// '01' – Entrada
	// '02' – Baixa
	// '04' – Conceder Abatimento
	// '05' – Cancelar Abatimento
	// '06' – Alterar Vencimento
	// '07' – Alterar Controle do Participante
	// '08' – Alterar Número do Cedente
	// '09' – Protestar
	// '10' – Sustar protesto e conservar em carteira (após título ser enviado a
	// cartório)
	// '11' – Não protestar (antes do título ser enviado a cartório)
	// '13' – Cancelar Multa
	// '14' – Alterar Multa
	// '16' – Sustar protesto e baixar o título (após título ser enviado a cartório)
	// '17' – Conceder Desconto
	// '18' – Cancelar Protesto Automático
	// 109 110 002 Num
	private EdiField codOcorrencia = new EdiField(2, true);

	// Número do Título no Cedente (Seu número)
	// 111 120 010 Alfa
	private EdiField numTituloCedente = new EdiField(10);

	// Data de Vencimento
	// DDMMAA
	// '333333' - À Vista
	// '222222' - Contra Apres.
	// 121 126 006 Num
	private EdiField dtVencimento = new EdiField(6, true);

	// Valor do Título
	// V99
	// 127 139 013 Num
	private EdiField valTitulo = new EdiField(13, true);

	// Identificação do Banco
	// 140 142 003 Num
	private EdiField idBanco = new EdiField(3, "033", true);

	// Zeros
	// 143 147 005 Num
	private EdiField constZeros6 = new EdiField(5, "00000", true);

	// Espécie de Título
	// '01' – Duplicata
	// '02' - Nota promissória
	// '03' - Nota de seguro
	// '05' – Recibo
	// '06' – Banco emitirá Boleto
	// '07' – Banco não emitirá Boleto
	// '08' – Duplicata de serviço
	// '99' – Outros
	// 148 149 002 Num
	private EdiField especieTitulo = new EdiField(2, true);

	// Aceite
	// 'A' – Aceito
	// 'N' - Não Aceito
	// 150 150 001 Alfa
	private EdiField aceite = new EdiField(1);

	// Data de Emissão (DDMMAA)
	// 151 156 006 Num
	private EdiField dtEmissao = new EdiField(6, true);

	// Código do Protesto
	// '00' – Conforme cadastro do convênio
	// '03 - 55' Número de dias vencidos para protesto
	// '99' - Não protestar
	// 157 158 002 Num
	private EdiField codProtesto = new EdiField(2, true);

	// Brancos
	// 159 160 002 Alfa
	private EdiField constBrancos4 = new EdiField(2);

	// Tipo de Juros
	// '0' - Valor
	// '1' - Taxa
	// 161 161 001 Num
	private EdiField tpJuros = new EdiField(1, true);

	// Juros de Mora
	// V99 - Valor/Dia ou Taxa/Mês
	// 162 173 012 Num
	private EdiField jurosMora = new EdiField(12, true);

	// Data Limite para Desconto
	// DDMMAA - data limite
	// '888888' - Desc. Até data de Pagamento
	// '999999' - Desc. Dia Corrido de antecipação
	// 174 179 006 Num
	private EdiField dtLimiteDesconto = new EdiField(6, true);

	// Valor Desconto
	// V99
	// 180 192 013 Num
	private EdiField valDesconto = new EdiField(13, true);

	// Zeros
	// 193 205 013 Num
	private EdiField constZeros7 = new EdiField(13, true);

	// Abatimento
	// V99
	// 206 218 013 Num
	private EdiField abatimento = new EdiField(13, true);

	// Cód. Inscr. Sacado
	// '01' - CPF
	// '02' - CNPJ
	// 219 220 002 Num
	private EdiField codInscrSacado = new EdiField(2, true);

	// CPF ou CNPJ do Sacado
	// 221 234 014 Num
	private SantanderInscricao inscrSacado;

	// Nome do sacado
	// 235 274 040 Alfa
	private EdiField nomeSacado = new EdiField(40);

	// Endereço do Sacado
	// 275 314 040 Alfa
	private EdiField enderecoSacado = new EdiField(40);

	// Bairro
	// 315 326 012 Alfa
	private EdiField bairro = new EdiField(12);

	// CEP
	// 327 331 005 Num
	private EdiField cep = new EdiField(5, true);

	// Complemento do C.E.P
	// 332 334 003 Alfa
	private EdiField compCep = new EdiField(3);

	// Cidade
	// 335 349 015 Alfa
	private EdiField cidade = new EdiField(15);

	// Estado
	// 350 351 002 Alfa
	private EdiField estado = new EdiField(2);

	// Nome do sacador
	// 352 391 040 Alfa
	private EdiField nomeSacador = new EdiField(40);

	// Moeda
	// '0' - Reais
	// 392 392 001 Num
	private EdiField moeda = new EdiField(1, "0", true);

	// Uso do banco
	// 393 394 002 Num
	private EdiField usoBanco2 = new EdiField(2, "07", true);

	// Número de Seqüência
	// 395 400 006 Num
	private EdiField numSequencia = new EdiField(6, true);

	public EdiField getCodInscricao() {
		return codInscricao;
	}

	public SantanderInscricao getInscrCedente() {
		return inscrCedente;
	}

	public void setInscrCedente(SantanderInscricao inscrCedente) {
		this.inscrCedente = inscrCedente;
	}

	public EdiField getAgCedente() {
		return AgCedente;
	}

	public EdiField getCodConvenio() {
		return codConvenio;
	}

	public EdiField getContrlParticipante() {
		return contrlParticipante;
	}

	public EdiField getNossoNumero() {
		return nossoNumero;
	}

	public EdiField getIncidenciaMulta() {
		return incidenciaMulta;
	}

	public EdiField getNumDiasMulta() {
		return numDiasMulta;
	}

	public EdiField getTpMulta() {
		return tpMulta;
	}

	public EdiField getMulta() {
		return multa;
	}

	public EdiField getCodOcorrencia() {
		return codOcorrencia;
	}

	public EdiField getNumTituloCedente() {
		return numTituloCedente;
	}

	public EdiField getDtVencimento() {
		return dtVencimento;
	}

	public EdiField getValTitulo() {
		return valTitulo;
	}

	public EdiField getEspecieTitulo() {
		return especieTitulo;
	}

	public EdiField getAceite() {
		return aceite;
	}

	public EdiField getDtEmissao() {
		return dtEmissao;
	}

	public EdiField getCodProtesto() {
		return codProtesto;
	}

	public EdiField getTpJuros() {
		return tpJuros;
	}

	public EdiField getJurosMora() {
		return jurosMora;
	}

	public EdiField getDtLimiteDesconto() {
		return dtLimiteDesconto;
	}

	public EdiField getValDesconto() {
		return valDesconto;
	}

	public EdiField getAbatimento() {
		return abatimento;
	}

	public EdiField getCodInscrSacado() {
		return codInscrSacado;
	}

	public SantanderInscricao getInscrSacado() {
		return inscrSacado;
	}

	public void setInscrSacado(SantanderInscricao inscrSacado) {
		this.inscrSacado = inscrSacado;
	}

	public EdiField getNomeSacado() {
		return nomeSacado;
	}

	public EdiField getEnderecoSacado() {
		return enderecoSacado;
	}

	public EdiField getBairro() {
		return bairro;
	}

	public EdiField getCep() {
		return cep;
	}

	public EdiField getCompCep() {
		return compCep;
	}

	public EdiField getCidade() {
		return cidade;
	}

	public EdiField getEstado() {
		return estado;
	}

	public EdiField getNomeSacador() {
		return nomeSacador;
	}

	public EdiField getNumSequencia() {
		return numSequencia;
	}

	public EdiField getCodRegistro() {
		return codRegistro;
	}

	public EdiField getConstZeros1() {
		return constZeros1;
	}

	public EdiField getConstZeros2() {
		return constZeros2;
	}

	public EdiField getConstBrancos1() {
		return constBrancos1;
	}

	public EdiField getConstZeros3() {
		return constZeros3;
	}

	public EdiField getConstBrancos2() {
		return constBrancos2;
	}

	public EdiField getConstZeros4() {
		return constZeros4;
	}

	public EdiField getConstBrancos3() {
		return constBrancos3;
	}

	public EdiField getConstZeros5() {
		return constZeros5;
	}

	public EdiField getUsoBanco1() {
		return usoBanco1;
	}

	public EdiField getIdBanco() {
		return idBanco;
	}

	public EdiField getConstZeros6() {
		return constZeros6;
	}

	public EdiField getConstBrancos4() {
		return constBrancos4;
	}

	public EdiField getConstZeros7() {
		return constZeros7;
	}

	public EdiField getMoeda() {
		return moeda;
	}

	public EdiField getUsoBanco2() {
		return usoBanco2;
	}

}
