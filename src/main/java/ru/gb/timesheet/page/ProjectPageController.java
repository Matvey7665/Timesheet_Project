package ru.gb.timesheet.page;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.service.ProjectPageService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {

    private final ProjectPageService service;
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        Optional<ProjectPageDto> projectPageDto = service.findById(id);
        if (projectPageDto.isEmpty()){
            throw new NoSuchElementException();
        }

        model.addAttribute("project" , projectPageDto.get());

        return "project-page.html";
    }
}
