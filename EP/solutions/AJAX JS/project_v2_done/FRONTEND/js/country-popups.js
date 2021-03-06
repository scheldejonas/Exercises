/**
 * Created by scheldejonas on 02/03/2017.
 */

var xhrCountryRequest = new XMLHttpRequest();
xhrCountryRequest.onreadystatechange = function () {
    if (xhrCountryRequest.readyState === 4 && xhrCountryRequest.status === 200) {
        var spanCountryHeadlineArray = document.querySelectorAll('.countryHeadline');
        var wikiPageLinkParagraph = document.getElementById('wikiPageLinkParagraph');
        var countryInfoSpan = document.querySelector('.countryInfoBox');
        //console.log(countryInfoSpan);
        var countryInfoModel = JSON.parse(xhrCountryRequest.responseText);
        console.log(countryInfoModel);
        for (var i = 0; i < spanCountryHeadlineArray.length; i += 1) {
            spanCountryHeadlineArray[i].innerText = countryInfoModel[0].name;
        }
        wikiPageLinkParagraph.innerHTML = '<a target="_blank" href="https://en.wikipedia.org/wiki/' + countryInfoModel[0].name + '">Wiki page</a>';
        var countryInfoHTML = '';
        for (var property in countryInfoModel[0]) {
            countryInfoHTML += '<p>' + property + ': ' + countryInfoModel[0][property] + '</p>';
        }
        //console.log(countryInfoHTML);
        countryInfoSpan.innerHTML = countryInfoHTML;
    }
};

const cardDiv = document.querySelector('div.svg-container');


// Use this to show with restcountries having CORS headers activated.
cardDiv.addEventListener('click', (event) => {
    if (event.target.tagName === 'path') {
        var countryId = event.target.id;
        var svgPopupPathContainer = document.getElementById('svgPopupPathContainer');
        var pathCountry = event.target;
        //console.log('id of country: ' + countryId);
        xhrCountryRequest.open('GET', "http://restcountries.eu/rest/v1/alpha?codes=" + countryId.substring(0,2));
        xhrCountryRequest.send();
        //console.log(pathCountry);
        //console.log(svgPopupPathContainer);
        svgPopupPathContainer.innerHTML = '';
        svgPopupPathContainer.appendChild(pathCountry.cloneNode());
    }
});

// Use this for you own web proxy, that serves as server web proxy setup.
// cardDiv.addEventListener('click', (event) => {
//     if (event.target.tagName === 'path') {
//         var countryId = event.target.id;
//         var svgPopupPathContainer = document.getElementById('svgPopupPathContainer');
//         var pathCountry = event.target;
//         console.log('id of country: ' + countryId);
//         xhrCountryRequest.open('GET', "http://localhost:8080/web-proxy/api/countries?countryId=" + countryId.substring(0,2));
//         xhrCountryRequest.send();
//         console.log(pathCountry);
//         console.log(svgPopupPathContainer);
//         svgPopupPathContainer.innerHTML = '';
//         svgPopupPathContainer.appendChild(pathCountry.cloneNode());
//     }
// });