package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokeDayPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeDayRemoteDataSource {
    fun findRandom(callback: JokeDayPresenter) {
        HTTPClient.retrofit().create(ChuckNorrisAPI::class.java).findRandom().enqueue(object : Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful) {
                    val joke = response.body() ?: throw RuntimeException("Piada não encontrada")
                    callback.onSuccess(joke)
                } else {
                    val error = response.errorBody()?.string()
                    callback.onError(error ?: "Erro desconhecido")
                }

                callback.onComplete()
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                callback.onError(t.message ?: "Erro interno")
                callback.onComplete()
            }
        })

    }
}