package br.com.maddytec.cliente.http.controller;

import br.com.maddytec.cliente.entity.Cliente;
import br.com.maddytec.cliente.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/cliente")
class ClienteController(val clienteService: ClienteService, val modelMapper: ModelMapper) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun salvar(@RequestBody cliente: Cliente): Cliente{
        return clienteService.salvar(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listaCliente() = clienteService.listaCliente().toList()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarClientePorId(@PathVariable("id") id: Long) = clienteService.buscarPorId(id).orElse(null)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado.");


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removerCliente(@PathVariable("id") id: Long){
        clienteService.buscarPorId(id).orElse(null) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado.")
        clienteService.removerPorId(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarCliente(@PathVariable("id") id: Long, @RequestBody cliente: Cliente){
        clienteService.buscarPorId(id)
                .map{ clienteBase ->
                    modelMapper.map(cliente, clienteBase);
                    clienteService.salvar(clienteBase);
                }.orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado.")}
    }


}
