# Opcional

No código, foram usadas bastante as ferramentas de ViewModel e LiveData, principalmente nas classes que se referem à pegar dados do banco.

```kotlin
class ContentRepository {
    val groupListStatus  = MutableLiveData<Resource<ArrayList<Group>>>()
    val feedListStatus = MutableLiveData<Resource<ArrayList<FeedListItem>>>()
    val eventListStatus = MutableLiveData<Resource<ArrayList<Event>>>()

...

class ServiceRepository {

    val loginStatus  = MutableLiveData<Resource<User>>()
    val createGroupStatus = MutableLiveData<Resource<Group>>()
    val createEventStatus = MutableLiveData<Resource<Event>>()
...
```

Em ambos os repositórios é possível ver uso massivo do MutableLiveData. Em conjunto com a classe de Resource 

```kotlin
sealed class Resource<T> {
    data class Complete<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    data class Error<T>(val error: Errors) : Resource<T>()
}
```

Foi possível usar para facilmente modificar os valores das variáveis que as atividades observavam das ViewModels.

```kotlin
loginStatus.value = Resource.Loading()
...
loginStatus.value = Resource.Error(Errors.ERROR_COMMUNICATING_WITH_API)
...
loginStatus.value = Resource.Complete(user)
```

Assim ficando bem fácil na Activity saber qual foi a resposta da chamada, quando ela acontecesse, dessa forma:
```kotlin
loginViewModel.content.observe(this, Observer {
            when(it){
                is Resource.Complete -> {
                    loading.visibility = View.INVISIBLE
                    var preferences = getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
                    preferences.edit().putString("session", User.toJson(it.data)).apply()
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is Resource.Error -> {
                    loading.visibility = View.INVISIBLE
                    Toast.makeText(this@LoginActivity, "Ops, something went wrong",Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    loading.visibility = View.VISIBLE
                }
            }
        })
```

As ViewModels basicamente carregavam o conteúdo e permitiam que as atividades observassem a variável para trazer as mudanças assim que elas aconteciam:

```kotlin
class GroupViewModel : ViewModel() {
    private val contentRepository : ContentRepository = ContentRepository()
    private val serviceRepository : ServiceRepository = ServiceRepository()

    private val groupStatus = contentRepository.groupListStatus
    val content = Transformations.map(groupStatus){it}

    private val eventCreationStatus = contentRepository.eventListStatus
    val eventContent = Transformations.map(eventCreationStatus){it}

    fun getGroupsList(username : String) = this.contentRepository.getGroupList(username)

    fun createGroup(group: Group) = this.serviceRepository.createGroup(group)

    fun joinGroup(username: String, groupName : String) = this.serviceRepository.joinGroup(username, groupName)

    fun createEvent(event : Event) = this.serviceRepository.createEvent(event)
}
```

Com essa infraestrutura, fazer chamadas ao backend ficou simples, sendo basicamente uma hierarquia da Viewmodel -> repositório -> Api

Api:

```kotlin
interface FetchApi {

    @GET("api/login/{username}/{password}")
    fun login(@Path("username") username : String, @Path("password") password:String): Call<DataUser>

    @POST("api/login/register")
    fun register(@Body user : DataUser) : Call<ResponseBody>

    @GET("api/user/groups/{username}")
    fun getGroupsByUsername(@Path("username") username : String) : Call<ArrayList<DataGroup>>

    @POST("api/group/register")
    fun createGroup(@Body group : Group) : Call<DataGroup>

    @POST("api/user/groups/join/{username}/{groupname}")
    fun joinGroup(@Path("username") username: String, @Path("groupname") groupName : String) : Call<DataGroup>

    @POST("api/group/event")
    fun createEvent(@Body event : Event) : Call<ResponseBody>

    @GET("api/user/events/{username}")
    fun getEventsByUsername(@Path("username") username: String) : Call<ArrayList<DataEvent>>
=
}
```
Foi usada a biblioteca retrofit2.

Cliente:

```kotlin
object ApiClient {

    var BASE_URL : String =  "https://go-events.herokuapp.com/"

    val getClient: FetchApi
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(FetchApi::class.java)
        }
}
```