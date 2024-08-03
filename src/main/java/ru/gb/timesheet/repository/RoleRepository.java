package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.timesheet.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
