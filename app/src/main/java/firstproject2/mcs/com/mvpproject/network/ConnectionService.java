package firstproject2.mcs.com.mvpproject.network;

import android.database.Observable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import firstproject2.mcs.com.mvpproject.MainActivity;
import firstproject2.mcs.com.mvpproject.data.CakesModel;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
//import android.database.Observable;
//import rx.Observable;
//import showtime.interview.com.apidemo.data.CakesModel;


public class ConnectionService implements ICakes_Interacter {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static HttpLoggingInterceptor interceptor;
    private static File httpCacheDirectory;
   // okHttpClient: caching; offline mode


    public ConnectionService() {
        getConnection();
    }

    public static IRequestInterface getConnection(){
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        httpCacheDirectory = new File(MainActivity.cacheDir, "offlineCache");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);



        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(provideCacheInterceptor())
                .addInterceptor(provideOfflineCacheInterceptor())
                .build();
        //Builder design pattern
        retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_Request.CAKES_BASE_URL)
                .client(okHttpClient)
        .build();

        return retrofit.create(IRequestInterface.class);

    }
    private static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                String cacheControl = originalResponse.header("Cache-Control");
                if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + 5000)
                            .build();
                } else {
                    return originalResponse;
                }
            }
        };
    }


    private static Interceptor provideOfflineCacheInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                try {
                    return chain.proceed(chain.request());
                } catch (Exception e) {

                    CacheControl cacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(1, TimeUnit.DAYS)
                            .build();

                    Request offlineRequest = chain.request().newBuilder()
                            .cacheControl(cacheControl)
                            .removeHeader("pragma")
                            .build();
                    return chain.proceed(offlineRequest);
                }
            }
        };
    }
    @Override
    public Observable<List<CakesModel>> getCakesList() {
        return getConnection().getCakesList();
    }
}
