## TODO

### Offline
* Event fragment
    * Buttons work
* Feed fragment
    * Click listener on objects
* Map Activity
    * Place markers
    * Make animation to start
* Wondertrade fragment
    * Think of interface
* General
    * Think about group infraestructure
    * Save instances when changing landscape


## The api

### Get endpoints

#### User

* /api/user/groups/{username} : Returns all groups of that username

* /api/user/all : Returns all users

* /api/user/groups/join/{username}/{groupname} : Makes that user join that group

#### Group

* /api/group/register : Creates group sent in body

* api/group/all : Returns all groups

#### Login

* /api/login/register : Creates an account with the user in the body

* /api/login/{username}/{password} : Try to login with that username and password