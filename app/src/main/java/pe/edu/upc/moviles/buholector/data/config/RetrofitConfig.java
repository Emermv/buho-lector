package pe.edu.upc.moviles.buholector.data.config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

  private static Retrofit retrofit;
  private static final String BASE_URL = "http://buholectorservices-env.eba-djmmtwth.us-east-1.elasticbeanstalk.com";

  public static HttpDomain getRetrofitInstance() {
    if (retrofit == null) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
      httpClient.addInterceptor(logging);
      retrofit = new retrofit2.Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(httpClient.build())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit.create(HttpDomain.class);
  }
}
