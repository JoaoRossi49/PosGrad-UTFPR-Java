package com.utfpr.empresa.service;

import com.utfpr.empresa.entity.Funcionario;
import com.utfpr.empresa.respository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario buscarPorNomeEQuantidadeDependentes(String nome, Integer qtdDependentes) {
        return funcionarioRepository.findByNomeAndQtdDependentes(nome, qtdDependentes);
    }

    public List<Funcionario> buscarPorDepartamento(Integer codigoDepartamento) {
        return funcionarioRepository.findByDepartamentoCodigo(codigoDepartamento);
    }

    public Funcionario buscarFuncionarioComMaiorSalario() {
        return funcionarioRepository.findFirstByOrderBySalarioDesc();
    }

    public List<Funcionario> buscarTresFuncionariosComMaioresSalarios() {
        return funcionarioRepository.findTop3ByOrderBySalarioDesc();
    }

    public List<Funcionario> buscarFuncionariosSemDependentesOrdenadosPorNome() {
        return funcionarioRepository.findFuncionariosSemDependentesOrdenadosPorNome();
    }

    public List<Funcionario> buscarPorSalarioMaiorQue(BigDecimal salario) {
        return funcionarioRepository.findBySalarioMaiorQue(salario);
    }

    public List<Funcionario> buscarPorSalarioMaiorQueNative(BigDecimal salario) {
        return funcionarioRepository.findBySalarioMaiorQueNative(salario);
    }

    public List<Funcionario> buscarPorQuantidadeDependentes(Integer qtdDependentes) {
        return funcionarioRepository.findByQtdDependentes(qtdDependentes);
    }

    public List<Funcionario> buscarPorNomeContendo(String nome) {
        return funcionarioRepository.findByNomeContaining(nome);
    }

    @Transactional
    public void aumentarSalarios(Integer percentual) {
        funcionarioRepository.aumentarSalarios(percentual);
    }

    public List<Funcionario> buscarFuncionariosSemDependentesPorDepartamento(Integer codigoDepartamento) {
        return funcionarioRepository.findFuncionariosSemDependentesPorDepartamento(codigoDepartamento);
    }

    @Transactional
    public int transferirFuncionariosDeDepartamento(
            Integer codigoDepartamentoOrigem,
            Integer codigoDepartamentoDestino) {
        return funcionarioRepository.transferirFuncionariosDeDepartamento(
                codigoDepartamentoOrigem, codigoDepartamentoDestino);
    }

    @Transactional
    public int excluirFuncionariosPorDepartamento(Integer codigoDepartamento) {
        return funcionarioRepository.excluirFuncionariosPorDepartamento(codigoDepartamento);
    }
}
