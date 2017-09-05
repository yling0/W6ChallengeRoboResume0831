package me.yling.w6challenge.controllers;

import me.yling.w6challenge.models.Education;
import me.yling.w6challenge.models.Experience;
import me.yling.w6challenge.models.Person;
import me.yling.w6challenge.models.Skill;
import me.yling.w6challenge.repositories.EducationRepo;
import me.yling.w6challenge.repositories.ExperienceRepo;
import me.yling.w6challenge.repositories.PersonRepo;
import me.yling.w6challenge.repositories.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String showWelcome()
    {
        return "welcome";
    }
//
//    @RequestMapping("/login")
//    public String login()
//    {
//        return "login";
//    }


    @GetMapping("/addperson")
    public String addPerson (Model model)
    {
        model.addAttribute("newPerson", new Person());
        model.addAttribute("addpersonmessage", "Add person here");
        return "addperson";
    }



    @PostMapping("/addperson")
    public String postPerson(@Valid @ModelAttribute ("newPerson") Person person, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "addperson";
        }
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

    //show resume of specific person, according to person's id
    @RequestMapping("/listresume/{id}")
    public String listEducationofOne (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("persons",personRepo.findOne(id));
        return"listresume";
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
    public String postEdutoPer (@Valid @ModelAttribute ("newEdu") Education oneedu, BindingResult result) //@ModelAttribute ("person") Person findper)
    {
        if (result.hasErrors())
        {
            return "/addedutoper";
        }
        Person findper = personRepo.findOne(oneedu.getPerson().getId());
        educationRepo.save(oneedu);

        System.out.println("############findper.getID: "+findper.getId());
        System.out.println("--------oneedu.getDegree:"+oneedu.getDegree());

        return "resultedu";
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
    public String delEdu(@PathVariable("id") long id)
    {
        Education eduTodel = educationRepo.findOne(id);
        Person perWithedu = eduTodel.getPerson();
        perWithedu.delEdu(eduTodel);
        eduTodel.setPerson(null);

        System.out.println("******Deleting:"+eduTodel);
        System.out.println("-------Education ID:-------: "+id);
        educationRepo.delete(id);
//        model.addAttribute(personRepo.findOne(perId));

        return "redirect:/";
    }

    //add experience to specific person according person's id
    @GetMapping("/addexp/{id}")
    public String addExptoPer (@PathVariable("id") long id, Model model)
    {
        Experience oneexp = new Experience();
        oneexp.setPerson(personRepo.findOne(id));
        model.addAttribute("addexpmessage", "Add your working experience here");
        model.addAttribute("newExp", oneexp);

        return "addexptoper";
    }

    @PostMapping("/addexptoper")
    public String postExptoPer (@Valid @ModelAttribute ("newExp") Experience oneexp, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "addexptoper";
        }
        Person findper = personRepo.findOne(oneexp.getPerson().getId());
        experienceRepo.save(oneexp);

        System.out.println("############findper.getID: "+findper.getId());
        System.out.println("--------oneedu.getDegree:"+oneexp.getCompany());

        return "resultexp";
    }


    //update specific person's experience info
    @RequestMapping("/updateexp/{id}")
    public String updateExp (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("newExp", experienceRepo.findOne(id));
        return "addexptoper";
    }

    @RequestMapping("/deleteexp/{id}")
    public String delExp(@PathVariable("id") long id)
    {
        Experience expTodel = experienceRepo.findOne(id);
        Person perWithexp = expTodel.getPerson();
        perWithexp.delExp(expTodel);
        expTodel.setPerson(null);

        System.out.println("******Deleting:"+expTodel);
        System.out.println("-------Experience ID:-------: "+id);
        experienceRepo.delete(id);
//        model.addAttribute(personRepo.findOne(perId));

        return "redirect:/";
    }

    //add skills to specific person according person's id
    @GetMapping("/addski/{id}")
    public String addSkitoPer (@PathVariable("id") long id, Model model)
    {
        Skill oneski = new Skill();
        oneski.setPerson(personRepo.findOne(id));
        model.addAttribute("addskimessage", "Add your skills here");
        model.addAttribute("newSki", oneski);

        return "addskitoper";
    }

    @PostMapping("/addskitoper")
    public String postSkitoPer (@Valid @ModelAttribute ("newSki") Skill oneski, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "addskitoper";
        }
        Person findper = personRepo.findOne(oneski.getPerson().getId());
        skillRepo.save(oneski);

        System.out.println("############findper.getID: "+findper.getId());
        System.out.println("--------oneedu.getDegree:"+oneski.getSkiName());

        return "resultski";
    }

    //update specific person's skill info
    @RequestMapping("/updateski/{id}")
    public String updateSki (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("newSki", skillRepo.findOne(id));
        return "addskitoper";
    }

    @RequestMapping("/deleteski/{id}")
    public String delSki(@PathVariable("id") long id)
    {
        Skill skiTodel = skillRepo.findOne(id);
        Person perWithski = skiTodel.getPerson();
        perWithski.delSki(skiTodel);
        skiTodel.setPerson(null);

        System.out.println("******Deleting:"+skiTodel);
        System.out.println("-------Experience ID:-------: "+id);
        experienceRepo.delete(id);
//        model.addAttribute(personRepo.findOne(perId));

        return "redirect:/";
    }



}
