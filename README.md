## Project Description
go!Events is a auxiliary app for Pokémon go! players, focusing on communities. Since pokémon go! promotes union and forming groups of people to hunt together, it is very common for players in a certain region to unite and make Whatsapp groups to plan meeting and participate in events, including charity.

Even with this promotion, the pokémon go app itself does not have any form of communication or group forming mechanic. Thinking of this, I thought about creating an app to implement this function.

One can create groups, create an event for you group, join other groups and see this where this events will take place on the map.


## Project Infraestructure

#### Login
There is a simple login infraestructure, with your credentials being saved on the backend(encrypted) and your session being saved in the sharedpreferences.

#### Main Activity
Here is a ViewPager with all three main fragments. It is slideable and in sync with the bottom bar.

First fragment is a feed list. It is supossed to contain useful general information on groups and events you are participating. It has not been done, so it's a recycler view filled with random data.

Second Fragment is a event list. They are events that groups you are in are promoting.

Third Fragment is a profile. You can manage groups you are, creating a new group or joining existing ones.

#### Group Infraestructure
Being owner of a group, you can create a new event that will be associated with that group, that will appear on the event list of everyone in that group. In theory, you can associeate lat and long to place markers on users maps, but this has not been implemented. You can only choose the name, lat and long are sent to the backend as 10 and 10.

## Learned in Class
Here are some things i learned in class in built into my app

* Constraint Layout

```xml
  <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/frameLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="10">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv_group"
            android:layout_width="match_parent"
            android:layout_height="480dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent">

        </androidx.recyclerview.widget.RecyclerView>

        <ProgressBar
            android:id="@+id/loading"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="211dp"
            android:visibility="invisible"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent" />


    </androidx.constraintlayout.widget.ConstraintLayout>
```
Several pages have a constraint layout as parent layout or built in inside a linear layout. This makes the layout cleaner and more responsive.

* Recycler View

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_feed"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </androidx.recyclerview.widget.RecyclerView>

</FrameLayout>
```

```kotlin
class EventsListRecyclerAdapter(private var myDataset: ArrayList<EventListItem>) : RecyclerView.Adapter<EventsListRecyclerAdapter.RecyclerListViewHolder>() {


    class RecyclerListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title : TextView? = null
        var body : TextView? = null
        var image : ImageView? = null
    }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerListViewHolder {
            val eventItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.event_recycler_item, parent, false) as View

            var recHolder = RecyclerListViewHolder(eventItem)
            recHolder.body = eventItem.findViewById(R.id.tv_eventbody)
            recHolder.title = eventItem.findViewById(R.id.tv_eventtitle)

            return recHolder
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            holder.title?.text = this.myDataset[position].getTitle()
            holder.body?.text = this.myDataset[position].getBody()
        }

        override fun getItemCount() = myDataset.size

        fun updateItemList(newItens : ArrayList<EventListItem>){
            this.myDataset = newItens
            notifyDataSetChanged()
        }

}
```
The app is all about recycler views, getting information from the backend and displaying with custom views in the screen for the user. This is possible easily with the power of RecyclerViews. Using Adapters and viewHolders to set custom object listeners and updating the UI in real-time are important assets that can be implemented using RecyclerViews.

* Intents & Shared Preferences

```kotlin
   manageGroup.setOnClickListener {
            activity?.startActivity(Intent(context, GroupActivity::class.java))
        }
```
```kotlin
 var preferences = getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
                    preferences.edit().putString("session", User.toJson(it.data)).apply()
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
```
Intents are essential for opening activities and saving user sessions. It is utterly important when you want to do something with an activity before opening it. Always remember to ```finish()``` activities that you don't want on the pile stack too.

* Threads

```kotlin
    fun register(username: String, password: String){
        loginStatus.value = Resource.Loading()
        val user = DataUser(username, UUID.randomUUID(),username, password)
        val call : Call<ResponseBody> = ApiClient.getClient.register(user)
        call.enqueue(object: Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginStatus.value = Resource.Error(Errors.RESPONSE_NOT_SUCCESSFUL)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    login(username, password)
                }
                else{
                    loginStatus.value = Resource.Error(Errors.EMPTY_RESPONSE_FROM_API)
                }
            }

        })

    }
```
Although not the classic thread you may see, this app is full of callbacks been done in the backgroud. Since the app is server-heavy, in a lot of screens you will see the loading symbol while something is being fetched. In a way, it's a theard that is not stopping the UI from being shown while a worker is doing something in the background.

* App permisions
```xml
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
Nowadays every app needs internet access, and the other 3 are requeriments of the open street maps [osdroid library](https://github.com/osmdroid/osmdroid/wiki/How-to-use-the-osmdroid-library).

* Data

Map data in this app is saved in the SD storage, if possible. This is due to the osmdroid library. Other data is fetched for activities and it's lost after finished, unfortunately. All calls are for the [backend i wrote](https://github.com/Gui-Lima/go-Events-Back) and are up using [heroku](https://dashboard.heroku.com/) in [this site](https://go-events.herokuapp.com/). Api calls endpoints are on the bottom of this file.


## The api

### Get endpoints

#### User

* /api/user/groups/{username} : Returns all groups of that username

* /api/user/all : Returns all users

* /api/user/groups/join/{username}/{groupname} : Makes that user join that group

* /api/user/events/{username} : Returns events that user is related to


#### Group

* /api/group/register : Creates group sent in body

* api/group/all : Returns all groups

#### Login

* /api/login/register : Creates an account with the user in the body

* /api/login/{username}/{password} : Try to login with that username and password