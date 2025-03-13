package com.mvc.gradesubmission;

import java.util.ArrayList;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class GradeController {
    

    List<Grade> grades = new ArrayList<>();


    @GetMapping("/")
    public String getForm(Model model , @RequestParam(required = false) String id){
    
        int index  = getGradeIndex(id);
        Grade grade = (index == Constants.NOT_FOUND) ? new Grade() : grades.get(index);

        model.addAttribute("grade", grade);
        return "form";
        
    }


    @PostMapping("/handleSubmit")
    public String postMethodName( Grade grade) {

        int index  = getGradeIndex(grade.getId());

        if (index == Constants.NOT_FOUND){
            grades.add(grade);
            
        }else{
            grades.set(index, grade);

        }

        return "redirect:/grades";
    }
    



    @GetMapping("/grades")
    public String hello(Model model){
        model.addAttribute("grades", grades); 
        return "grades";
    }


    public int getGradeIndex(String id){

        for(int i=0;i< grades.size() ;i++){
            if(grades.get(i).getId().equals(id)){
                return i;
            }
        }
        

        return Constants.NOT_FOUND;
    }


}
