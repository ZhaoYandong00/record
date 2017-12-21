package com.zl.record.service;

import java.util.List;

import com.zl.record.model.Production;

public interface ProductionService {
	/**
	 * 条件查询
	 * 
	 * @param production
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Production> findBy(Production production, Integer offset, Integer limit);

	/**
	 * 查找全部
	 * 
	 * @return
	 */
	List<Production> findAll();

	/**
	 * 插入或更新
	 * 
	 * @param production
	 * @return
	 */
	boolean saveOrUpdate(Production production);

	/**
	 * 删除
	 * 
	 * @param production
	 * @return
	 */
	boolean delete(Production production);

	/**
	 * id查找
	 * 
	 * @param id
	 * @return
	 */
	Production findById(Long id);

	/**
	 * 查找自动完成列表
	 * 
	 * @param columnName
	 * @return
	 */
	List<String> findAutoList(String columnName);
}
