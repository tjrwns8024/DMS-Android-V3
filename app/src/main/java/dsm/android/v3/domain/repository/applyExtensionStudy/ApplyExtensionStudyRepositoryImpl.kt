package dsm.android.v3.domain.repository.applyExtensionStudy

import dsm.android.v3.data.local.dao.ApplyExtensionStudyDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.extensionStudy.ApplyExtensionStudyModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class ApplyExtensionStudyRepositoryImpl(
    val apiClient: ApiClient,
    val dao: ApplyExtensionStudyDao): ApplyExtensionStudyRepository {

    override fun getMap(time: Int, classNum: Int): Single<Response<ApplyExtensionStudyModel>> = apiClient.getMap(time, classNum)

    override fun applyExtension(time: Int, body: HashMap<String, Int>): Single<Response<Unit>> = apiClient.applyExtension(time, body)

    override fun deleteExtension(time: Int): Single<Response<Unit>> = apiClient.deleteExtension(time)

    override fun loadExtensionMap(): Flowable<ApplyExtensionStudyModel> = dao.getAll()

    override fun saveExtensionMap(map: ApplyExtensionStudyModel) = dao.insertAll(map)

}