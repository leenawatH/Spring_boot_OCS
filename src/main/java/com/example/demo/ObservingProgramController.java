package com.example.demo;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.ObservingProgram;

@Controller
public class ObservingProgramController {
    OCS ocs = new OCS(true);

    // @GetMapping("/all")
    // public @ResponseBody ObservingProgram[] getAllSciencePlan() {
    //     return ocs.getObservingPrograms();
    // }
    @CrossOrigin
    @GetMapping("createObservingProgram")
    public String showObservingProgramForm(Model model) {
        model.addAttribute("createObservingProgram", new ObservingProgram());
        return "createObservingProgram";
    }

    @CrossOrigin
    @PostMapping(value = "sentsciplan/createObservingProgram/create/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public @ResponseBody String createOP(@RequestParam Map<String, Object> body , @PathVariable Integer id) {
        Observing op = new Observing();
        ObservingProgram newop = op.createObservingProgram(body , id);
        ocs.saveObservingProgram(newop);
        if(ocs.saveObservingProgram(newop)){
            return "The validation status is " + newop.isValidationStatus() + " ==> Can't save ObservingProgram.";
        }
        return "success";
    }
}


