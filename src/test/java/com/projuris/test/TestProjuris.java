package com.projuris.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.projuris.exception.PadraoInvalidoException;
import com.projuris.test.domain.Resultado;
import com.projuris.test.service.ManchaService;
import com.projuris.test.service.ManchaServiceImpl;

public class TestProjuris {


	@Test
	public void testeArea() {

		String area = "[[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]]";

		ManchaService manchaService = new ManchaServiceImpl();
		Resultado resultado = manchaService.processa(area);

		//total_area: 8
		Integer totalAreaExpected = new Integer(8);
		Integer numberOfSpotsExpected = new Integer(2);
		Double spotsAverageAreaExpected = new Double(4.0);
		Integer biggestSpotAreaExpected = new Integer(4);


		assertEquals(totalAreaExpected, resultado.getTotal_area());

		//number_of_spots
		assertEquals(numberOfSpotsExpected, resultado.getNumber_of_spots());

		//spots_average_area
		assertEquals(spotsAverageAreaExpected, resultado.getSpots_average_area());


		//biggest_spot_area
		assertEquals(biggestSpotAreaExpected, resultado.getBiggest_spot_area());

	}

	@Test
	public void teste2() {

		String area = "[[1,0,0,0],[0,0,1,0],[0,0,0,1],[0,0,1,1]]";

		ManchaService manchaService = new ManchaServiceImpl();
		Resultado resultado = manchaService.processa(area);


		//total_area: 8
		Integer totalAreaExpected = new Integer(5);
		Integer numberOfSpotsExpected = new Integer(3);
		Double spotsAverageAreaExpected = new Double(1.66666);
		Integer biggestSpotAreaExpected = new Integer(3);


		assertEquals(totalAreaExpected, resultado.getTotal_area());

		//number_of_spots
		assertEquals(numberOfSpotsExpected, resultado.getNumber_of_spots());

		//spots_average_area
		assertEquals(spotsAverageAreaExpected, resultado.getSpots_average_area());


		//biggest_spot_area
		assertEquals(biggestSpotAreaExpected, resultado.getBiggest_spot_area());

	}

	@Test
	public void teste3() {

		String area = "[[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]";

		ManchaService manchaService = new ManchaServiceImpl();
		Resultado resultado = manchaService.processa(area);

		//total_area: 8
		Integer totalAreaExpected = new Integer(0);
		Integer numberOfSpotsExpected = new Integer(0);
		Double spotsAverageAreaExpected = new Double(0.0);
		Integer biggestSpotAreaExpected = new Integer(0);

		assertEquals(totalAreaExpected, resultado.getTotal_area());

		//number_of_spots
		assertEquals(numberOfSpotsExpected, resultado.getNumber_of_spots());

		//spots_average_area
		assertEquals(spotsAverageAreaExpected, resultado.getSpots_average_area());

		//biggest_spot_area
		assertEquals(biggestSpotAreaExpected, resultado.getBiggest_spot_area());

	}
	
	@Test(expected = PadraoInvalidoException.class)
	public void testeError() {

		String area = "[[1,0,0,0],[0,0,1,0],[0,0,0,1],[0,0,1,1,1,0,1,1,0]]";

		ManchaService manchaService = new ManchaServiceImpl();
		manchaService.processa(area);
	}
	
	@Test(expected = PadraoInvalidoException.class)
	public void testeErrorPadrao() {

		String area = "‘[[“0”,0.0,false,0],[“1”,1.0,true,0],[0,0,0,0],[0,0,0,0]]’";

		ManchaService manchaService = new ManchaServiceImpl();
		manchaService.processa(area);
	}
}
