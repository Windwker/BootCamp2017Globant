package com.javieralvarez.dao;

import java.util.List;

public interface ForecastAndConditionsDao<T> {
	public  void insert (T t);
	public  void update(T t);
	//public  int verifyBD(T t);
	public  List<T> select(String date, String country, String city);
}
