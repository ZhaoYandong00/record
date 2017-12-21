package com.zl.record.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.record.mapper.ProductionMapper;
import com.zl.record.model.Production;
import com.zl.record.service.ProductionService;

@Service
public class ProductionServiceImpl implements ProductionService {
	@Autowired
	private ProductionMapper productionMapper;
	@Override
	public List<Production> findBy(Production production, Integer offset, Integer limit) {
		RowBounds rowBounds = RowBounds.DEFAULT;
		if (offset != null && limit != null) {
			rowBounds = new RowBounds(offset, limit);
		}
		return productionMapper.selectListByProduction(production, rowBounds);
	}

	@Override
	public boolean saveOrUpdate(Production production) {
		if (productionMapper.selectProductionByProductNumber(production) == null) {
			if (production.getId() == null) {
				return productionMapper.insert(production) == 1;
			} else {
				return productionMapper.update(production) == 1;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Production production) {
		return productionMapper.delete(production) == 1;
	}

	@Override
	public Production findById(Long id) {	   
		return productionMapper.selectById(id);
	}

	@Override
	public List<Production> findAll() {
		return productionMapper.selectAll();
	}

	@Override
	public List<String> findAutoList(String columnName) {
		return productionMapper.selectListByColumnName(columnName);
	}

}
