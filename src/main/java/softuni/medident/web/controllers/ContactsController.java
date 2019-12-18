package softuni.medident.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.medident.web.controllers.base.BaseController;

@Controller
public class ContactsController extends BaseController {

    @GetMapping("/contacts")
    @PreAuthorize("isAnonymous()")
    public ModelAndView contacts(){
        return super.view("contacts");
    }
}
