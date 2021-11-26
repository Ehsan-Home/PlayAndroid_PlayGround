# Retrofit

Retrofit is a light Java Network libaray enabling network requests in more managable way.

## Benefits:
1. Converting `json` to `java` object is done automatically via `converters`
2. No need for overriding internal functions in order to get `response code`. Retrofit provides the `response code` itself.
3. It is mainly used in `MVVM` Android architecture.
4. It follows `MVVM` architecture.
5. Amount of boilterplate codes is highly reduced.


## Steps:
- We create a `java` class, so Retrofit converts `json` to `java` and stores it in such class

```java
public class NAMEOFCLASS {

    // name of varibale should be the same 
    // as the name of the key in JSON
    private int value1;
    private String value2;

    // Retrofit converts JSONKey value in 
    // JSON into value3
    @SerializedName("JSONKey")
    private String value3

    // GETTERS

}
```

- We create an `interface` class to indicate the type of the request, relative URL, and etc.<br>
📝 Retrofit automatically implements the body  of interface functions.


```java
public interface MyApi {

    // type of the request , alogn with
    // relative URL, for example: 
    // https://www.getAPI.com/RELATIVE-URL
    // we set baseURL in other class
    @GET("RELATIVE-URL")
    public void fetchData();
}
```

- We implement Retrofit call in Activity or Network classes <br>
📝 `onFailure` callback happens when Retrofit is unable to communicate with the server <br>
📝 `onResponse` callback is for when Retrofit can communicate with the server. However, the response might indicate a problem terminating request execution. Hence, we need to check whether or not the request is successful in `onResponse`.
```java
// create retrofit class
Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://www.getAPI.com/")
        // select GSON converter to convert
        // JSON to java class
        .addConverterFactory(GsonConverterFactory.create())
        .build();

// create instance of interface class
// note that retrofit creates the body of functions
MyApi myApi = retrofit.create(MyApi.class);

// Create the request call
Call<List<NAMEOFCLASS>> call = MyApi.fetchData();

call.enqueue(new Callback<List<NAMEOFCLASS>>() {

    @Override
    public void onResponse(@NonNull Call<List<NAMEOFCLASS>> call, @NonNull Response<List<NAMEOFCLASS>> response) {

        if (!response.isSuccessful()) {
            Log.e("getPosts", "Server respond back error: " + response.code());
            return;
        }

        List<NAMEOFCLASS> posts = response.body();

    }

    @Override
    public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
            Log.e("getPosts", "error in communication with server : " + t.getMessage());
        }
    });
```
