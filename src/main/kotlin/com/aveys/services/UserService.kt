package com.aveys.services

import com.aveys.dto.UserSignInDTO
import com.aveys.entity.Pokedex
import com.aveys.entity.User
import com.aveys.entity.Users
import com.aveys.plugins.encodePassword
import com.aveys.utils.dbQuery
import java.time.Instant

class UserService {
    suspend fun addUser(userSignInDTO: UserSignInDTO) {
        dbQuery {
            val userCreated =
                User.new {
                    username = userSignInDTO.username
                    password = encodePassword(userSignInDTO.password)
                }
            Pokedex.new {
                user = userCreated
                createdAt = Instant.now()
            }
        }
    }

    suspend fun getUserByName(name: String): User? = dbQuery { User.find { Users.username eq name }.firstOrNull() }
}
