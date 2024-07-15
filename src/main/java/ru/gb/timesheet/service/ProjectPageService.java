package ru.gb.timesheet.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.page.ProjectPageDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final ProjectService service;

    public Optional<ProjectPageDto> findById(Long id){
        return service.findById(id)
                .map(this::convert);
    }

    private ProjectPageDto convert(Project project){
        ProjectPageDto projectPageDto = new ProjectPageDto();

        projectPageDto.setId(String.valueOf(project.getId()));
        projectPageDto.setName(project.getName());

        return projectPageDto;
    }
}
