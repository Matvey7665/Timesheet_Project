package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.aspect.Timer;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class TimesheetService {

  private final TimesheetRepository timesheetRepository;
  private final ProjectRepository projectRepository;
  private final EmployeeRepository employeeRepository;

  public TimesheetService(TimesheetService timesheetService){
    this(timesheetService.timesheetRepository,timesheetService.projectRepository,timesheetService.employeeRepository);
  }

  @Autowired
  public TimesheetService(TimesheetRepository timesheetRepository,ProjectRepository projectRepository,EmployeeRepository employeeRepository){
    this.projectRepository = projectRepository;
    this.employeeRepository = employeeRepository;
    this.timesheetRepository = timesheetRepository;
  }
  //@Timer
  public Optional<Timesheet> findById(Long id) {
    return timesheetRepository.findById(id);
  }

  public List<Timesheet> findAll() {
    return findAll(null, null);
  }

  public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
    //FIXME: вернуть фильтрацию
    return timesheetRepository.findAll();
  }

  public Timesheet create(Timesheet timesheet) {
    if (Objects.isNull(timesheet.getEmployeeId())){
      throw new IllegalArgumentException("employeeId must not be null");
    }
    if (employeeRepository.findById(timesheet.getEmployeeId()).isEmpty()){
      throw new NoSuchElementException("employee with id " + timesheet.getEmployeeId() + " does not exists");
    }
    if (Objects.isNull(timesheet.getProjectId())) {
      throw new IllegalArgumentException("projectId must not be null");
    }

    if (projectRepository.findById(timesheet.getProjectId()).isEmpty()) {
      throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
    }

    timesheet.setCreatedAt(LocalDate.now());
    return timesheetRepository.save(timesheet);
  }
  @Timer(enabled = false)
  public void delete(Long id) {
    timesheetRepository.deleteById(id);
  }

}
