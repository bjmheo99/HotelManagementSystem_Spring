package com.projectHotel.PhanLam.controller;

import com.projectHotel.PhanLam.entity.*;
import com.projectHotel.PhanLam.repository.IAccount;
import com.projectHotel.PhanLam.repository.IAdministration;
import com.projectHotel.PhanLam.repository.IRoom;
import com.projectHotel.PhanLam.repository.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private  IRoom roomrepository;

    @Autowired
    private IService serviceRepo;

    @Autowired
    private IAccount accountRepo;

    @Autowired
    private IAdministration adminRepo;

    @GetMapping("/login")
    public String getLogin() {
        return "adminLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminhome";
    }

    @RequestMapping(value ="/allrooms")
    public String listRooms(Model model) {
        List<Room> rooms = roomrepository.findAll();
        model.addAttribute("allroom", rooms);
        return "allrooms";
    }

    @GetMapping("/addroom")
    public String addroom(Model model) {
        model.addAttribute("addroom", new Room());
        return "addRoom";
    }

    @PostMapping("/saveroom")
    public String saveRoom(@Valid Room room, BindingResult result, RedirectAttributes redirect, Model model) {

//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        roomrepository.save(room);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/allrooms";
    }

    @RequestMapping("updateroom{id}")
    public String updateRoom(@PathVariable int id, Model model) {
        Optional<Room> room = roomrepository.findById(id);
        model.addAttribute("room", room);
        return "updateroom";
    }

    @PostMapping("/updateroom")
    public String doUpdateRoom(@ModelAttribute("room") Room rooms, Model model) {
        roomrepository.save(rooms);
        model.addAttribute("allroom", roomrepository.findAll());
        return "redirect:/allrooms";
    }

    @GetMapping("deleteroom{id}")
    public String deleteRoom(@PathVariable int id, Model model) {
        roomrepository.deleteById(id);
        model.addAttribute("allroom", roomrepository.findAll());
        return "redirect:/allrooms";
    }

    @GetMapping("/addservice")
    public String addService(Model model) {
        model.addAttribute("addservice", new Service());
        return "addService";
    }

    @RequestMapping(value ="/allservices")
    public String listServices(Model model) {
        List<Service> services = serviceRepo.findAll();
        model.addAttribute("allservice", services);
        return "allService";
    }

    @PostMapping("/saveservice")
    public String saveService(@Valid Service service, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "addService";
        }
        serviceRepo.save(service);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/allservices";
    }

    @RequestMapping("updateservice{id}")
    public String updateService(@PathVariable int id, Model model) {
        Optional<Service> service = serviceRepo.findById(id);
        if (service.isPresent()) {
            model.addAttribute("service", service.get());
        }
        return "updateService";
    }

    @GetMapping("deleteservice{id}")
    public String deleteService(@PathVariable int id, Model model) {
        serviceRepo.deleteById(id);
        model.addAttribute("allservice", serviceRepo.findAll());
        return "redirect:/allservices";
    }

    @RequestMapping(value ="/allstaff")
    public String listStaff(Model model) {
        List<Administration> admin = adminRepo.findAll();
        model.addAttribute("allstaff", admin);
        return "allstaffs";
    }

    @GetMapping("/addstaff")
    public String addStaff(Model model) {
        model.addAttribute("addstaff", new Administration());
        return "addstaff";
    }

    @PostMapping("/savestaff")
    public String saveStaff(@Valid Administration admin, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "addstaff";
        }
        adminRepo.save(admin);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/allstaff";
    }

    @RequestMapping("updatestaff{id}")
    public String updateStaff(@PathVariable int id, Model model) {
        Optional<Administration> admin = adminRepo.findById(id);
        if (admin.isPresent()) {
            model.addAttribute("staff", admin.get());
        }
        return "updatestaff";
    }

    @GetMapping("deletestaff{id}")
    public String deleteStaff(@PathVariable int id, Model model) {
        adminRepo.deleteById(id);
        model.addAttribute("staff", adminRepo.findAll());
        return "redirect:/allstaff";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
