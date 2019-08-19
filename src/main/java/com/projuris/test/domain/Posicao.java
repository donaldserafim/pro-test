package com.projuris.test.domain;

public class Posicao {

	int linha;
	int coluna;
	
	public Posicao(int row, int col) {
		this.coluna = col;
		this.linha = row;
	}
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	@Override
	public String toString() {
		return "Posicao [linha=" + linha + ", coluna=" + coluna + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coluna;
		result = prime * result + linha;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		Posicao posicao = (Posicao) obj;
		
		if(this.getColuna() ==  posicao.getColuna() &&
				this.getLinha() == posicao.getLinha()) {
			return true;
		}
		
		return false;
	}
	
	public boolean verificaIrmas(Object obj) {
		Posicao posicao = (Posicao) obj;
		
		if((this.getLinha()+1 == posicao.getLinha()) 
				&& this.getColuna() == posicao.getColuna()){
			return true;
		}

		if((this.getLinha()-1 == posicao.getLinha()) 
				&& this.getColuna() == posicao.getColuna()){

			return true;
		}

		if((this.getLinha() == posicao.getLinha()) 
				&& this.getColuna()+1 == posicao.getColuna()){

			return true;
		}

		if((this.getLinha() == posicao.getLinha()) 
				&& this.getColuna()-1 == posicao.getColuna()){

			return true;
		}
		return false;
	}
}