//CARGO
package com.utfpr.empresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.empresa.entity.Cargo;
import com.utfpr.empresa.repository.CargoRepository;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public Cargo salvar(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public void delete(Integer id) {
        cargoRepository.deleteById(id);
    }

    public List<Cargo> listarTodos() {
        return cargoRepository.findAll();
    }
}

//FUNCIONARIO
package com.utfpr.empresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.empresa.entity.Funcionario;
import com.utfpr.empresa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public Funcionario salvar(Funcionario funcionario, Integer codigoCargo) {

        Cargo cargo = cargoRepository.findById(codigoCargo)
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));

        funcionario.setCargo(cargo);

        return funcionarioRepository.save(funcionario);
    }

    public void delete(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public List<Funcionario> listarTodosOrdenadosPorNome() {
        return funcionarioRepository.findAllByOrderByNomeAsc();
    }

    public long quantidadeFuncionarios() {
        return funcionarioRepository.count();
    }

}









package com.utfpr.empresa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.utfpr.empresa.entity.Cargo;
import com.utfpr.empresa.entity.Funcionario;
import com.utfpr.empresa.service.CargoService;
import com.utfpr.empresa.service.FuncionarioService;

@Configuration
public class DataLoader {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner executar(CargoService cargoService,
                               FuncionarioService funcionarioService) {

        return args -> {

            funcionarioService.listarTodos().forEach(funcionario ->
                    logger.info(
                            "Código: {}, Nome: {}, Sexo: {}, Telefone: {}, Cargo: {}",
                            funcionario.getCodigo(),
                            funcionario.getNome(),
                            funcionario.getSexo(),
                            funcionario.getTelefone(),
                            funcionario.getCargo().getCargo()
                    )
            );

            cargoService.listarTodos().forEach(cargo ->
                    logger.info(
                            "Código: {}, Cargo: {}",
                            cargo.getCodigo(),
                            cargo.getCargo()
                    )
            );

            funcionarioService.listarTodosOrdenadosPorNome().forEach(funcionario ->
                    logger.info(
                            "Código: {}, Nome: {}, Cargo: {}",
                            funcionario.getCodigo(),
                            funcionario.getNome(),
                            funcionario.getCargo().getCargo()
                    )
            );

            long quantidade = funcionarioService.quantidadeFuncionarios();

            logger.info("Quantidade de funcionários: {}", quantidade);
        };
    }
}














