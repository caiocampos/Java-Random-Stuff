package br.ufu.facom.tesi.Protocol;

import java.io.Serializable;

public class ProtocolData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private byte[] bytes;
    private boolean finished;
    private String message;
    private byte[] ver;

    public ProtocolData() {
	finished = false;
    }

    public ProtocolData(byte[] bytes) {
	super();
	finished = false;
	this.bytes = bytes;
    }

    public ProtocolData(String nome) {
	super();
	finished = false;
	this.nome = nome;
    }

    public ProtocolData(String nome, byte[] bytes) {
	super();
	finished = false;
	this.nome = nome;
	this.bytes = bytes;
    }

    @Override
    public String toString() {
	return new String(bytes);
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public byte[] getVer() {
	return ver;
    }

    public void setVer(byte[] ver) {
	this.ver = ver;
    }

    public byte[] getBytes() {
	return bytes;
    }

    public void setBytes(byte[] bytes) {
	this.bytes = bytes;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public boolean isFinished() {
	return finished;
    }

    public void setFinished(boolean finished) {
	this.finished = finished;
    }
}
