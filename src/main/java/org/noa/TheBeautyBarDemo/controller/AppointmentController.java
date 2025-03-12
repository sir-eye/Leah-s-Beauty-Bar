package org.noa.TheBeautyBarDemo.controller;


import org.noa.TheBeautyBarDemo.model.Appointment;
import org.noa.TheBeautyBarDemo.model.User;
import org.noa.TheBeautyBarDemo.repository.AppointmentRepository;
import org.noa.TheBeautyBarDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class AppointmentController {


    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/appointments")
    public String getAppointments(Model model, @RequestParam("email") String email) {
        User loggedInUser = userRepository.findByEmail(email);


        if (loggedInUser == null) {
            return "redirect:/users/login"; // Redirect if user is not found
        }


        model.addAttribute("user", loggedInUser);
        model.addAttribute("appointments", appointmentRepository.findByUserId(loggedInUser.getId()));
        return "appointments";
    }

    @PostMapping("/appointments")
    public String bookAppointment(@RequestParam("appointmentDate") String appointmentDate,
                                  @RequestParam("appointmentTime") String appointmentTime,
                                  @RequestParam("description") String description,
                                  @RequestParam("email") String email) {


        User loggedInUser = userRepository.findByEmail(email);


        if (loggedInUser == null) {
            return "redirect:/users/login"; // Redirect if no user is found
        }


        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate.trim());
        appointment.setAppointmentTime(appointmentTime.trim());
        appointment.setDescription(description.trim());
        appointment.setUser(loggedInUser);


        appointmentRepository.save(appointment);


        return "redirect:/users/appointments?email=" + email; // Redirect back to appointments page
    }
    // Show the update appointment form
    @GetMapping("/appointments/edit/{id}")
    public String editAppointmentForm(@PathVariable("id") Long id, Model model, @RequestParam("email") String email) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);


        if (appointment == null) {
            return "redirect:/users/appointments?email=" + email; // Redirect if appointment not found
        }


        model.addAttribute("appointment", appointment);
        model.addAttribute("email", email); // Keep track of logged-in user
        return "edit_appointment"; // Load edit page
    }
    // Update appointment details
    @PostMapping("/appointments/update/{id}")
    public String updateAppointment(@PathVariable("id") Long id,
                                    @RequestParam("appointmentDate") String appointmentDate,
                                    @RequestParam("appointmentTime") String appointmentTime,
                                    @RequestParam("description") String description,
                                    @RequestParam("email") String email) {


        Appointment appointment = appointmentRepository.findById(id).orElse(null);


        if (appointment == null) {
            return "redirect:/users/appointments?email=" + email; // Redirect if appointment not found
        }
        appointment.setAppointmentDate(appointmentDate.trim());
        appointment.setAppointmentTime(appointmentTime.trim());
        appointment.setDescription(description.trim());


        appointmentRepository.save(appointment); // Save updated details


        return "redirect:/users/appointments?email=" + email; // Redirect back to appointments page
    }
    // Delete an appointment
    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id, @RequestParam("email") String email) {
        appointmentRepository.deleteById(id);
        return "redirect:/users/appointments?email=" + email; // Redirect back to appointments page
    }
}



























//package org.noa.TheBeautyBarDemo.controller;
//
//import jakarta.servlet.http.HttpSession;
//import org.noa.TheBeautyBarDemo.model.Appointment;
//import org.noa.TheBeautyBarDemo.model.User;
//import org.noa.TheBeautyBarDemo.repository.AppointmentRepository;
//import org.noa.TheBeautyBarDemo.repository.UserRepository;
//import org.noa.TheBeautyBarDemo.service.AppointmentService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//@Controller
//@RequestMapping("/users")
//public class AppointmentController {
//
//    private final AppointmentService appointmentService;
//    private final AppointmentRepository appointmentRepository;
//    private final UserRepository userRepository;
//
//    public AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository, UserRepository userRepository) {
//        this.appointmentService = appointmentService;
//        this.appointmentRepository = appointmentRepository;
//        this.userRepository = userRepository;
//    }
//
//
//    // Optional: if you want to display appointments in a list
//    @GetMapping("/appointments")
//    public String getAppointments(Model model, @RequestParam("email") String email) {
//        User loggedInUser = userRepository.findByEmail(email);
//
//
//        if (loggedInUser == null) {
//            return "redirect:/users/login"; // Redirect to login if user is not found
//        }
//
//
//        model.addAttribute("user", loggedInUser); // Pass user object
//        model.addAttribute("appointments", appointmentRepository.findByUserId(loggedInUser.getId())); // Fetch user appointments
//        return "appointments";
//    }
//    @PostMapping("/appointments")
//    public String bookAppointment(@RequestParam("appointmentDate") String appointmentDate,
//                                  @RequestParam("appointmentTime") String appointmentTime,
//                                  @RequestParam("description") String description,
//                                  @RequestParam("email") String email,
//                                  HttpSession session) {
//        User loggedInUser = userRepository.findByEmail(email);
//
//
//        User loggedInUser1 = (User) session.getAttribute("loggedInUser");
//
//
//        if (loggedInUser == null) {
//            return "redirect:/users/login"; // Redirect if no user is logged in
//        }
//        Appointment appointment = new Appointment();
//        appointment.setAppointmentDate(appointmentDate);
//        appointment.setAppointmentTime(appointmentTime);
//        appointment.setDescription(description);
//        appointment.setUser(loggedInUser); // Link appointment to logged-in user
//
//
//        appointmentRepository.save(appointment); // Save to database
//
//
//        return "redirect:/users/appointments?email=" + email;
//    }
//}