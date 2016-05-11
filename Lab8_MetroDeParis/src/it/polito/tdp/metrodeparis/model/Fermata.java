package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.List;

public class Fermata {
	
	private int id_fermata;
	private String nome;
	private double coordX;
	private double coordY;
	private List<FermataSuLinea> fermateSuLinea;
	
	public Fermata(int id_fermata, String nome, double coordX, double coordY) {
		super();
		this.id_fermata = id_fermata;
		this.nome = nome;
		this.coordX = coordX;
		this.coordY = coordY;
		this.fermateSuLinea = new ArrayList<FermataSuLinea>();
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

	public List<FermataSuLinea> getFermateSuLinea() {
		return this.fermateSuLinea;
	}

	public void addFermataSuLinea(FermataSuLinea fermataSuLinea) {
		this.fermateSuLinea.add(fermataSuLinea);
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public int hashCode() {
		return ((Integer) id_fermata).hashCode();
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
