# AI-Diet-Planner-App
- The Android application offers personalized diet plans by leveraging AI-powered modules.
- It takes into account the user's personal information, such as age, weight, and dietary goals, to generate customized meal plans tailored to their specific needs and preferences.

## Layouts
<div align = center>
  <img src = "https://drive.google.com/uc?export=view&id=1ficEKX6LlBddyq2nUk_P5nHWCCuNkl0m" width = "70%" alt = "AppLayoutPoster">
</div>

## Implementations
- **Code:** The project utilizes both ***Java*** and ***Kotlin*** programming languages to implement its functionality.
  
- **Architecture:** The project follows the ***MVVM architecture*** pattern, allowing for modular and scalable development. Additionally, the layouts are managed using the ***binding*** concept within a ***heterogeneous recycler view***, enabling the creation of dynamic layouts based on specific requirements.

- **Handlings:**
  - ***Coroutines*** and ***LiveData*** are employed to efficiently handle responses, facilitating smooth execution and seamless data flow in the project's asynchronous operations.
  - In this project, the ***Android Jetpack's Navigation*** component is utilized to facilitate seamless navigation between different fragments in the application.
  - To ensure precise functionality based on received responses, a dedicated ***ViewModel*** is created for each fragment, enabling tailored actions to be performed accordingly.

## Platforms and Code-Stack
- **[Backend](https://github.com/RiSiNgFuRy/AI-Diet-Planner-Bakend)**
- **ngrok:** To make the backend accessible online and usable within the app, **[ngrok](https://ngrok.com/)** is employed as a hosting solution.

<div align = center>
  <img src = "https://drive.google.com/uc?export=view&id=1em3HGhHcA8mzhhxvSX-NHzRUUrSgtPSO" width = "60%" alt = "AppLayoutPoster">
</div>
