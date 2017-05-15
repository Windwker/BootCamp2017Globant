package com.javieralvarez.dao;

import com.javieralvarez.clases.Conditions;

public interface DaoConditions {
	public void insertConditions(Conditions con);
	public void updateConditions(Conditions con);
	public int verifyConditions(Conditions con);
	public void selectConditions(Conditions con);
}
