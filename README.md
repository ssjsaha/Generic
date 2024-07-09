# Resi-take-home-challenge

Description :
Resy Take Home Assignment Sample Project
The sample project is a small photo list and viewer app. It consists of two views. The first is a list
of available images. The second is a detail view displaying a selected image and one piece of
associated metadata.

API:
- The data for this app is to be loaded from the Lorem Picsum API, full documentation at
  https://picsum.photos/

List View:
- The list screen should display a list of all available images. Each row should display the
  filename of the corresponding image.
- Data for the list is to be loaded from the list endpoint of the Lorem Picsum API, at
  https://picsum.photos/list
- The response from the list endpoint should be decoded and transformed into a Swift or Kotlin
  type before being displayed.
- Tapping on a row in the list view should display the detail view for the selected image.

Detail View:
- The detail view should display the selected image, as well as the author’s name.
- Images should fill the full width of the screen.
- If the image is a landscape image, it should display centered vertically, with the author’s
  name shown directly below.
- If the image is a portrait image, it should display at the top of the screen, with the
  author’s name directly below.
- Images should be loaded from the image endpoint, at
  https://picsum.photos/[width]/[height]?image=<id>
- The images should be requested at their original aspect ratio, as specified in the data
  returned from the list endpoint, and should be displayed without cropping or distorting
  the image.
- Detail view should include a back button to return to the list view.

Other Requirements:
- The app only needs to work on phones in portrait orientation and does not need to work on
  tablets.
- The project should include a unit test that verifies that the portrait vs landscape logic is
  functioning correctly.
- The project should include one additional unit test of your choice.
- Methods of building UI:
  iOS: No third-party libraries, Storyboards or XIB files. Build UI programmatically in Swift
  using auto layout constraints or using SwiftUI.
  Android: No third-party libraries. Build UI programmatically in Kotlin or using Layout
  Editor in XML.



App Architecture: The app is made following the clean architecture with MVI design pattern
The app has 3 layers.

1.Domain
2.Data
3.Presentation

1. The domain layer contains the business logic part. Since it is a small app, the only business
logic is what data we are retrieving from the Picsum api list

2. The data layer contains how we are retrieving the data. In other words, the implementation of 
domain with android specific objects  and APIs is done in the data layer

3. The presentation layer contains the ui related logics. It contains the Viewmodel and the UI 
related logics, the Composables from Jetpack Compose for list view and details view

Technical requirements: 
In the description two  main tasks are required to be done. 
1. Get list of pics from the picsum list api
2. Show the image in a detail view with a back button

Other requirement: 
1. Need to show landscape image in the middle  and portrait image on the top of the composable.


Features;
1. Get a list of available images from picsum api 
2. User clicks on some image row and get to see the image in original aspect ratio


Extra features made:
1. Swipe refresh feature. Users can refresh the list page in case of an error
2. A Splash screen is made when starting the app
3. For loading, a shimmer loader is used without any third party library 
(Thanks to Philip Lackner and his amazing android lessons 
link : https://www.youtube.com/watch?v=NyO99OJPPec)


Architecture breakdown: 

When got the list data from the api, we show the data in a lazy column in a composable
(jetpack compose). We use MVI pattern for that. So
we have an onEvent() function and a uiState variable
When user makes any interaction with the UI composable, we pass an event to the viewmodel on Event
function. And when any ui change is needed like we get some data from the api, we update the state
of the uiState which is a stateflow and can be converted to composable state so that the ui knows 
about the update in the page and gets updated.

we have a viewmodel -> PhotoListViewModel 
In the viewmodel We have a function onEvent()
There are two kinds of events 
1. user navigates to photo details page
2. get the data from backend or refresh

We used the same viewmodel for both the details and the list page since there are not much work to
do for the viewmodel to do

Core Landscape-Portrait logic:
As feature request, the aspect ratio of the photo must be same and we have to request with the same
AR.
So what I did was, got the image from the list api. And then according to the aspect ratio,
I requested the image that fits the device width.

Let's do the math here

lets say the device is X*Y pixel 
And the picture original width*height = a*b
now, we need to fit the image into the X width
so the image width will be X. and according to the aspect ratio of the image,
The height of the image will be =  X*(b/a)
So, at last the image dimension will be (X, X*(b/a)) where b is the image original height and a
is the original image width

example: 
Device dimen = (1080*2400)px (width*height)
Image dimen  = (5000*3000)px (width*height)

To fit the device, the image width should be = 1080px
So the image new height according to device width = 1080 * (3000/5000) = 648
So the new image dimension according to fitting the width of the device is (1080*648)
We requested the image at that (1080*648) dimension, and show that into image composable


Library used for image:
# Coil: Coil is a convenient library to load images from url. It directly shows the image from url 
# into composable Image()  component.

It has some listeners, error, loading and success,
So if any error happens like no internet while loading the photo from the url, we show a 
error image. And corresponding success call will also show the author name below to the
image

Without coil, what was needed to be done is, download the image using some other way like 
httpUrlConnection and convert the input stream to bitmap and then show the bitmap into 
the Image() component of jetpack compose !

