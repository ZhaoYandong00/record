package com.zl.record.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping("/serch")
	public ModelAndView productions(Production production, Integer offset, Integer limit) {
		ModelAndView mv = new ModelAndView("record_serch");
		List<Production> list = productionService.findBy(production, offset, limit);
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
			if (production == null)
				mv.setViewName("redirect:/record");
		}
		mv.addObject("production", production);
		return mv;
	}
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView save(Production production) {
		ModelAndView mv = new ModelAndView();
		if (productionService.saveOrUpdate(production))
			mv.setViewName("redirect:/record");
		else {
			mv.setViewName("record_detail");
			mv.addObject("msg", "检查是否有生产编号重复");
			mv.addObject("production", production);
		}
		return mv;
	}
}
