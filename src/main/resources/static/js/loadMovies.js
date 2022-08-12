var loadMoviesBtn = document.getElementById('loadMovies')

loadMoviesBtn.addEventListener('click', onLoadMovies);

function onLoadMovies(event) {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    var moviesContainer = document.getElementById('movies-container')
    moviesContainer.innerHTML = ''

    fetch("http://localhost:8080/api/all", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(movie => {
            // here we will create some elements and add them to the table.
            let row = document.createElement('tr')

            let titleCol = document.createElement('td')
            // let pictureCol = document.createElement('td')
            // let linkCol = document.createElement("td")


            titleCol.textContent = movie.title
            // pictureCol.textContent = '/images/movie_random.jpg'
            // linkCol.textContent = "<a sec:authorize=\"!isAuthenticated()\"  href=\"/register\" style=\"color: #0c5460\">See more</a>\n" +
            //     "                <a sec:authorize=\"isAuthenticated()\" th:href=\"@{/movies/movie-details/{id}(id = ${f.id})}\" style=\"color: #0c5460\">See more</a>\n" +
            //     "        "

            // add the columns to the parent row
            row.appendChild(titleCol)
            // row.appendChild(pictureCol)
            // row.appendChild(linkCol)


            moviesContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}