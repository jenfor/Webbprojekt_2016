package com.united.ctrl;




import com.united.auth.User;
import com.united.core.Course;
import com.united.core.Moment;
import com.united.core.Registration;
import com.united.core.School;
import com.united.view.registrations.AddRegistrationBB;
import com.united.view.registrations.DeleteRegistrationBB;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 Used for Add, Edit Delete pages

 */
@Named("registrationController")
@RequestScoped
public class RegistrationController {

    private static final Logger LOG = Logger.getLogger(RegistrationController.class.getSimpleName());
    
    @Inject
    private School school;
    
    private AddRegistrationBB addBB;
    private DeleteRegistrationBB delBB;
    
   
     public void newRegistration() {
       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
       Map<String, Object> sessionMap = externalContext.getSessionMap();
       User u = (User) sessionMap.get("user");
       
       List<Registration> rl = school.getRegistrationList().getAllRegistrationsForUsername();
       if(school.getRegistrationList().getCCForTeacher(school.getCourseList().getById(addBB.getId())).equals("true")) {
            for(Registration r : rl) {
                r.setCurrentCourse("false");
                school.getRegistrationList().update(r);
            }
            school.getRegistrationList().create(new Registration(u, school.getCourseList().getById(addBB.getId()), "true"));
       }
       else
           school.getRegistrationList().create(new Registration(u, school.getCourseList().getById(addBB.getId())));
    }
     
    //not working!!!
    public void cloneCourseRegistration() {
       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
       Map<String, Object> sessionMap = externalContext.getSessionMap();
       User u = (User) sessionMap.get("user");
       
       Course c = new Course(school.getCourseList().generatedCourseId(school.getCourseList().getById(addBB.getId()).getId()), school.getCourseList().getById(addBB.getId()).getName(), school.getCourseList().getById(addBB.getId()).getVersion());
       Moment newM;
       for(Moment m : school.getCourseList().getById(addBB.getId()).getMoments()) {
           newM = new Moment(m.getName());
           school.getMomentList().create(newM);
       }
       //c.setMoments();
       school.getCourseList().create(c);
       
       Registration p = new Registration(u, c, school.getRegistrationList().getCCForTeacher(school.getCourseList().getById(addBB.getId())));
       school.getRegistrationList().create(p);
    }

    public void deleteRegistration() {
       Long id = delBB.getId();
       LOG.log(Level.INFO, "***DEL ID {0}", id);
       //school.getRegistrationList().deleteRegistrationById(id);
       school.getRegistrationList().delete(id);
    }

    @Inject
    public void setAddBB(AddRegistrationBB addBB) {
        this.addBB = addBB;
    }
    
    @Inject
    public void setDelBB(DeleteRegistrationBB delBB) {
        this.delBB = delBB;
    }

}
