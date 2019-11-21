package com.diegoferreiracaetano.data.di

import androidx.room.Room
import com.diegoferreiracaetano.data.BuildConfig
import com.diegoferreiracaetano.data.local.AppDatabase
import com.diegoferreiracaetano.data.local.card.CardRepositoryLocal
import com.diegoferreiracaetano.data.local.transaction.TransactionRepositoryLocal
import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.data.remote.payment.PaymentRepositoryRemote
import com.diegoferreiracaetano.data.remote.user.UserRepositoryRemote
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.domain.payment.PaymentRepository
import com.diegoferreiracaetano.domain.transaction.TransactionRepository
import com.diegoferreiracaetano.domain.user.UserRepository
import java.util.concurrent.TimeUnit
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val REQUEST_TIMEOUT: Long = 60

val dataModule: Module = module {

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
    }

    single {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = get<OkHttpClient.Builder>()

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "database-name")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { get<AppDatabase>().cardDao() }

    single { get<AppDatabase>().transactionDao() }

    single { get<AppDatabase>().userDao() }

    single { get<Retrofit>().create(PicpayApi::class.java) }

    single<UserRepository> { UserRepositoryRemote(get()) }

    single<PaymentRepository> { PaymentRepositoryRemote(get()) }

    single<CardRepository> { CardRepositoryLocal(get()) }

    single<TransactionRepository> { TransactionRepositoryLocal(get()) }
}
