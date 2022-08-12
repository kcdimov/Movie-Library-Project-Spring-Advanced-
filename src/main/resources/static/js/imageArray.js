
const pictures = [];

 // th:inline="javascript" th:each="f : ${allMovies}" th:object="${f}"
    pictures.push("[(${f.pictures})]")

    const getRandomWord = function () {
    return pictures[Math.floor(Math.random() * pictures.length)]
    // = document.getElementById("movie-picture").src;
};

    $(function () {
    $('.pictures').mouseenter(function () {
        $('#movie-picture').html(getRandomWord());
    });
});
