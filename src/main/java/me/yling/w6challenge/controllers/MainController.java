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

    //Homepage, for user to input personal info
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

    //show list of persons
    @RequestMapping("/showper")
    public String showPerson (Model model)
    {
        model.addAttribute("persons", personRepo.findAll());
        return "showper";
    }

    //add education to specific person according person's id
    @GetMapping("/addedu/{id}")
    public String addEdutoPer (@PathVariable("id") long id, Model model)
    {
//        Person findper = personRepo.findOne(id);
//        model.addAttribute("findper", findper);

        Education oneedu = new Education();
        oneedu.setPerson(personRepo.findOne(id));
        model.addAttribute("addedumessage", "Add your education here");
        model.addAttribute("newEdu", oneedu);

        System.out.println("id:"+id);
//        System.out.println(findper.getId());
        System.out.println("person.findOne.getID:"+personRepo.findOne(id).getId());

        return "addedutoper";
    }

    @PostMapping("/addedutoper")
    public String postEdutoPer (@ModelAttribute ("newEdu") Education oneedu) //@ModelAttribute ("person") Person findper)
    {
        Person findper = personRepo.findOne(oneedu.getPerson().getId());
        educationRepo.save(oneedu);

        System.out.println("############findper.getID: "+findper.getId());
        System.out.println("--------oneedu.getDegree:"+oneedu.getDegree());

        return "resultedu";
    }

    //show education list of specific person, according to person's id
    @RequestMapping("/listedu/{id}")
    public String listEducationofOne (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("persons",personRepo.findOne(id));
        return"listedu";
    }

    //update person's info
    @RequestMapping("/updateperson/{id}")
    public String updatePerson (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("newPerson", personRepo.findOne(id));
        return "addperson";
    }

    //delete person
    @RequestMapping("/deleteperson/{id}")
    public String delPerson(@PathVariable("id") long id, Model model)
    {
        personRepo.delete(id);
        return "redirect:/showper";
    }

    //update specific person's education info
    @RequestMapping("/updateedu/{id}")
    public String updateEdu (@PathVariable("id") long id, Model model)
    {
        // Person = personRepo.findOne(educationID);
//        Education updateedu = educationRepo.findOne(educationID);
//        Person theperson = updateedu.getPerson();
//        model.addAttribute("newEdu", updateedu);
//        model.addAttribute("theperson",theperson);
//
//        System.out.println("------educationID:"+educationID);
//        System.out.println("------getPerson:"+updateedu.getPerson());

        model.addAttribute("newEdu", educationRepo.findOne(id));
        return "addedutoper";
    }

    @RequestMapping("/deleteedu/{id}")
    public String delEdu(@PathVariable("id") long id, Model model)
    {
        Education eduTodel = educationRepo.findOne(id);
        Person perWithedu = eduTodel.getPerson();
        perWithedu.delEdu(eduTodel);
        eduTodel.setPerson(null);

        System.out.println("******Deleting:"+eduTodel);
        educationRepo.delete(id);
        return "redirect:/listedu/{id}";
    }

}
