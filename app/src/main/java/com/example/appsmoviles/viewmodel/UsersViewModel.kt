package com.example.appsmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsmoviles.model.Role
import com.example.appsmoviles.model.User
import com.example.appsmoviles.util.RequestResult
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UsersViewModel: ViewModel(){

    private val _users = MutableStateFlow( emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _userResult = MutableStateFlow<RequestResult?>(null)
    val userResult: StateFlow<RequestResult?> = _userResult.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    val db = Firebase.firestore

    init {
        LoadUsers()
    }

    fun create(user: User){
        viewModelScope.launch {
            _userResult.value = RequestResult.Loading
            _userResult.value = runCatching {createFirebase(user) }
                .fold(
                    onSuccess = { RequestResult.Success("Usuario creado correctamente") },
                    onFailure = { RequestResult.Failure(it.message ?: "Error registrando al usuario") },
                )
        }
    }

    private suspend fun createFirebase(user: User){

        db.collection("users")
            .add(user)
            .await()

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

    fun findById(id: String){
        viewModelScope.launch {
            _userResult.value = RequestResult.Loading
            _userResult.value = runCatching {findByIdFirebase(id) }
                .fold(
                    onSuccess = { RequestResult.Success("Usuario obtenido exitosamente") },
                    onFailure = { RequestResult.Failure(it.message ?: "Error obteniendo al usuario") },
                )
        }
    }

    private suspend fun findByIdFirebase(id: String){
        val snapshot = db.collection("users")
            .document(id)
            .get()
            .await()

        val user = snapshot.toObject(User::class.java)?.apply {
            this.id = snapshot.id
        }

        _currentUser.value = user

    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            _userResult.value = RequestResult.Loading
            _userResult.value = runCatching {loginFirebase(email, password) }
                .fold(
                    onSuccess = { RequestResult.Success("Login exitoso!") },
                    onFailure = { RequestResult.Failure(it.message ?: "Error en el login") },
                )
        }
    }

    private suspend fun loginFirebase (email: String, password: String){ //Esto sera cambiado mas adelante
        val snapshot = db.collection("users")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .await()

        if( snapshot.documents.isEmpty()) {
            throw Exception("Usuario o contraseña incorrectos")
        }else{
            snapshot.documents.mapNotNull {
                var user = it.toObject(User::class.java)?.apply {
                    this.id = it.id
                }
                _currentUser.value = user
            }
        }
    }

    fun resetOperationResult(){
        _userResult.value = null
    }

    fun LoadUsers() {
        /*_users.value = listOf(
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
        )*/
    }
}