package com.zl.record.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zl.record.model.Production;

@Mapper
public interface ProductionMapper {
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<Production> selectAll();

	/**
	 * 根据id查找
	 * 
	 * @param id
	 * @return
	 */
	Production selectById(Long id);

	/**
	 * 根据内容模糊查找
	 * 
	 * @param production
	 *            产品
	 * @return
	 */
	List<Production> selectListByProduction(Production production, RowBounds rowBounds);

	/**
	 * 根据列名查找列内内容
	 * 
	 * @param columnName
	 *            列名
	 * @return
	 */
	List<String> selectListByColumnName(@Param(value = "columnName") String columnName);

	/**
	 * 根据生产编号精准查询
	 * 
	 * @param production
	 * @return
	 */
	Production selectProductionByProductNumber(Production production);

	/**
	 * 插入
	 * 
	 * @param production
	 * @return
	 */
	int insert(Production production);

	/**
	 * 更新
	 * 
	 * @param production
	 * @return
	 */
	int update(Production production);

	/**
	 * 删除
	 * 
	 * @param production
	 * @return
	 */
	int delete(Production production);
}
