package dsm.android.v3.presentation.viewModel.register

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dsm.android.v3.domain.repository.register.RegisterRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(val registerRepository: RegisterRepository) : BaseViewModel() {

    val registerConfirmCode = MutableLiveData<String>()
    val registerId = MutableLiveData<String>()
    val registerPw = MutableLiveData<String>()
    val registerPwConfirm = MutableLiveData<String>()


    val btnColorSet = MediatorLiveData<Boolean>().apply {
        addSource(registerConfirmCode) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()
        }
        addSource(registerId) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()

        }
        addSource(registerPw) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()
        }
        addSource(registerPwConfirm) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()
        }
    }

    val registerFinishedLiveEvent = SingleLiveEvent<Any>()
    val wrongUuidLiveEvent = SingleLiveEvent<Any>()
    val sameIdLiveEvent = SingleLiveEvent<Any>()
    val wrongPwLiveEvent = SingleLiveEvent<Any>()
    val badNetworkLiveEvent = SingleLiveEvent<Any>()

    fun doSignUp() {
        if (registerPw.value == registerPwConfirm.value) {
            btnColorSet.value = false
            add(registerRepository.signUp(
                hashMapOf(
                    "uuid" to registerConfirmCode.value,
                    "id" to registerId.value,
                    "password" to registerPw.value
                )
            ).subscribe({ response ->
                when (response.code()) {
                    201 -> registerFinishedLiveEvent.call()
                    204 -> wrongUuidLiveEvent.call()
                    205 -> sameIdLiveEvent.call()
                    else -> badNetworkLiveEvent.call()
                }
                btnColorSet.value = true
            }, {
                btnColorSet.value = true
                badNetworkLiveEvent.call()
            }))
        } else {
            wrongPwLiveEvent.call()
        }
    }

    fun MutableLiveData<String>.isValueBlank() = value.isNullOrBlank()

}
