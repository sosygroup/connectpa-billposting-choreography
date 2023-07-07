package it.univaq.connectpa.publicbillposting.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.univaq.connectpa.publicbillposting.business.EventService;
import it.univaq.connectpa.publicbillposting.business.InstanceService;

@Controller
public class MonitorController {

	@Value("${spring.application.name}")
    String appName;

	@Autowired
	private EventService eventService;

	@Autowired
	private InstanceService instanceService;

    @GetMapping("/")
    public String getHomepage(Model model) {

    	model.addAttribute("requireServiceResult", eventService.requireEventsFromServices());
    	model.addAttribute("instancesCount", eventService.countInstances());

    	return "index";
    }

    @GetMapping("/instances")
    public String getInstancesPage(Model model) {

    	model.addAttribute("instances", instanceService.getInstances());

    	return "instances";
    }

    @GetMapping("/instances/{instanceId}")
    public String getInstanceDetailPage(Model model, @PathVariable("instanceId") String instanceId) {

    	model.addAttribute("instance", instanceService.getInstanceDetails(instanceId));

    	return "instance-detail";
    }
}
