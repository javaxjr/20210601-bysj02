package com.tjetc.demo.集合;

/**
 * Title:匹配方式，枚举类型<br>
 * 
 * Description:定义的枚举类型的匹配方式。共有EQ(匹配), NE(不匹配), IEQ(忽略大小写匹配), LIKE(like),
 * LLIKE(left like), RLIKE(right like), ILIKE(ignore case like), LT(less than),
 * GT(greater then), LE(小于等于), GE(大于等于), BETWEEN, IN, NOT_IN, HAS等十五种匹配方式<br>
 * 
 * Company: 亚信联创集团股份有限公司<br>
 * 
 * @author author
 * @see
 * @CreateDate 2011-8-26 下午05:12:02
 * 
 */
public enum MatchType {
	/** equals */
	EQ,
	/** not equals */
	NE,
	/** ignore case equals */
	IEQ,
	/** like */
	LIKE,
	/** left like */
	LLIKE,
	/** right like */
	RLIKE,
	/** ignore case like */
	ILIKE,
	/** less than */
	LT,
	/** greater than */
	GT,
	/** less than or equals */
	LE,
	/** greater than or equals */
	GE,
	/** between */
	BETWEEN,
	/** in */
	IN,
	/** not in */
	NOT_IN,
	/** eqProperty */
	EQ_PROPERTY,
	
	SIZE_EQ,
	
	SIZE_GE,
	
	SIZE_LE,
	
	SIZE_GT,
	
	SIZE_LT,
	
	DATE_STRING_EQ, DATE_STRING_LIKE,
	
	DATE_PART_EQ, DATE_PART_LE, DATE_PART_LT, DATE_PART_GE,	DATE_PART_GT,
	
	/** exists */
	EXISTS,
	/** ex */
	EX,
	
	/** not */
	NOT;
}
