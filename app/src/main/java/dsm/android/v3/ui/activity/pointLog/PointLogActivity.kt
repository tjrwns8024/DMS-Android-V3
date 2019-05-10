package dsm.android.v3.ui.activity.pointLog

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.ui.adapter.PointLogAdapter
import dsm.android.v3.domain.entity.PointLogResponseModel
import dsm.android.v3.domain.repository.pointLog.PointLogRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import kotlinx.android.synthetic.main.activity_point_log.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PointLogActivity : AppCompatActivity() {

    @Inject
    lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_log)
        setSupportActionBar(pointLog_toolbar)

        BaseApp.appComponent.injectActivity(this)

        val repository = PointLogRepositoryImpl(apiClient)

        pointLog_toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "상벌점 내역 조회"

        pointLog_toolbar.setNavigationOnClickListener {
            finish()
        }
        
        Connecter.api.getPointLog(getToken(applicationContext)).enqueue(object : Callback<PointLogResponseModel> {
            override fun onResponse(call: Call<PointLogResponseModel>, response: Response<PointLogResponseModel>) {
                val body = response.body()
                pointLog_list_rv.layoutManager = LinearLayoutManager(this@PointLogActivity)
                pointLog_list_rv.adapter = body?.pointHistory?.let { PointLogAdapter(it) }

            }

            override fun onFailure(call: Call<PointLogResponseModel>, t: Throwable) {

            }

        })

    }
}