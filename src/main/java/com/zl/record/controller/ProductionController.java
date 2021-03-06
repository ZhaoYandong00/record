package com.zl.record.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zl.record.model.Production;
import com.zl.record.service.ProductionService;

@Controller
@RequestMapping("/record")
public class ProductionController {
	@Autowired
	private ProductionService productionService;

	@RequestMapping
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("record");
		List<Production> list = productionService.findAll();
		mv.addObject("productions", list);
		return mv;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("record_search");
		Production production = new Production();
		mv.addObject("production", production);
		setAutoList(mv);
		return mv;
	}

	/**
	 * 设置自动填充列表
	 * 
	 * @param mv
	 */
	private void setAutoList(ModelAndView mv) {
		List<String> attrList = new ArrayList<String>();
		attrList.add("name");
		attrList.add("specification");
		attrList.add("product_number");
		attrList.add("producer");
		attrList.add("inspector");
		attrList.add("salesman");
		attrList.add("purchaser");
		Map<String, List<String>> map = new HashMap<>();
		for (String columnName : attrList) {
			List<String> autoList = productionService.findAutoList(columnName);
			columnName = underlineToCamehump(columnName);
			map.put(columnName, autoList);
		}
		mv.addObject("autoListMap", map);
	}

	/**
	 * 将下划线替换为驼峰风格
	 * 
	 * @param inputString
	 * @return
	 */
	private String underlineToCamehump(String inputString) {
		StringBuilder sb = new StringBuilder();
		boolean nextUpperCase = false;
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);
			if (c == '_') {
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
			} else {
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else
					sb.append(Character.toLowerCase(c));
			}
		}
		return sb.toString();
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView productions(Production production, Integer offset, Integer limit) {
		ModelAndView mv = new ModelAndView("record_search");
		List<Production> list = productionService.findBy(production, offset, limit);
		if (list == null || list.isEmpty()) {
			mv.addObject("msg", "未找到数据,请重新查找");
		}
		mv.addObject("production", production);
		mv.addObject("productions", list);
		return mv;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView production(Long id) {
		ModelAndView mv = new ModelAndView("record_detail");
		Production production;
		if (id == null)
			production = new Production();
		else {
			production = productionService.findById(id);
			if (production == null) {
				mv.setViewName("redirect:/record");
				mv.addObject("msg", "选择错误");
			}
		}
		mv.addObject("production", production);
		setAutoList(mv);
		return mv;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView save(Production production) {
		ModelAndView mv = new ModelAndView();
		boolean nextStep = true;
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		String regex1 = "\\s*";
		try {
			if (production.getName() == null || production.getName().matches(regex1)) {
				mv.addObject("msg1", "产品名字不能为空");
				nextStep = false;
			}
			if (production.getSpecification() == null || production.getSpecification().matches(regex1)) {
				mv.addObject("msg1", "型号规格不能为空");
				nextStep = false;
			}
			if (production.getProductNumber() == null || production.getProductNumber().matches(regex1)) {
				mv.addObject("msg1", "产品编号不能为空");
				nextStep = false;
			}
			if (production.getProducer() == null || production.getProducer().matches(regex1)) {
				mv.addObject("msg1", "生产人员不能为空");
				nextStep = false;
			}
			if (production.getInspector() == null || production.getInspector().matches(regex1)) {
				mv.addObject("msg1", "检验人员不能为空");
				nextStep = false;
			}
			if (production.getProductionDate() == null || production.getProductionDate().matches(regex1)) {
				mv.addObject("msg1", "生产时间不能为空");
				nextStep = false;
			} else if (!production.getProductionDate().matches(regex)) {
				mv.addObject("msg1", "生产时间格式不对");
				nextStep = false;
			}
			if (production.getSaleDate() != null && !production.getSaleDate().matches(regex1)
					&& !production.getSaleDate().matches(regex)) {
				mv.addObject("msg1", "销售时间格式不对");
				nextStep = false;
			}
			if (nextStep) {
				if (productionService.saveOrUpdate(production))
					mv.setViewName("/record_search");
				else {
					mv.setViewName("record_detail");
					mv.addObject("msg", "检查是否有生产编号重复");
					mv.addObject("production", production);
				}
			} else {
				mv.setViewName("record_detail");
				mv.addObject("production", production);
			}
		} catch (Exception e) {
			mv.setViewName("record_detail");
			mv.addObject("production", production);
			mv.addObject("msg", e.getMessage());
		}
		return mv;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap delete(@RequestParam Long id) {
		ModelMap modelMap = new ModelMap();
		Production production = new Production();
		try {
			production = productionService.findById(id);
			if (production == null) {
				modelMap.put("msg", "未找到目标，无法删除");
				modelMap.put("success", false);
			} else {
				boolean success = productionService.delete(production);
				modelMap.put("success", success);
			}
		} catch (Exception e) {
			modelMap.put("msg", e.getMessage());
			modelMap.put("success", false);
		}
		return modelMap;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addProductionFromClient(Production production) {
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		String regex1 = "\\s*";
		try {
			if (production.getName() == null || production.getName().matches(regex1))
				return "产品名字不能为空";
			if (production.getSpecification() == null || production.getSpecification().matches(regex1))
				return "型号规格不能为空";
			if (production.getProductNumber() == null || production.getProductNumber().matches(regex1))
				return "产品编号不能为空";
			if (production.getProducer() == null || production.getProducer().matches(regex1))
				return "生产人员不能为空";
			if (production.getInspector() == null || production.getInspector().matches(regex1))
				return "检验人员不能为空";
			if (production.getProductionDate() == null || production.getProductionDate().matches(regex1))
				return "生产时间不能为空";
			else if (!production.getProductionDate().matches(regex))
				return "生产时间格式不对";
			if (production.getSaleDate() != null && !production.getSaleDate().matches(regex1)
					&& !production.getSaleDate().matches(regex))
				return "销售时间格式不对";
			if (productionService.saveOrUpdate(production))
				return "成功";
			else 
				return "检查是否有生产编号重复";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
