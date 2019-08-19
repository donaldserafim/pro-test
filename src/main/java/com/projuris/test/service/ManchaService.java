package com.projuris.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projuris.test.domain.Mancha;
import com.projuris.test.domain.Posicao;
import com.projuris.test.domain.Resultado;


@Service
public interface ManchaService{

	public static final char FECHAMENTO_LINHA = ']';
	public static final char SEPARADOR_LINHA = ',';
	public static final char ABERTURA_LINHA = '[';
	public static final char RUGOSO = '1';
	public static final char NAO_RUGOSO = '0';

	public Resultado processa(String area);

	public List<Posicao> getPosicoesRugosas(String area);
	public List<Mancha> getManchas(List<Posicao> listaPosicoesRugosas);
	public Integer getTotalArea(List<Posicao> lista);
	public Double getspotsAverageArea(Integer totalArea, Integer numberOfSpots);
	public Integer biggestSpotArea(List<Mancha> listaManchas);
	public boolean verificaSePosicaoEstaSendoUsada(Posicao posicao, List<Mancha> listaManchas);
	public List<Posicao> verificarIrmas(Posicao posicao, List<Posicao> listPosicao);

}
