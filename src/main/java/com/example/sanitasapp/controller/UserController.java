package com.example.sanitasapp.controller;

import com.example.sanitasapp.models.Users;
import com.example.sanitasapp.services.AppointmentServices;
import com.example.sanitasapp.services.DoctorServices;
import com.example.sanitasapp.services.PatientServices;
import com.example.sanitasapp.services.ServicesImpl.AppointmentSerImpl;
import com.example.sanitasapp.services.ServicesImpl.DoctorSerImpl;
import com.example.sanitasapp.services.ServicesImpl.PatientSerImpel;
import com.example.sanitasapp.services.ServicesImpl.UsersSerImpel;
import com.example.sanitasapp.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {
    private final UsersServices usersServices;
    private final PatientServices patientServices;
    private final DoctorServices doctorServices;
    private final AppointmentServices appointmentService;


    @Autowired
    public UserController(UsersServices usersServices, PatientServices patientServices, DoctorServices doctorServices, AppointmentServices appointmentService) {
        this.usersServices = usersServices;
        this.patientServices = patientServices;
        this.doctorServices = doctorServices;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/login")
    public String usersPage(Model model){
        Users user = new Users();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/signIn")
    public String index(@ModelAttribute("user") Users user, Model model, HttpServletRequest request){
        String response = usersServices.validateUser(user);
        HttpSession session = request.getSession();

        switch (response) {
            case "Admin":
                return "redirect:/overall";
            case "Not Registered Email":
            case "Incorrect Password":
                model.addAttribute("error", response);
                session.setAttribute("error", response);
                return "redirect:/login";
            default:
                Optional<Users> user1 = usersServices.getUserByUsername(user.getUsername());
                session.setAttribute("user", user1.get());
                return "redirect:/check";
        }
    }

    @GetMapping("/check")
    public String usersView(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if(user.getUsername().contains("@patient")){
            session.setAttribute("person", patientServices.getPatientByUsername(user.getUsername()).get());
            return "redirect:/patientDashboard";
        }

        if(user.getUsername().contains("@doctor")){
            session.setAttribute("person", doctorServices.getDoctorByUsername(user.getUsername()));
            return "doctorview";
        }

        return "redirect:/overall";
    }

    @GetMapping("/overall")
    public String overallPage(Model model){
        model.addAttribute("allAppointment", appointmentService.getAllAppointment());
        return "index";
    }
}
