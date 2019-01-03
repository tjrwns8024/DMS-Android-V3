package dsm.android.v3.ui.mypage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import org.jetbrains.anko.doFromSdk

class MyPageViewModel(private val contract: MyPageContract?): ViewModel() {

    private var initalTimeDialogFragment: Long = 0
    private lateinit var logoutContract: MyPageContract.LogoutContract
    private lateinit var bugReportContract: MyPageContract.BugReportContract
    private lateinit var institutionReportContract: MyPageContract.InstitutionReportContract

    val nameText = MutableLiveData<String>()
    val infoText = MutableLiveData<String>()
    val meritText = MutableLiveData<String>()
    val demeritText = MutableLiveData<String>()
    val adviceText = MutableLiveData<String>()

    val bugTitleEditText = MutableLiveData<String>()
    val bugContentEditText = MutableLiveData<String>()

    val institutionTitleEditText = MutableLiveData<String>()
    val institutionRoomNumberEditText = MutableLiveData<String>()
    val institutionReportContentEditText = MutableLiveData<String>()


    init {
        // TODO("나중에 통신하는 과정에서 Model 클래스 작성할 때 merit, demerit 넣어줌")
        // contract?.startCountAnimation()
    }

    constructor (contract: MyPageContract.LogoutContract) : this(contract = null){
        this.initalTimeDialogFragment = initalTimeDialogFragment
        logoutContract = contract
    }
    constructor (contract: MyPageContract.BugReportContract) : this(contract = null){
        this.initalTimeDialogFragment = initalTimeDialogFragment
        bugReportContract = contract
    }
    constructor (contract: MyPageContract.InstitutionReportContract) : this(contract = null){
        this.initalTimeDialogFragment = initalTimeDialogFragment
        institutionReportContract = contract
    }

    fun clickEnterInstitutionReport() = contract?.showDialogInstitutionReport()
    fun clickEnterQuestionResearch() = contract?.intentQuestionResearch()
    fun clickEnterBugReport() = contract?.showDialogBugReport()
    fun clickEnterLogout() = contract?.showDialogLogout()
    fun clickEnterPasswordChange() = contract?.intentPasswordChange()
    fun clickEnterMeritHistory() = contract?.intentMeriteHistory()
    fun clickEnterIntroDevelopers() = contract?.intentintroDevelopers()

    fun bugClickCancel() = bugReportContract.exitBugReport()
    fun bugClickSend(){
        if (bugTitleEditText.value.equals(null) || bugTitleEditText.value!!.isBlank())
            bugReportContract.flagBugTitleBlankError()

        else if (bugContentEditText.value.equals(null) || bugContentEditText.value!!.isBlank())
            bugReportContract.flagBugContentBlankError()

        else {
            // TODO("버그 내용 서버에 보내는 동작")
            bugReportContract.exitBugReport()
        }
    }

    fun institutionClickCancel() = institutionReportContract.exitInstitutionReport()

    fun institutionClickSend(){
        if (institutionTitleEditText.value.equals(null) || institutionTitleEditText.value.toString().isBlank())
            institutionReportContract.flagInstitutionTitleBlankError()

        else if (institutionRoomNumberEditText.value.equals(null) || institutionRoomNumberEditText.value.toString().isBlank())
            institutionReportContract.flagInstitutionRoomNumberBlankError()

        else if (institutionReportContentEditText.value.equals(null) || institutionReportContentEditText.value.toString().isBlank())
            institutionReportContract.flagInstitutionContentBlankError()

        else{
            // TODO("고장 내용 서버에 보내는 동작")
            institutionReportContract.exitInstitutionReport()
        }
    }

    fun logoutClickCancel() = logoutContract.exitLogout()

    fun logoutClickLogout() {
        // TODO("로그아웃하는 동작")
        logoutContract.exitLogout()
    }
}