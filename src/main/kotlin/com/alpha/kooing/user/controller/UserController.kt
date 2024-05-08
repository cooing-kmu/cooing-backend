package com.alpha.kooing.user.controller

import com.alpha.kooing.user.service.UserService
import com.alpha.kooing.util.CommonResDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
){
    @GetMapping("")
    fun findAllUsers() : CommonResDto<*>{
        val result = userService.findAll() ?: return CommonResDto(HttpStatus.BAD_REQUEST, "Bad Request", null)
        return CommonResDto(HttpStatus.OK, "OK", result)
    }
}