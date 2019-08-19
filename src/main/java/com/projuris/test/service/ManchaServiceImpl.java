package com.projuris.test.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projuris.exception.PadraoInvalidoException;
import com.projuris.test.domain.Mancha;
import com.projuris.test.domain.Posicao;
import com.projuris.test.domain.Resultado;


@Service
public class ManchaServiceImpl   implements ManchaService{

	public static final char FECHAMENTO_LINHA = ']';
	public static final char SEPARADOR_LINHA = ',';
	public static final char ABERTURA_LINHA = '[';
	public static final char RUGOSO = '1';
	public static final char NAO_RUGOSO = '0';

	public Resultado processa(String area) {
		
		Resultado resultado = new Resultado();
		
		List<Posicao> listaPosicoesRugosas = getPosicoesRugosas(area);
		List<Mancha> listaManchas = getManchas(listaPosicoesRugosas);
		
		resultado.setTotal_area(getTotalArea(listaPosicoesRugosas));
		resultado.setNumber_of_spots(listaManchas.size());
		resultado.setSpots_average_area(getspotsAverageArea(listaPosicoesRugosas.size(), listaManchas.size()));
		resultado.setBiggest_spot_area(biggestSpotArea(listaManchas));
		
		return resultado;
	}

	public List<Posicao> getPosicoesRugosas(String area){
		int coluna = 0;
		int linha = 0;
		int tamanhoColunaAtual = 0;
		List<Posicao> listaPosicoes = new ArrayList<Posicao>();
		
		for(int i = 0; i < area.length(); i++)
		{    
			if(area.charAt(i) == FECHAMENTO_LINHA) {
				if(linha == 0) {
					tamanhoColunaAtual = coluna;
				}
				
				if(tamanhoColunaAtual != coluna && area.charAt(i-1) != FECHAMENTO_LINHA) {
					throw new PadraoInvalidoException(PadraoInvalidoException.INVALID_MATRIX);	
				}
				
				linha++;
				tamanhoColunaAtual = coluna;
				coluna=0;
			}
			else if(area.charAt(i) == NAO_RUGOSO || area.charAt(i) == RUGOSO) {
				if(area.charAt(i) == RUGOSO) {
					listaPosicoes.add(new Posicao(linha,coluna));
				}
			    coluna++;
			}
			else if (area.charAt(i) != NAO_RUGOSO && 
					area.charAt(i) != RUGOSO &&
					area.charAt(i) != SEPARADOR_LINHA && 
					area.charAt(i) != FECHAMENTO_LINHA &&
					area.charAt(i) != ABERTURA_LINHA){
				throw new PadraoInvalidoException(PadraoInvalidoException.INVALID_MATRIX);
			}
		}		
		return listaPosicoes;
	}

	public List<Mancha> getManchas(List<Posicao> listaPosicoesRugosas){
		
		List<Mancha> listaManchas = new ArrayList<Mancha>();
		
		for (Posicao posicao : listaPosicoesRugosas) {

			//verifico se ela esta em alguma mancha já, se tiver passa pra proxima
			if(!listaManchas.isEmpty()) {
				if(verificaSePosicaoEstaSendoUsada(posicao,listaManchas)) {
					continue;
				}
			}

			//verifica as irmas
			List<Posicao> listPosicaoIrma = verificarIrmas(posicao,listaPosicoesRugosas);


			if(!listPosicaoIrma.isEmpty()) {

				List<Posicao> listPosicaoIrmaIrma = new ArrayList<Posicao>();
				Mancha mancha = new Mancha();
				mancha.addPosicoes(listPosicaoIrma);

				for (Posicao posicaoMancha : mancha.getPosicoes()) {
					listPosicaoIrmaIrma.addAll(verificarIrmas(posicaoMancha, listaPosicoesRugosas));
				}

				if(!listPosicaoIrmaIrma.isEmpty()) {
					mancha.addPosicoes(listPosicaoIrmaIrma);
				}

				listaManchas.add(mancha);

			}	
		}
		return listaManchas;
	}

	public Integer getTotalArea(List<Posicao> lista) {
		return lista.size();
	}

	public Double getspotsAverageArea(Integer totalArea, Integer numberOfSpots) {
		
		if(totalArea.compareTo(0) == 0 || totalArea.compareTo(0)==0) {
			return new Double(0.0);
		}
		
		Double resultado = new Double(totalArea)/new Double(numberOfSpots);
		
		BigDecimal bd = new BigDecimal(resultado).setScale(5, RoundingMode.DOWN);
		
		return bd.doubleValue();	
	}

	public Integer biggestSpotArea(List<Mancha> listaManchas) {
		Integer biggestSpotArea = 0;
		for (Mancha mancha : listaManchas) {
			if(mancha.getPosicoes().size()>biggestSpotArea) {
				biggestSpotArea = mancha.getPosicoes().size(); 
			}
		}
		return biggestSpotArea;
	}

	public boolean verificaSePosicaoEstaSendoUsada(Posicao posicao, List<Mancha> listaManchas) {
		for (Mancha mancha : listaManchas) {
			for (Posicao posicaoEE: mancha.getPosicoes()) {
				if(posicao.equals(posicaoEE)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Posicao> verificarIrmas(Posicao posicao, List<Posicao> listPosicao) {
		List<Posicao> listaPosicaoIrmas = new ArrayList<Posicao>();
		for (Posicao posicao2 : listPosicao) {
			if(posicao.verificaIrmas(posicao2)) {
				listaPosicaoIrmas.add(posicao2);
			}
		}
		
		listaPosicaoIrmas.add(posicao);

		return listaPosicaoIrmas;
	}
	
}
