package com.example.appsmoviles.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appsmoviles.model.Role
import com.example.appsmoviles.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel: ViewModel(){

    private val _users = MutableStateFlow( emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()
    init {
        LoadUsers()
    }

    fun LoadUsers() {
        _users.value = listOf(
            User(
                id = "1",
                name = "Juan",
                username = "juan123",
                role = Role.ADMIN,
                city = "Armenia",
                email = "juan@email.com",
                password = "123456"
            ),
            User(
                id = "2",
                name = "Pedro",
                username = "pedro123",
                role = Role.USER,
                city = "Armenia",
                email = "pedro@email.com",
                password = "123456"
            ),
            User(
                id = "3",
                name = "Maria",
                username = "maria123",
                role = Role.USER,
                city = "Armenia",
                email = "maria@email.com",
                password = "123456"
            ),
            User(
                id = "4",
                name = "Ana",
                username = "ana123",
                role = Role.USER,
                city = "Armenia",
                email = "ana@email.com",
                password = "123456"
            ),
            User(
                id = "5",
                name = "Luis",
                username = "luis123",
                role = Role.USER,
                city = "Armenia",
                email = "luis@email.com",
                password = "123456"
            ),
            User(
                id = "6",
                name = "Carlos",
                username = "carlos123",
                role = Role.USER,
                city = "Armenia",
                email = "carlos@email.com",
                password = "123456"
            ),
            User(
                id = "7",
                name = "Sofia",
                username = "sofia123",
                role = Role.USER,
                city = "Armenia",
                email = "sofia@email.com",
                password = "12345"
            ),
            User(
                id = "8",
                name = "Laura",
                username = "laura123",
                role = Role.USER,
                city = "Armenia",
                email = "laura@email.com",
                password = "123456"
            ),
            User(
                id = "9",
                name = "Diego",
                username = "diego123",
                role = Role.USER,
                city = "Armenia",
                email = "diego@email.com",
                password = "123456"
            ),
            User(
                id = "10",
                name = "Valentina",
                username = "valentina123",
                role = Role.USER,
                city = "Armenia",
                email = "valentina@email.com",
                password = "12345"
            )
        )
    }

    fun create(user: User){
        _users.value = _users.value + user
    }

    fun update(user: User){
        _users.value = _users.value.map {
            if(it.id == user.id){
                user
            }else{
                it
            }
        }
    }

    fun findById(id: String): User?{
        return _users.value.find { it.id == id }
    }

    fun findByEmail(email: String): User?{
        return _users.value.find { it.email == email }
    }

    fun login(email: String, password: String): User?{
        return _users.value.find { it.email == email && it.password == password }
    }
}