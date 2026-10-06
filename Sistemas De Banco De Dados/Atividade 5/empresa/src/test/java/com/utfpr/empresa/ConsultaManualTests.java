package com.utfpr.empresa;

import com.utfpr.empresa.entity.Departamento;
import com.utfpr.empresa.entity.Funcionario;
import com.utfpr.empresa.respository.DepartamentoRepository;
import com.utfpr.empresa.respository.FuncionarioRepository;
import com.utfpr.empresa.service.DepartamentoService;
import com.utfpr.empresa.service.FuncionarioService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConsultaManualTests {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testarConsultas() {

        //Feito para testar as questões da atv 4
        imprimirFuncionario("1. Funcionário por nome e dependentes", funcionarioService.buscarPorNomeEQuantidadeDependentes("Marcos Silva", 1));
        imprimirFuncionarios("2. Funcionários do departamento 1", funcionarioService.buscarPorDepartamento(1));
        imprimirDepartamento("3. Primeiro departamento", departamentoService.buscarPrimeiroDepartamento());
        imprimirFuncionario("4. Funcionário com maior salário", funcionarioService.buscarFuncionarioComMaiorSalario());
        imprimirFuncionarios("5. Três maiores salários", funcionarioService.buscarTresFuncionariosComMaioresSalarios());
        imprimirFuncionarios("6. Funcionários sem dependentes", funcionarioService.buscarFuncionariosSemDependentesOrdenadosPorNome());
        imprimirFuncionarios("7. Salários maiores que 3000 (JPQL)", funcionarioService.buscarPorSalarioMaiorQue(new BigDecimal("3000.00")));
        imprimirFuncionarios("8. Salários maiores que 3000 (native query)", funcionarioService.buscarPorSalarioMaiorQueNative(new BigDecimal("3000.00")));
        imprimirFuncionarios("9. Dois dependentes (@NamedQuery)", funcionarioService.buscarPorQuantidadeDependentes(2));
        imprimirFuncionarios("10 e 11. Nome contendo 'ar' (@NamedNativeQuery)", funcionarioService.buscarPorNomeContendo("ar"));
    }

    @Test
    @Transactional
    @Rollback
    void testarManipulacaoDeDadosETransacoes() {

        //Feito para testar as questões da atv 5

        Departamento departamentoOrigem = new Departamento();
        departamentoOrigem.setNome("Departamento de Teste - Origem");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionário de Teste");
        funcionario.setQtdDependentes(0);
        funcionario.setSalario(new BigDecimal("1000.00"));
        funcionario.setCargo("Analista");

        // Questão 5: os dois salvamentos ocorrem na mesma transação.
        Funcionario funcionarioSalvo = departamentoService.salvarDepartamentoEAssociarFuncionario(
                departamentoOrigem, funcionario);

        Departamento departamentoDestino = new Departamento();
        departamentoDestino.setNome("Departamento de Teste - Destino");
        departamentoDestino = departamentoRepository.save(departamentoDestino);

        // Questão 2: consulta com parâmetro nomeado.
        List<Funcionario> semDependentes = funcionarioService
                .buscarFuncionariosSemDependentesPorDepartamento(departamentoOrigem.getCodigo());
        assertEquals(1, semDependentes.size());

        // Questão 1: a procedure aumentar_salarios deve receber um percentual inteiro.
        BigDecimal salarioAntes = funcionarioSalvo.getSalario();
        funcionarioService.aumentarSalarios(1);
        entityManager.flush();
        entityManager.clear();
        BigDecimal salarioDepois = funcionarioRepository.findById(funcionarioSalvo.getCodigo())
                .orElseThrow()
                .getSalario();
        assertTrue(salarioDepois.compareTo(salarioAntes) > 0);

        // Questão 3: update com @Modifying transfere o funcionário para outro departamento.
        int transferidos = funcionarioService.transferirFuncionariosDeDepartamento(
                departamentoOrigem.getCodigo(), departamentoDestino.getCodigo());
        assertEquals(1, transferidos);

        // Questão 4: delete com @Modifying remove os funcionários do departamento de destino.
        int excluidos = funcionarioService.excluirFuncionariosPorDepartamento(departamentoDestino.getCodigo());
        assertEquals(1, excluidos);
        entityManager.flush();
        entityManager.clear();
        assertFalse(funcionarioRepository.existsById(funcionarioSalvo.getCodigo()));
    }

    private void imprimirFuncionario(String titulo, Funcionario funcionario) {
        System.out.println("\n" + titulo);
        if (funcionario == null) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }
        System.out.printf("Código: %d | Nome: %s | Dependentes: %d | Salário: %s%n",
                funcionario.getCodigo(), funcionario.getNome(), funcionario.getQtdDependentes(), funcionario.getSalario());
    }

    private void imprimirFuncionarios(String titulo, List<Funcionario> funcionarios) {
        System.out.println("\n" + titulo);
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }
        funcionarios.forEach(funcionario -> System.out.printf(
                "Código: %d | Nome: %s | Dependentes: %d | Salário: %s%n",
                funcionario.getCodigo(), funcionario.getNome(), funcionario.getQtdDependentes(), funcionario.getSalario()));
    }

    private void imprimirDepartamento(String titulo, Departamento departamento) {
        System.out.println("\n" + titulo);
        if (departamento == null) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }
        System.out.printf("Código: %d | Nome: %s%n", departamento.getCodigo(), departamento.getNome());
    }
}
