package com.example.chuckapplication.ui.theme.model

import com.example.chuckapplication.ui.theme.model.ChuckAPI.chuckSpeaksFrench
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


//Les beans
//pour la chuck API
data class ChuckBean(var icon_url:String,var value:String)
//pour google tranlate
data class ChuckFrenchBean(
    val from: String,
    val ok: Boolean,
    val response: String,
    val text: String,
    val to: String
)
object ChuckAPI {

    val client = OkHttpClient()
    val gson = Gson()
    val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder()
            .url("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("X-RapidAPI-Key", "bef9f2c6f5msh0679de80264549dp1f0276jsne10bb7e98fb9")
            .addHeader("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
            .build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }

    fun chuckLoadYou(): ChuckBean {
        val json: String =
            sendGet("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random")
        //Parser le JSON avec le bon bean et GSON
        val data: ChuckBean = gson.fromJson(json, ChuckBean::class.java)
        return data
    }

    fun sendGoogleTranslate(texte: String): String {
        val client = OkHttpClient()

        val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "text=$texte&from=en&to=fr")
        val request = Request.Builder()
            .url("https://translate281.p.rapidapi.com/")
            .post(body)
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .addHeader("X-RapidAPI-Key", "bef9f2c6f5msh0679de80264549dp1f0276jsne10bb7e98fb9")
            .addHeader("X-RapidAPI-Host", "translate281.p.rapidapi.com")
            .build()

        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code} ${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }

    fun chuckSpeaksFrench(): String {
        val json: String = sendGoogleTranslate(chuckLoadYou().value)
        //Parser le JSON avec le bon bean et GSON
        val frenchData: ChuckFrenchBean = gson.fromJson(json, ChuckFrenchBean::class.java)
        return frenchData.response
    }
}
fun main() {
    val test= chuckSpeaksFrench()
    println(test)
}