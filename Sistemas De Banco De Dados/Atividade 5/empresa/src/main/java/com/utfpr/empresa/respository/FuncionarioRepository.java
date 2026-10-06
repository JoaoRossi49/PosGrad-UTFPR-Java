package com.utfpr.empresa.respository;

import com.utfpr.empresa.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findAllByOrderByNomeAsc();
}