package com.example.demo.controller;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Restcontroller sends the response in json or xml format so eliminating the use of @ResponseBody
@RestController
public class AlienController {
    @Autowired
    private AlienRepo alienRepo;

    @RequestMapping("/")
    public String home(){
        return "home.jsp";
    }

//    @RequestMapping("/addAlien")
//    public String addAlien(Alien alien){
//        alienRepo.save(alien);
//        return "home.jsp";
//    }

//    @RequestMapping("/getAlien")
//    public ModelAndView addAlien(@RequestParam int aid){
//        ModelAndView mv = new ModelAndView("showAlien.jsp");
//        Alien alien = alienRepo.findById(aid).orElse(new Alien());
//        mv.addObject(alien);
//
//        System.out.println(alienRepo.findByTech("php"));
//        System.out.println(alienRepo.findByAidGreaterThan(101));
//        System.out.println(alienRepo.findByTechSorted("java"));
//        return mv;
//    }

    //Restricting the result of this method to only have xml values
//    @RequestMapping(path="/aliens", produces = "application/xml")
    @GetMapping("/aliens")
    @ResponseBody
    public List<Alien> getAliens(){
        return alienRepo.findAll();
    }

    @GetMapping("/alien/{aid}")
    @ResponseBody
    public Optional<Alien> getAlien(@PathVariable("aid") int aid){
        return alienRepo.findById(aid);
    }

    @PostMapping("/alien")
    public Alien addAlien(@RequestBody Alien alien){ //@RequestBody is used when we're sending raw data in the body
        alienRepo.save(alien);
        return alien;
    }

    @DeleteMapping("/alien/{aid}")
    public String removeAlien(@PathVariable("aid") int aid){
        Alien alien = alienRepo.getReferenceById(aid);
        alienRepo.delete(alien);
        return "deleted";
    }

    @PutMapping(path="/alien", consumes = {"application/json"})
    public Alien saveOrUpdateAlien(@RequestBody Alien alien){
        alienRepo.save(alien);
        return alien;
    }



}
