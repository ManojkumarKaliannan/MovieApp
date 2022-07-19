# Mobile Assignment CS
This is a placeholder README file with the instructions for the assingment. We expect you to build your own README file.

## Kick off
In order for us to kick off your assingment, please **[watch this 9 minutes video](https://youtu.be/qUkYkm9bWak)** where we will go through the assingment instructions and answer some of the questions you might have.

## Delivering the code
* Fork this repo and select the access level as PRIVATE. This is very important. **[Check how to do it here](https://docs.gitlab.com/ee////user/project/working_with_projects.html#fork-a-project)**
* Do NOT open a PR to this repository.
* Add the user **m-cs-recruitment@backbase.com** as `Reporter` member **[Check how to do it here](https://docs.gitlab.com/ee/user/project/members/#add-a-user)**
* Once you are done with the development, send an e-mail to **m-cs-recruitment@backbase.com** AND CC the recruiter who is in touch with you with your info and repo. This helps us to keep track of your progress and move with the process faster.

Please remember to work with small commits, it help us to see how you improve your code :)

## Instructions

You should build an application using the TheMovieDB API. We have provided an initial application that will help you with fast-tracking app development. It contains the following:

* Class containing baseUrl and API key which is needed to fetch contents from TMDB API.
* Basic implementation of a scrollable list to fetch contents and display them in list format.
* Basic JSON parsing to parse server response and populate details.
* Placeholder RatingView class

### UI/UX
Below you can find the links to the layouts you need to follow. You can inspect each view to get the dimensions:

* [Android](https://share.goabstract.com/bd042e15-d8d9-4a19-8be0-c8e7d42a646c)
* [iOS](https://share.goabstract.com/cc138f78-f400-415e-96e8-ae705b715897)

### Functionalities
We expect you to implement the following functionalities in the app:

1. **List of playing now movies**
	* Client API details 
		* GET `https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=undefined&api_key=55957fcf3ba81b137f8fc01ac5a31fb5`
	* This list should be accessible in as a tab bar/bottom navigation item, as per UI/UX.
	* Each movie item should contain the following:
		* Poster image
		* Title
		* Rating (the couloured dot view): use red tint for movie ratings less than 50% and green for 50% and above.
		* Duration
	
2. **List of most popular movies**
	* Client API details  
		* GET `https://api.themoviedb.org/3/movie/popular?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US`
	* This list should be accessible in as a tab bar/bottom navigation item, as per UI/UX.
	* Each movie item should contain the following:
		* Poster image
		* Title
		* Rating
	* Cache movie images, in order to make smooth scrolling.
	
3. **When a user clicks on any movie item, it will navigate to a detailed screen, with more information about the movie**
	* Client API details 
		* GET `https://api.themoviedb.org/3/movie/{MOVIE_ID}?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US`
		* Where `MOVIE_ID` should be replaced with the id of the movie.
		* Example: https://api.themoviedb.org/3/movie/464052?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US
	* Detail screen should contain the following information:
		* Poster image: use the API as per described: https://developers.themoviedb.org/3/getting-started/images
		* Duration
		* Title
		* Overview
		* Release date
		* List of genres
	
### Additional Requirements And Restrictions
We expect you to follow this additional requirements and restrictions, as it will be part of how we evaluate your assignment:

1. Provide Unit Tests. This is very important for us to evaluate your level of seniority, so please spare some time to spend on developing Unit Tests.
2. 3rd party libraries are allowed (except for the rating view). However, do not use any Alpha version of libraries.
3. This is not an ordinary assignment. If you notice any strange behavior, you are free to make decisions regarding the implementation or to take things out of scope, as long as your decision can be justified.
4. Provide a README.md explaining your approach, which includes the image caching but also the rating view implementation and any other important decision or assumptions you made during development. Also, list all the 3rd party libraries used and the reason why.
5. You should follow the layouts provided to develop the functionalities.
6. The code of the assignment has to be delivered along with the git repository (.git folder). We want to see your progress. We require a cloud-hosted repository on Gitlab, which *MUST* be PRIVATE.
7. **Do not open PRs to the main repository.**
8. You are free to handle extra requirements, and this will be part of how we evaluate your work.
7. The application should be developed in portrait mode only.
9. Minimum Supported versions:
	* Android - 5.0 +
	* iOS - 14.0 +
10. Do not use any hybrid solutions, such as Reactive Native or Flutter.
11. As a recommendation, we would like to see you using SwiftUI+Combine or Jetpack Compose.


