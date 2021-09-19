package br.com.maddytec.cliente.http.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("exemplo")
class Exemplo {

    @GetMapping
    fun exemploOla() = "Olá Mundo..."

}