package com.javieralvarez.clases;

import com.javieralvarez.dao.DaoConditions;
import com.javieralvarez.dao.DaoConditionsIMP;
import com.javieralvarez.dao.DaoForecast;
import com.javieralvarez.dao.DaoForecastIMP;

public class WeatherGlobant{
	
 public static void main(String[] args) {
	
	Conditions cc = new Conditions();
	Forecast fc = new Forecast();
	cc.setCurrentConditions();  // CARGA LAS CONDICIONES ACTUALES.
	fc.setForecastConditions(); // CARGA EL FORECAST.
	DaoConditions dao = new DaoConditionsIMP();
	DaoForecast daof = new DaoForecastIMP();
	
	if(dao.verifyConditions(cc)==1){     /// VERIFICA SI EL DIA YA HA SIDO CARGADO, HACE UPDATE O INSERT SEGUN CORRESPONDA.
		dao.updateConditions(cc);
	}else{
		dao.insertConditions(cc);	
	}
	
	
	if(daof.verifyForecast(fc)==1){		/// VERIFICA SI YA SE HA CARGADO UN FORECAST PARA EL DIA, HACE UPDATE O INSERT SEGUN CORRESPONDA.
		daof.updateForecast(fc);
	}else{
		daof.insertForecast(fc);
	}
	
	dao.selectConditions(cc);       /// MUESTRA POR PANTALLA LAS CONDICIONES ACTUALES.
	daof.selectForecast(fc);		/// MUESTRA POR PANTALLA EL FORECAST PARA EL DIA.
	
		

}
	
	
}




