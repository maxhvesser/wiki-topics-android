### Wiki Topics
Fetch a Wikipedia page for a given topic, return a count for number of occurrences for said topic in the page text

### Solution 
The main solution to this problem revolves around the following code snippet, taken from `HomeViewModel.kt`:

 ```kotlin
 /**
  * Iterates over the input [text] starting at a given index until a match is found, loops and
  * repeats, in this instance a separate counter that we create is present. Potentially easier
  * to immediately see what is happening 'under the hood'.
  */
private fun searchByIndexOf(text: String, topic: String): Int {
    var count = 0
    var index = 0

    while (true) {
        index = text.indexOf(topic, index)
        index += if (index != -1) {
            count ++
            topic.length
        } else return count
    }
}

/**
  * Slides one by one over the input for the given length, in this instance producing a 1 if
  * the window matches our [topic] else 0. It will slide over the entire input before
  * eventually summing the resulting values.
  */
private fun searchByWindowed(text: String, topic: String): Int {
    return text.windowed(topic.length) { if (it == topic) 1 else 0 }.sum()
}

/**
  * Matches based on a given pattern, simply in this instance we can just use the topic input.
  * findAll will produce a sequence to iterate through, checking matches.
  */
private fun searchByRegex(text: String, topic: String): Int {
    return Regex(topic).findAll(text).count()
}
 ```

I decided to have a bit of fun with this challenge and make this solution as a full Android app, providing a couple of options for search methods, and then showing the executions times for each method. This also allowed me to showcase the following:

- Remote API handling via Retrofit
- Dependency injection via Hilt (Dagger) 
- LiveData
- MVVM
- Clean UI
- Proper theme handling

### Preview
| | |
| - | - |
| ![](https://raw.githubusercontent.com/maxhvesser/wiki-topics-android/main/images/home.jpg)  | ![](https://raw.githubusercontent.com/maxhvesser/wiki-topics-android/main/images/home_input.jpg)  |
