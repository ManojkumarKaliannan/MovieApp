package com.backbase.assignment.ui.network.clientbuilder

import androidx.lifecycle.MutableLiveData
import com.backbase.assignment.ui.network.model.BaseResponse
import com.backbase.assignment.ui.network.model.Resource
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


open class BaseRepository {
    fun <T> getCallback(responseData: MutableLiveData<Resource<T>>): Callback<T> {
        return object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                if (t is IOException)
                    if(t is NoConnectivityException) {
                        responseData.value = Resource.failure(t.message, null)
                    }else{
                        responseData.value = Resource.failure("Server time out", null)
                    }
                else{
                    responseData.value = Resource.failure(t.message!!, null)
                }
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    responseData.value = Resource.success(response.body()!!)
                } else {
                    val errorResponse = getErrorResponse<T>(response.errorBody()!!)
                    responseData.value = Resource.error(errorResponse.message, errorResponse.data)
                }
            }
        }
    }

    fun <T> getListCallbacks(responseData: MutableLiveData<List<T>>): Callback<List<T>> {
        return object : Callback<List<T>> {

            override fun onResponse(call: Call<List<T>>, response: Response<List<T>>) {
                if (response.isSuccessful && response.body() != null) {
                    responseData.value = response.body()
                } else {
                    responseData.value = null
                }
            }

            override fun onFailure(call: Call<List<T>>, t: Throwable) {
                responseData.value = null
            }

        }
    }

    fun <T> getErrorResponse(responseBody: ResponseBody): BaseResponse<T> {
        val truckErrorResponse: BaseResponse<T> = try {
            val jObjError = JSONObject(responseBody.string())
            BaseResponse(
                success = true,
                message = jObjError.getString("message"),
                data = null
            )
        } catch (e: JSONException) {
            BaseResponse(
                success = false,
                message = "unable to get the response",
                data = null
            )
        }
        return truckErrorResponse
    }
}