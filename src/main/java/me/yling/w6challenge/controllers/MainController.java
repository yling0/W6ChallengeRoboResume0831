package me.yling.w6challenge.controllers;

import me.yling.w6challenge.models.Education;
import me.yling.w6challenge.models.Person;
import me.yling.w6challenge.repositories.EducationRepo;
import me.yling.w6challenge.repositories.ExperienceRepo;
import me.yling.w6challenge.repositories.PersonRepo;
import me.yling.w6challenge.repositories.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.PercentStyleFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    PersonRepo personRepo;

    @Autowired
    EducationRepo educationRepo;

    @Autowired
    ExperienceRepo experienceRepo;

    @Autowired
    SkillRepo skillRepo;

    //for user to input personal info
    @GetMapping("/")
    public String addPerson (Model model)
    {
        model.addAttribute("newPerson", new Person());
        model.addAttribute("addpersonmessage", "Add person here");
        return "addperson";
    }

    @PostMapping("/addperson")
    public String postPerson(@ModelAttribute ("newPerson") Person person)
    {
        personRepo.save(person);
        return "resultper";
    }

    @RequestMapping("/showper")
    public String showPerson (Model model)
    {
        model.addAttribute("persons", personRepo.findAll());
        return "showper";
    }

    @RequestMapping("/addedu/{id}")
    public String addEducation (@PathVariable("id") long id, Model model)
    {
        Person findper = personRepo.findOne(id);
        model.addAttribute("findper", findper);

        Education oneedu = new Education();
        oneedu.setPerson(findper);
        model.addAttribute("newEdu", oneedu);

        return "addedutoper";
    }

    @PostMapping("/addedutoper")
    public @ResponseBody String postEdutoPer (@ModelAttribute ("newEdu") Education oneedu, @ModelAttribute ("person") Person findper)
    {
        educationRepo.save(oneedu);
        return "Education added.";
    }

    @RequestMapping("/listedu")
    public String listEducation (Model model)
    {
        model.addAttribute("persons", personRepo.findAll());
        return "listedu";
    }

    @RequestMapping("/listedu/{id}")
    public String listEducationofOne (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("persons",personRepo.findOne(id));
        return"listedu";
    }







}
