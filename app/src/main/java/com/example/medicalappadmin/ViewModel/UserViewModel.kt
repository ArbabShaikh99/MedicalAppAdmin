package com.example.medicalappadmin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalappadmin.FunctionResponseState.DeleteResponseState
import com.example.medicalappadmin.Repo.Repo
import com.example.medicalappadmin.FunctionResponseState.User.GetAllUserState
import com.example.medicalappadmin.FunctionResponseState.User.UpdateUserState
import com.example.medicalappadmin.State.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repo:Repo): ViewModel() {


    private val _getAllUserState = MutableStateFlow(GetAllUserState())
    val getAllUserState = _getAllUserState.asStateFlow()

    private val _isApprovedUserResponseState = MutableStateFlow(UpdateUserState())
    val isApprovedUserResponseState = _isApprovedUserResponseState.asStateFlow()

    private val _isBlockedUserResponseState = MutableStateFlow(UpdateUserState())
    val isBlockedUserResponseState = _isBlockedUserResponseState.asStateFlow()

    private val _isDeleteUserResponseState = MutableStateFlow(DeleteResponseState())
    val isDeleteUserResponseState = _isDeleteUserResponseState.asStateFlow()


    init {
        getAllUser()

    }

    fun getAllUser() {
        viewModelScope.launch (Dispatchers.IO){
            repo.getAllUserRepo().collect { state ->
                when (state) {
                    is State.loading -> {
                        _getAllUserState.value = GetAllUserState(loading = true)
                    }

                    is State.Success -> {
                        _getAllUserState.value = GetAllUserState(data = state.data, loading = false)
                    }

                    is State.Error -> {
                        _getAllUserState.value =
                            GetAllUserState(Error = state.message, loading = false)
                    }
                }
            }
        }
    }


    fun approveUser(userId: String, isApproved: Int) {
        viewModelScope.launch (Dispatchers.IO){
            repo.approveUser(
                userID = userId,
                isApproved = isApproved
            ).collect {
                when (it) {
                    is State.loading -> {
                        _isApprovedUserResponseState.value = UpdateUserState(loading = true)
                    }

                    is State.Success -> {
                        _isApprovedUserResponseState.value =
                            UpdateUserState(Data = it.data, loading = false)
                    }

                    is State.Error -> {
                        _isApprovedUserResponseState.value =
                            UpdateUserState(Error = it.message, loading = false)
                    }
                }
            }
        }
    }

        fun UpDateBlockUser(userId: String, isBlock: Int) {
            viewModelScope.launch(Dispatchers.IO) {
                repo.updateBlockStatus(
                    userID = userId,
                    isBlock = isBlock
                ).collect {
                    when (it) {
                        is State.loading -> {
                            _isBlockedUserResponseState.value = UpdateUserState(loading = true)
                        }

                        is State.Success -> {
                            _isBlockedUserResponseState.value =
                                UpdateUserState(Data = it.data, loading = false)
                        }

                        is State.Error -> {
                            _isBlockedUserResponseState.value =
                                UpdateUserState(Error = it.message, loading = false)
                        }
                    }
                }
            }
        }

        fun deleteUser(userId: String) {
            viewModelScope.launch (Dispatchers.IO){
                repo.deleteSpecificUser(
                    userID = userId
                ).collect {
                    when (it) {
                        is State.loading -> {
                            _isDeleteUserResponseState.value =
                                DeleteResponseState(isLoading = true)
                        }

                        is State.Success -> {
                            _isDeleteUserResponseState.value = DeleteResponseState(data = it.data)
                        }

                        is State.Error -> {
                            _isDeleteUserResponseState.value =
                                DeleteResponseState(error = it.message)
                        }

                    }
                }
            }
        }



    fun resetUserState(){
        _isApprovedUserResponseState.value = UpdateUserState(
            loading = false,
            Data = null,
            Error = null
        )
        _isBlockedUserResponseState.value = UpdateUserState(
            loading = false,
            Data = null,
            Error = null
        )
        _isDeleteUserResponseState.value = DeleteResponseState(
            isLoading = false,
            data = null,
            error = null
        )
    }
    }



