import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreakingBadApiNetwork private constructor() {
private val httpClient: OkHttpClient
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

fun get(): BreakingBadApiService {
        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://breakingbadapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(BreakingBadApiService::class.java)
    }

private object Holder {
val instance = BreakingBadApiNetwork()
}

companion object {
val instance: BreakingBadApiNetwork by lazy { Holder.instance }
}
}