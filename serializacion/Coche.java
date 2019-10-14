package cocheSerializar;

import java.io.Serializable;

public class Coche implements Serializable{

	private static final long serialVersionUID = 1L;
	private String marca;
	private int largo;
	private int ancho;
	private int potencia;
	private int puertas;
	private int motor;
	private boolean estado;
	private int velocidad;
	
	public Coche(String m, int l, int a, int p, int pu, int mo) {
		setMarca(m);
		setLargo(l);
		setAncho(a);
		setPotencia(p);
		setPuertas(pu);
		setMotor(mo);
	}
	public void cambiarEstado(boolean a) {
		this.estado = a;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getPuertas() {
		return puertas;
	}

	public void setPuertas(int puertas) {
		this.puertas = puertas;
	}

	public int getMotor() {
		return motor;
	}

	public void setMotor(int motor) {
		this.motor = motor;
	}

	public void acelerar(int cantidad) {
		if(this.estado) {
			if(this.velocidad + cantidad > 200) {
				this.velocidad = 200;
			}
		} else {
			System.out.println("Tienes que arrancar el coche primero");
		}
	}
	
	public void frenar(int cantidad) {
		if (this.estado) {
			if (this.velocidad - cantidad < 0) {
				this.velocidad = 0;
			}
		} 
	}
	
	public String toString() {
		String estado = this.estado ? "arrancado" : "parado";
		return this.marca + " mide " + this.largo + " x " + this.ancho + ", tiene " + this.potencia + " cv y esta " + estado;
	}
}
