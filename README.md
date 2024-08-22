BookShelf Application which will list the books
Design Patten followed: model-view-viewModel


Libararies Used:
1. Glide for Image loading
2. Retrofit for Api Calls
3. Room for persistent storage


Modules:
1. Data -> (Local, Remote, Repository)
2. Di -> Data injection
3. Domain -> (Models, Repository)
4. Presentation -> (Screens) 


Screens :
1. Splash Screen
2. Login Screen
3. SignUp Screen
4. Books Guide Screen
5. Book Detail Screen


Flows : 
1. Login Flow:
   Splash Screen -> if not loggedIn -> Login Screen
         -> Enter Details -> (login) -> Books Guide Screen
         -> (no account) -> SignUp Screen -> (create account) -> Login Screen -> (login) -> Books Guide Screen
2. Books Guide screen Flow:
   Splash Screen -> If loggedIn -> Books Guide Screen
         -> click Book Thumbnail -> Book Detail Screen


Features:
1. Stimulated login and signup with sharedPreferences
2. Password should consist minimum 8 charactes, One Upper Case, one Lower case, one number and one special character
3. Display a List of Books
4. On clicking the thumbnail of book (image), will move to Book detail screen
5. Book detail screen, we can add tags to the book using the floating action Button


Need To Implement:
1. Custom pagination support for the Book List
2. Persistent storage of tags for the specific book
3. Realtime Authentication for login and signUp
4. Need to move the complete project to follow MVI design pattern
5. Need to handle network scenarios
6. Give support for filtering based on the tags.

