package com.javieralvarez.dao;

import org.springframework.stereotype.Component;


public interface ForecastAndConditionsDao<T> {
	public  void insert (T t);
	public  void update(T t);
	public  int verifyBD(T t);
	public  void select(T t);
}