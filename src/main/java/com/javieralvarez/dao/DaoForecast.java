package com.javieralvarez.dao;

import com.javieralvarez.clases.Forecast;

public interface DaoForecast {
	public void insertForecast(Forecast fc);
	public void updateForecast(Forecast fc);
	public void selectForecast(Forecast fc);
	public int verifyForecast(Forecast fc);
}
