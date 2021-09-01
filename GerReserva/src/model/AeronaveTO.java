package model;

/**
 *
 * @author William
 */
public class AeronaveTO {

    private String codAeronave;
    private String fabricante;
    private String modelo;
    private AeronaveTO substituta;

    public String getCodAeronave() {
	return codAeronave;
    }

    public void setCodAeronave(String codAeronave) {
	this.codAeronave = codAeronave;
    }

    public String getFabricante() {
	return fabricante;
    }

    public void setFabricante(String fabricante) {
	this.fabricante = fabricante;
    }

    public String getModelo() {
	return modelo;
    }

    public void setModelo(String modelo) {
	this.modelo = modelo;
    }

    public AeronaveTO getSubstituta() {
	return substituta;
    }

    public void setSubstituta(AeronaveTO substituta) {
	this.substituta = substituta;
    }
    
    @Override
    public String toString(){
        return this.modelo;
    }
    
}
