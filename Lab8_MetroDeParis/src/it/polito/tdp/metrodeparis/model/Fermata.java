package it.polito.tdp.metrodeparis.model;

public class Fermata {
	
	private int id_fermata;
	private String nome;
	private double coordX;
	private double coordY;
	
	public Fermata(int id_fermata, String nome, double coordX, double coordY) {
		super();
		this.id_fermata = id_fermata;
		this.nome = nome;
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public Fermata(int id_fermata) {
		this.id_fermata = id_fermata;
	}

	public int getId_fermata() {
		return id_fermata;
	}

	public void setId_fermata(int id_fermata) {
		this.id_fermata = id_fermata;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_fermata;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fermata other = (Fermata) obj;
		if (id_fermata != other.id_fermata)
			return false;
		return true;
	}
		
	
	
}
