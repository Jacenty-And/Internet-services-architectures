import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayPokemon();
    backButton();
});

/**
 * Fetches currently logged user's characters and updates edit form.
 */
function fetchAndDisplayPokemon() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/pokemons/' + getParameterByName('pokemon'), true);
    xhttp.send();
}

/**
 * Action event handled for updating character info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayPokemon();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/pokemons/' + getParameterByName('pokemon'), true);

    const request = {
        'id': parseInt(getParameterByName('pokemon')),
        'name': document.getElementById('name').value,
        'level': parseInt(document.getElementById('level').value),
        'trainer': getParameterByName('trainer')
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

function backButton() {
    const button = document.getElementById("back");
    const link = '../trainer_view/trainer_view.html?trainer=' + getParameterByName('trainer');
    button.href = link;
}