package dsm.android.v3.domain.repository.meal

import com.google.gson.JsonObject
import dsm.android.v3.domain.entity.meal.MealModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

interface MealRepository {
    fun getMeal(day: String): Single<Response<JsonObject>>

    fun loadMeal(): Flowable<MealModel>

    fun saveMeal(mealModel: MealModel)
}