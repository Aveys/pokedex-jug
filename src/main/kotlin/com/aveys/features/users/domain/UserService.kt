package com.aveys.features.users.domain

import com.aveys.features.pokedexs.data.Pokedex
import com.aveys.features.users.data.User
import com.aveys.features.users.data.Users
import com.aveys.features.users.domain.dto.UserSignInDTO
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
