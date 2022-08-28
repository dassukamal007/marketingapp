package com.marketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.dto.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;


@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	@RequestMapping("/createLead")
	public String viewCreateLeadPage() {
		return "create_lead";
	}
	
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute Lead lead, ModelMap model ) {
		leadService.saveLead(lead);
		model.addAttribute("msg", "record saved");
		return "create_lead";
	}
	
	
	@RequestMapping("/listall")
	public String listAllLeads(ModelMap model) {
		List<Lead> leads = leadService.listLeads();
		model.addAttribute("leads", leads);
		
		return "lead_search_result";
	}
	
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") long id,ModelMap model) {
		
		leadService.deleteLeadById(id);
		
		List<Lead> leads = leadService.listLeads();
		model.addAttribute("leads", leads);
		
		return "lead_search_result";
	}
	
	@RequestMapping("/update")
	public String updateOneId(@RequestParam("id") long id, ModelMap model) {
		Lead lead = leadService.getOneLead(id);
		model.addAttribute("lead", lead);
		
		return "update_lead";
		
	}
	
	@RequestMapping("/updateLead")
	public String updateOneLeadData(LeadData data, ModelMap model) {
		Lead lead = new Lead();
		lead.setId(data.getId());
		lead.setFirstName(data.getFirstName());
		lead.setLastName(data.getLastName());
		lead.setEmail(data.getEmail());
		lead.setMobile(data.getMobile());
		
		leadService.saveLead(lead);
		
		List<Lead> leads = leadService.listLeads();
		model.addAttribute("leads", leads);
		
		return "lead_search_result";
		
	}

}
