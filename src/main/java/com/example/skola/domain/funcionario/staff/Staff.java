package com.example.skola.domain.funcionario.staff;

import com.example.skola.domain.funcionario.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "secretaria_staff")
@Data
@EqualsAndHashCode(callSuper = true)
public class Staff extends Funcionario {
    private String cargo;
    private String setor;
}
