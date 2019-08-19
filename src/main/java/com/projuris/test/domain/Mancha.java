package com.projuris.test.domain;

import java.util.ArrayList;
import java.util.List;

public class Mancha {
	
	List<Posicao> posicoes;
	
	public Mancha() {
		this.posicoes = new ArrayList<Posicao>();
	}
	
	public void addPosicao(Posicao posicao) {
		if(!this.posicoes.contains(posicao)) {
			this.posicoes.add(posicao);			
		}
	}
	
	public void addPosicoes(List<Posicao> posicao) {
		for (Posicao posicao2 : posicao) {
			addPosicao(posicao2);
		}
	}


	public List<Posicao> getPosicoes() {
		return posicoes;
	}

	public void setPosicoes(List<Posicao> posicoes) {
		this.posicoes = posicoes;
	}
}
