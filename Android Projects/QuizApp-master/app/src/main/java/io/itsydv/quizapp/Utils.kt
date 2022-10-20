package io.itsydv.quizapp

// Using MathJax Script to render LaTeX
class Utils {
    companion object{
        var JS_FILES = "<script>\n" +
                "MathJax = {" +
                "  tex: {" +
                "    inlineMath: [['$', '$'], ['\\(', '\\)']]\n" +
                "  }" +
                "};" +
                "</script>" +"<style type=\"text/css\">\n" +
                "html, body {" +
                "margin: 0px;" +
                "padding: 0px;" +
                "}" +
                "</style>"+
                "<script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-chtml.js\"></script>\n" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/katex@0.11.1/dist/katex.min.css\" crossorigin=\"anonymous\">" +"<style>img{display: inline;height: auto;max-width: 100%;}</style>"
    }
}

// Resource class to store the current state of our request
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}