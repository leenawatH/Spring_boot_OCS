package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.example.MySciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import edu.gemini.app.ocs.model.*;
import org.springframework.ui.Model;

@Controller
public class SciencePlanController {

    OCS ocs = new OCS(true);

    @GetMapping("/all") 
    public @ResponseBody 
    Iterable<SciencePlan> getAllSciplan() { 
        return ocs.getAllSciencePlans(); 
    } 

    @GetMapping("dashboard") 
    public String displayAllSciplan(Model model) { 
        
        ArrayList<SciencePlan> n =  ocs.getAllSciencePlans();
        ArrayList<Boolean> check  = new ArrayList<Boolean>();
        model.addAttribute("sciplan", ocs.getAllSciencePlans());
        for (SciencePlan a : n){
            if(ocs.getObservingProgramBySciencePlan(a).getGeminiLocation() == null){
                check.add(false);
            }else{
                check.add(true);
            }
           
        }
        model.addAttribute("checkob", check);  

        return "Dashboard"; 
    }         
    
    @GetMapping("/sentsciplan/{id}")
    public String createSciplan(@PathVariable("id") Integer id ,Model model) { 

        model.addAttribute("createObservingProgram", ocs.getSciencePlanByNo(id));
        model.addAttribute("datarequirement", ocs.getSciencePlanByNo(id).getDataProcRequirements());
        return "createObservingProgram" ;
    } 


    // Test add Science Plan
    // @GetMapping("/add")
    // public String addSciplan() { 
    //     MySciencePlan mySP = new MySciencePlan();
    //     mySP.setCreator("Morakot Choetkiertikul");
    //     mySP.setSubmitter("Chaiyong Ragkhitwetsagul");
    //     mySP.setFundingInUSD(1000);
    //     mySP.setObjectives("To study the Auriga star system.");
    //     mySP.setStarSystem(StarSystem.CONSTELLATIONS.Auriga);
    //     mySP.setStartDate("22/04/2021 23:00:00");
    //     mySP.setTelescopeLocation(SciencePlan.TELESCOPELOC.CHILE);
    //     mySP.setEndDate("23/04/2021 02:00:00");
    //     DataProcRequirement dpr1 =
    //             new DataProcRequirement("JPEG", "Low", "Color mode",
    //                     11, 10, 5, 0, 7, 0,
    //                     0, 0, 10, 8);
    //     mySP.setDataProcRequirements(dpr1);
    //     return ocs.createSciencePlan(mySP);
    // } 

    
}
